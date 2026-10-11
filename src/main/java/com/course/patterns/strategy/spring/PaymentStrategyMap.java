package com.course.patterns.strategy.spring;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * CLEAN CODE SOLUTION:
 * Modern Spring Boot Strategy Map:
 * Injects List<PaymentStrategy> and builds a dispatch Map<PaymentProvider, PaymentStrategy>.
 * Eliminates if/else chains completely (O(1) dispatch).
 */
public class PaymentStrategyMap {

    public enum PaymentProvider {
        STRIPE, PAYPAL
    }

    public interface PaymentStrategy {
        PaymentProvider getProvider();
        String process(BigDecimal amount);
    }

    public static class StripePaymentStrategy implements PaymentStrategy {
        @Override public PaymentProvider getProvider() { return PaymentProvider.STRIPE; }
        @Override public String process(BigDecimal amount) { return "Processed $" + amount + " via Stripe"; }
    }

    public static class PayPalPaymentStrategy implements PaymentStrategy {
        @Override public PaymentProvider getProvider() { return PaymentProvider.PAYPAL; }
        @Override public String process(BigDecimal amount) { return "Processed $" + amount + " via PayPal"; }
    }

    public static class CheckoutService {
        private final Map<PaymentProvider, PaymentStrategy> strategyMap;

        public CheckoutService(List<PaymentStrategy> strategies) {
            this.strategyMap = strategies.stream()
                    .collect(Collectors.toMap(PaymentStrategy::getProvider, Function.identity()));
        }

        public String checkout(PaymentProvider provider, BigDecimal amount) {
            PaymentStrategy strategy = strategyMap.get(provider);
            if (strategy == null) {
                throw new IllegalArgumentException("Unsupported payment provider: " + provider);
            }
            return strategy.process(amount);
        }
    }
}
