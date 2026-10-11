package com.course.patterns.strategy;

import java.math.BigDecimal;

/**
 * STARTER ANTI-PATTERN: OCP Violation in Business Logic
 * Uses brittle if/else checks for payment providers.
 * Adding a new provider forces editing this method, violating the Open/Closed Principle.
 */
public class PaymentProcessor {

    public String process(String provider, BigDecimal amount) {
        if ("STRIPE".equalsIgnoreCase(provider)) {
            return "Processed $" + amount + " via Stripe";
        } else if ("PAYPAL".equalsIgnoreCase(provider)) {
            return "Processed $" + amount + " via PayPal";
        } else {
            throw new IllegalArgumentException("Unsupported payment provider: " + provider);
        }
    }
}
