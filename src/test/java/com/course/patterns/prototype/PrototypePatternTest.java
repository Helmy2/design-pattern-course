package com.course.patterns.prototype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PrototypePatternTest {

    @Test
    @DisplayName("Verify standard NetworkSecurityProfile instantiation")
    void testStandardProfileCreation() {
        NetworkSecurityProfile profile = new NetworkSecurityProfile(
                "STANDARD", 100, List.of("PORT_80", "PORT_443")
        );

        assertThat(profile.getProfileName()).isEqualTo("STANDARD");
        assertThat(profile.getMaxBandwidthMbps()).isEqualTo(100);
        assertThat(profile.getFirewallRules()).containsExactly("PORT_80", "PORT_443");
    }

    @Test
    @DisplayName("Problem Demonstration: Without Prototype, clients must manually copy fields via getters/setters")
    void testManualCopyingCoupling() {
        NetworkSecurityProfile baseTemplate = new NetworkSecurityProfile(
                "BASE_PCI_DSS", 500, List.of("ENCRYPT_ALL", "PORT_443")
        );

        // Flaw: Client code must manually extract all properties and create a new instance,
        // coupling client code to the concrete constructor and internal collection types
        NetworkSecurityProfile tenantProfile = new NetworkSecurityProfile(
                "TENANT_A",
                baseTemplate.getMaxBandwidthMbps(),
                new ArrayList<>(baseTemplate.getFirewallRules())
        );
        tenantProfile.addRule("PORT_8080");

        assertThat(tenantProfile.getProfileName()).isEqualTo("TENANT_A");
        assertThat(tenantProfile.getFirewallRules()).contains("PORT_8080");
        assertThat(baseTemplate.getFirewallRules()).doesNotContain("PORT_8080");
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
