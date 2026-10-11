package com.course.patterns.decorator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class DecoratorPatternTest {

    @Test
    @DisplayName("Verify base beverages without condiments")
    void testBaseBeverages() {
        Beverage espresso = new Espresso();
        assertThat(espresso.getDescription()).isEqualTo("Espresso");
        assertThat(espresso.cost()).isCloseTo(1.99, within(0.001));

        Beverage darkRoast = new DarkRoast();
        assertThat(darkRoast.getDescription()).isEqualTo("Most Excellent Dark Roast");
        assertThat(darkRoast.cost()).isCloseTo(0.99, within(0.001));
    }

    @Test
    @DisplayName("Problem Demonstration: Rigid boolean flags cannot handle multiple portions (e.g. Double Mocha)")
    void testBeverageBooleanFlagsProblem() {
        DarkRoast coffee = new DarkRoast();
        coffee.setMocha(true);
        coffee.setWhip(true);

        // Single portions work: 0.99 + 0.20 + 0.10 = 1.29
        assertThat(coffee.cost()).isCloseTo(1.29, within(0.001));

        // BUT: What if customer wants a Double Mocha?
        // Setting mocha = true again has no effect - boolean cannot represent count!
        coffee.setMocha(true);
        assertThat(coffee.cost()).isCloseTo(1.29, within(0.001)); // Still 1.29 instead of 1.49!
    }

    @Test
    @DisplayName("Verify LowerCaseInputStream custom Java I/O decorator converts uppercase to lowercase")
    void testLowerCaseInputStream() throws IOException {
        String text = "CLEAN CODE AND DESIGN PATTERNS!";
        try (InputStream in = new LowerCaseInputStream(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)))) {
            byte[] bytes = in.readAllBytes();
            String result = new String(bytes, StandardCharsets.UTF_8);
            assertThat(result).isEqualTo("clean code and design patterns!");
        }
    }

    @Test
    @DisplayName("Verify Kotlin class delegation with 'by' decorates send() while auto-forwarding other methods")
    void testKotlinChannelDelegation() {
        NotificationChannel raw = new StandardEmailChannel();
        NotificationChannel audited = new AuditedNotificationChannel(raw);

        // Decorated method
        assertThat(audited.send("Order confirmed"))
                .isEqualTo("[AUDIT_LOG] Email sent: Order confirmed");

        // Automatically forwarded methods
        assertThat(audited.getStatus()).isEqualTo("ONLINE");
        assertThat(audited.getChannelId()).isEqualTo("SMTP_PROD");
    }
}
