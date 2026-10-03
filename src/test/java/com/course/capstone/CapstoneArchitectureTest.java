package com.course.capstone;

import com.course.capstone.legacy.LegacyOrderManagementMonolith;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CapstoneArchitectureTest {

    @Test
    @DisplayName("Verify LegacyOrderManagementMonolith executes in starter, but suffers from all major code smells")
    void testLegacyMonolithExecution() {
        LegacyOrderManagementMonolith monolith = new LegacyOrderManagementMonolith();

        boolean result = monolith.processAndFulfillOrder(
                "ORD-999",
                "customer@enterprise.com",
                "US",
                50.0,
                20,      // 50 * 20 = 1000, 10% discount -> 900, US tax 8% (72) -> 972 + express 25 -> 997.0
                "STRIPE",
                true,
                true
        );

        assertThat(result).isTrue();
        assertThat(monolith.getExecutionAudit()).contains(
                "STRIPE_API_CHARGE:99700_CENTS",
                "SMTP_SEND:customer@enterprise.com:TOTAL=997.00",
                "DB_SAVE_ORDER:ORD-999"
        );

        // Invalid email rejected inline
        assertThatThrownBy(() -> monolith.processAndFulfillOrder(
                "ORD-1", "bademail", "US", 10.0, 1, "STRIPE", false, false
        )).isInstanceOf(IllegalArgumentException.class);
    }
}
