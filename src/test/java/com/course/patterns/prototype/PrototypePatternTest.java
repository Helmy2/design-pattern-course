package com.course.patterns.prototype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PrototypePatternTest {

    @Test
    @DisplayName("Verify Deep Copy isolates mutations completely between original and clone")
    void testDeepCopyIsolation() {
        NetworkSecurityProfile original = new NetworkSecurityProfile(
                "BASE_PROFILE", 100, List.of("PORT_80", "PORT_443")
        );

        NetworkSecurityProfile tenantClone = original.clone();
        tenantClone.setProfileName("TENANT_CORP_A");
        tenantClone.addRule("PORT_8080");

        // Deep copy isolates original completely
        assertThat(original.getProfileName()).isEqualTo("BASE_PROFILE");
        assertThat(original.getFirewallRules()).containsExactly("PORT_80", "PORT_443");

        // Clone has its own independent customized state
        assertThat(tenantClone.getProfileName()).isEqualTo("TENANT_CORP_A");
        assertThat(tenantClone.getFirewallRules()).containsExactly("PORT_80", "PORT_443", "PORT_8080");
    }

    @Test
    @DisplayName("Verify SecurityProfileRegistry serves pre-warmed clones without cross-tenant pollution")
    void testSecurityProfileRegistry() {
        SecurityProfileRegistry registry = new SecurityProfileRegistry();
        registry.register("PCI_DSS", new NetworkSecurityProfile(
                "PCI_DSS", 500, List.of("ENCRYPT_ALL", "PORT_443")
        ));

        // Tenant 1 gets a clone and customizes it
        NetworkSecurityProfile clone1 = registry.getClone("PCI_DSS");
        clone1.setProfileName("TENANT_BANK_1");
        clone1.addRule("PORT_9999");

        // Tenant 2 requests a clone from the registry
        NetworkSecurityProfile clone2 = registry.getClone("PCI_DSS");
        clone2.setProfileName("TENANT_BANK_2");

        // Clones and original template are distinct in memory
        assertThat(clone1).isNotSameAs(clone2);
        assertThat(clone1.getProfileName()).isEqualTo("TENANT_BANK_1");
        assertThat(clone1.getFirewallRules()).contains("PORT_9999");

        // Tenant 2 is NOT contaminated with Tenant 1's custom rule
        assertThat(clone2.getProfileName()).isEqualTo("TENANT_BANK_2");
        assertThat(clone2.getFirewallRules()).containsExactly("ENCRYPT_ALL", "PORT_443");

        assertThatThrownBy(() -> registry.getClone("NON_EXISTENT"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Verify Kotlin data class copy creates isolated clones with targeted mutation")
    void testKotlinCopy() {
        KotlinSecurityProfile original = new KotlinSecurityProfile(
                "DEFAULT", 100, List.of("PORT_80", "PORT_443")
        );

        KotlinSecurityProfile enterprise = original.copy(
                "ENTERPRISE",
                1000,
                List.of("PORT_80", "PORT_443", "PORT_8080")
        );

        assertThat(original.getMaxBandwidthMbps()).isEqualTo(100);
        assertThat(enterprise.getMaxBandwidthMbps()).isEqualTo(1000);
        assertThat(enterprise.getProfileName()).isEqualTo("ENTERPRISE");
    }
}
