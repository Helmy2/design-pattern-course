package com.course.cleancode.refactoring;

/**
 * STARTER CODE:
 * Uses temporary variables holding calculated state.
 */
public class PricingCalculator {
    private final int quantity;
    private final double itemPrice;

    public PricingCalculator(int quantity, double itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    // TODO: Step 2 - Replace Temp with Query: extract basePrice() and discountFactor()
    public double getPrice() {
        double basePrice = quantity * itemPrice;
        double discountFactor;

        if (basePrice > 1000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }

        return basePrice * discountFactor;
    }
}
