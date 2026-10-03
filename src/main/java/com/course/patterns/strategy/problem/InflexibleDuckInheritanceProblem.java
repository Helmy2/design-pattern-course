package com.course.patterns.strategy.problem;

import java.math.BigDecimal;

/**
 * STARTER PROBLEM CODE:
 *
 * Demonstrates the flaws BEFORE introducing the Strategy pattern:
 *
 * 1. Flawed Inheritance & Refused Bequest:
 *    Putting fly() in the base Duck class forces all subclasses to inherit flying.
 *    RubberDuck (a bathtub toy) ends up flying across the screen!
 *    DecoyDuck (a wooden carving) inherits both flying and quacking.
 *
 * 2. Maintenance Nightmare & Code Duplication:
 *    Overriding methods in dozens of subclasses to do nothing creates duplication
 *    and prevents dynamic runtime swapping (e.g. giving a duck rocket propulsion).
 *
 * 3. OCP Violation in Business Logic:
 *    NaiveCheckoutService uses brittle switch/if-else checks for payment providers.
 */
public class InflexibleDuckInheritanceProblem {

    public static class InflexibleDuck {
        public String quack() {
            return "Quack";
        }
        public String swim() {
            return "All ducks float, even decoys!";
        }
        public String fly() {
            return "I'm flying!!";
        }
        public String display() {
            return "I am a generic duck";
        }
    }

    // Flawed subclass: RubberDuck inherits fly() from superclass!
    public static class FlawedRubberDuck extends InflexibleDuck {
        @Override
        public String display() {
            return "I'm a yellow rubber duckie";
        }
        @Override
        public String quack() {
            return "Squeak";
        }
        // Rubber ducks shouldn't fly, but inherit InflexibleDuck.fly() unless overridden!
    }

    // OCP violation: hardcoded if/else payment processing
    public static class NaivePaymentProcessor {
        public static String process(String provider, BigDecimal amount) {
            if ("STRIPE".equalsIgnoreCase(provider)) {
                return "Processed $" + amount + " via Stripe";
            } else if ("PAYPAL".equalsIgnoreCase(provider)) {
                return "Processed $" + amount + " via PayPal";
            } else {
                throw new IllegalArgumentException("Unsupported provider: " + provider);
            }
        }
    }
}
