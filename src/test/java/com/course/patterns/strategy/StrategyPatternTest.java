package com.course.patterns.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrategyPatternTest {

    @Test
    @DisplayName("Verify MallardDuck behaves naturally using superclass inheritance")
    void testMallardDuck() {
        Duck mallard = new MallardDuck();
        assertThat(mallard.display()).isEqualTo("I'm a real Mallard duck");
        assertThat(mallard.quack()).isEqualTo("Quack");
        assertThat(mallard.fly()).isEqualTo("I'm flying!!");
        assertThat(mallard.swim()).isEqualTo("All ducks float, even decoys!");
    }

    @Test
    @DisplayName("Problem Demonstration: RubberDuck erroneously inherits fly() behavior from Duck superclass")
    void testRubberDuckInheritanceFlaw() {
        Duck rubberDuck = new RubberDuck();
        assertThat(rubberDuck.display()).isEqualTo("I'm a yellow rubber duckie");
        assertThat(rubberDuck.quack()).isEqualTo("Squeak");

        // The inheritance smell: rubber duck flies across the screen!
        assertThat(rubberDuck.fly()).isEqualTo("I'm flying!!");
    }

    @Test
    @DisplayName("Problem Demonstration: PaymentProcessor uses rigid if/else checks violating OCP")
    void testPaymentProcessorOcpViolation() {
        PaymentProcessor processor = new PaymentProcessor();

        assertThat(processor.process("STRIPE", new BigDecimal("100.00")))
                .isEqualTo("Processed $100.00 via Stripe");
        assertThat(processor.process("PAYPAL", new BigDecimal("50.00")))
                .isEqualTo("Processed $50.00 via PayPal");

        // Fails for new providers without modifying PaymentProcessor code:
        assertThatThrownBy(() -> processor.process("ADYEN", new BigDecimal("25.00")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unsupported payment provider: ADYEN");
    }
}
