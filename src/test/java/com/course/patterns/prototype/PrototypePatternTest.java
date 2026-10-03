package com.course.patterns.prototype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PrototypePatternTest {

    @Test
    @DisplayName("Demonstrate shallow copy hazard: mutating clone corrupts the original")
    void testShallowCopyBug() {
        List<String> rules = new ArrayList<>(List.of("PORT_80", "PORT_443"));
        ShallowSecurityProfile original = new ShallowSecurityProfile("DEFAULT", 100, rules);

        ShallowSecurityProfile clone = original.clone();
        clone.addRule("PORT_8080");

        // The bug: mutating the clone also mutated the original!
        assertThat(original.getFirewallRules()).contains("PORT_8080");
        assertThat(clone.getFirewallRules()).contains("PORT_8080");
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
