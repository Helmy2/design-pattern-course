package com.course.patterns.strategy;

import com.course.patterns.strategy.spring.PaymentStrategyMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrategyPatternTest {

    @Test
    @DisplayName("Verify MallardDuck delegates to initial flying and quacking strategies")
    void testMallardDuckStrategies() {
        Duck mallard = new MallardDuck();
        assertThat(mallard.display()).isEqualTo("I'm a real Mallard duck");
        assertThat(mallard.performQuack()).isEqualTo("Quack");
        assertThat(mallard.performFly()).isEqualTo("I'm flying!!");
        assertThat(mallard.swim()).isEqualTo("All ducks float, even decoys!");
    }

    @Test
    @DisplayName("Problem Demonstration: Rigid inheritance causes RubberDuck to fly and OCP violation in payments")
    void testInflexibleDuckInheritanceProblem() {
        var flawedRubberDuck = new com.course.patterns.strategy.problem.InflexibleDuckInheritanceProblem.FlawedRubberDuck();
        // The flaw: RubberDuck accidentally inherits fly() from superclass!
        assertThat(flawedRubberDuck.fly()).isEqualTo("I'm flying!!");

        String result = com.course.patterns.strategy.problem.InflexibleDuckInheritanceProblem.NaivePaymentProcessor
                .process("STRIPE", BigDecimal.valueOf(100));
        assertThat(result).isEqualTo("Processed $100 via Stripe");
    }

    @Test
    @DisplayName("Verify dynamic runtime behavior swapping on ModelDuck")
    void testDynamicBehaviorSwapping() {
        Duck model = new ModelDuck();
        assertThat(model.performFly()).isEqualTo("I can't fly");

        // Swap strategy dynamically at runtime!
        model.setFlyBehavior(new FlyBehavior.FlyRocketPowered());
        assertThat(model.performFly()).isEqualTo("I'm flying with a rocket!");
    }

    @Test
    @DisplayName("Verify Spring Boot Strategy Map pattern dynamically resolves payment providers without if/else")
    void testSpringStrategyMap() {
        List<PaymentStrategyMap.PaymentStrategy> strategies = List.of(
                new PaymentStrategyMap.StripePaymentStrategy(),
                new PaymentStrategyMap.PayPalPaymentStrategy()
        );
        PaymentStrategyMap.CheckoutService checkoutService = new PaymentStrategyMap.CheckoutService(strategies);

        String stripeResult = checkoutService.checkout(PaymentStrategyMap.PaymentProvider.STRIPE, BigDecimal.valueOf(100));
        assertThat(stripeResult).isEqualTo("Processed $100 via Stripe");

        String payPalResult = checkoutService.checkout(PaymentStrategyMap.PaymentProvider.PAYPAL, BigDecimal.valueOf(250));
        assertThat(payPalResult).isEqualTo("Processed $250 via PayPal");
    }
}
