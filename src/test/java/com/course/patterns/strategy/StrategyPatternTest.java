package com.course.patterns.strategy;

import com.course.patterns.strategy.spring.PaymentStrategyMap;
import com.course.patterns.strategy.spring.PaymentStrategyMap.CheckoutService;
import com.course.patterns.strategy.spring.PaymentStrategyMap.PayPalPaymentStrategy;
import com.course.patterns.strategy.spring.PaymentStrategyMap.PaymentProvider;
import com.course.patterns.strategy.spring.PaymentStrategyMap.StripePaymentStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StrategyPatternTest {

    @Test
    @DisplayName("Verify MallardDuck uses default fly with wings and quack strategies")
    void testMallardDuckStrategy() {
        Duck mallard = new MallardDuck();
        assertThat(mallard.display()).isEqualTo("I'm a real Mallard duck");
        assertThat(mallard.performQuack()).isEqualTo("Quack");
        assertThat(mallard.performFly()).isEqualTo("I'm flying with wings!");
    }

    @Test
    @DisplayName("Verify RubberDuck cannot fly and squeaks using encapsulated strategies")
    void testRubberDuckStrategy() {
        Duck rubberDuck = new RubberDuck();
        assertThat(rubberDuck.display()).isEqualTo("I'm a yellow rubber duckie");
        assertThat(rubberDuck.performQuack()).isEqualTo("Squeak");
        assertThat(rubberDuck.performFly()).isEqualTo("I can't fly.");
    }

    @Test
    @DisplayName("Verify ModelDuck swaps flying behavior dynamically at runtime")
    void testDynamicBehaviorSwapping() {
        Duck modelDuck = new ModelDuck();
        assertThat(modelDuck.performFly()).isEqualTo("I can't fly.");

        // Dynamically change strategy at runtime:
        modelDuck.setFlyBehavior(new FlyBehavior.FlyRocketPowered());
        assertThat(modelDuck.performFly()).isEqualTo("I'm flying with a rocket propulsion boost!");
    }

    @Test
    @DisplayName("Verify Spring Strategy Map pattern resolves strategies without if/else branching")
    void testSpringStrategyMap() {
        CheckoutService checkoutService = new CheckoutService(List.of(
                new StripePaymentStrategy(),
                new PayPalPaymentStrategy()
        ));

        String stripeResult = checkoutService.checkout(PaymentProvider.STRIPE, new BigDecimal("49.99"));
        assertThat(stripeResult).isEqualTo("Processed $49.99 via Stripe");

        String payPalResult = checkoutService.checkout(PaymentProvider.PAYPAL, new BigDecimal("12.50"));
        assertThat(payPalResult).isEqualTo("Processed $12.50 via PayPal");
    }
}
