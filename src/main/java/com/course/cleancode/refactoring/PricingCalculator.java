package com.course.cleancode.refactoring;

/**
 * CLEAN CODE SOLUTION:
 * Replace Temp with Query:
 * Eliminates local mutable variables by extracting basePrice() and discountFactor() queries.
 */
public class PricingCalculator {
    private final int quantity;
    private final double itemPrice;

    public PricingCalculator(int quantity, double itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public double getPrice() {
        return basePrice() * discountFactor();
    }

    public double basePrice() {
        return quantity * itemPrice;
    }

    public double discountFactor() {
        return basePrice() > 1000 ? 0.95 : 0.98;
    }
}
