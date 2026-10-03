package com.course.patterns.decorator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class DecoratorPatternTest {

    @Test
    @DisplayName("Verify base beverages without condiments")
    void testBaseBeverages() {
        Beverage espresso = new Espresso();
        assertThat(espresso.getDescription()).isEqualTo("Espresso");
        assertThat(espresso.cost()).isEqualTo(1.99);

        Beverage darkRoast = new DarkRoast();
        assertThat(darkRoast.getDescription()).isEqualTo("Most Excellent Dark Roast");
        assertThat(darkRoast.cost()).isEqualTo(0.99);
    }

    @Test
    @DisplayName("Problem Demonstration: Rigid boolean flags and subclass explosion")
    void testBeverageInheritanceProblem() {
        com.course.patterns.decorator.problem.BeverageInheritanceProblem.RigidBeverageWithFlags rigid =
                new com.course.patterns.decorator.problem.BeverageInheritanceProblem.RigidBeverageWithFlags();
        rigid.setMocha(true);
        rigid.setWhip(true);

        // Flaw: cannot order double mocha with boolean flag!
        assertThat(rigid.cost()).isEqualTo(2.29);

        com.course.patterns.decorator.problem.BeverageInheritanceProblem.DarkRoastWithMochaAndWhip hardcoded =
                new com.course.patterns.decorator.problem.BeverageInheritanceProblem.DarkRoastWithMochaAndWhip();
        assertThat(hardcoded.cost()).isEqualTo(1.29);
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
