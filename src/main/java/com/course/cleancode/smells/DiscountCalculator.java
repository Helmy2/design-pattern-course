package com.course.cleancode.smells;

/**
 * STARTER CODE:
 * OO Abuser Smell: Switch over Type Codes.
 * Missed opportunity for polymorphism / Strategy pattern.
 */
public class DiscountCalculator {

    public enum CustomerType {
        REGULAR, PREMIUM, VIP
    }

    public double applyDiscount(CustomerType type, double amount) {
        return switch (type) {
            case REGULAR -> amount;
            case PREMIUM -> amount * 0.90;
            case VIP -> amount * 0.80;
        };
    }
}
