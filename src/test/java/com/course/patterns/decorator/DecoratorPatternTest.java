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
    @DisplayName("Verify Dark Roast with Double Mocha and Whip computes layered cost and description")
    void testDarkRoastWithDoubleMochaAndWhip() {
        Beverage beverage = new DarkRoast(); // 0.99
        beverage = new Mocha(beverage);      // +0.20 -> 1.19
        beverage = new Mocha(beverage);      // +0.20 -> 1.39
        beverage = new Whip(beverage);       // +0.10 -> 1.49

        assertThat(beverage.getDescription())
                .isEqualTo("Most Excellent Dark Roast, Mocha, Mocha, Whip");
        assertThat(beverage.cost()).isCloseTo(1.49, within(0.001));
    }

    @Test
    @DisplayName("Verify Espresso with Soy and Whip")
    void testEspressoWithSoyAndWhip() {
        Beverage beverage = new Espresso();  // 1.99
        beverage = new Soy(beverage);        // +0.15 -> 2.14
        beverage = new Whip(beverage);       // +0.10 -> 2.24

        assertThat(beverage.getDescription())
                .isEqualTo("Espresso, Soy, Whip");
        assertThat(beverage.cost()).isCloseTo(2.24, within(0.001));
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
