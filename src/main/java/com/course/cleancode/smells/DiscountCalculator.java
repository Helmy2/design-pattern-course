package com.course.cleancode.smells;

/**
 * CLEAN CODE SOLUTION:
 * Replaces Switch over Type Codes with Polymorphism (Strategy Pattern).
 */
public class DiscountCalculator {

    public interface DiscountPolicy {
        double apply(double amount);
    }

    public static class RegularDiscount implements DiscountPolicy {
        @Override
        public double apply(double amount) {
            return amount;
        }
    }

    public static class PremiumDiscount implements DiscountPolicy {
        @Override
        public double apply(double amount) {
            return amount * 0.90;
        }
    }

    public static class VipDiscount implements DiscountPolicy {
        @Override
        public double apply(double amount) {
            return amount * 0.80;
        }
    }

    public double calculate(DiscountPolicy policy, double amount) {
        return policy.apply(amount);
    }
}
