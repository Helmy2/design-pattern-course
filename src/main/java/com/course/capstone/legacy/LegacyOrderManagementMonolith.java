package com.course.capstone.legacy;

import java.util.ArrayList;
import java.util.List;

/**
 * STARTER CODE:
 * The Ultimate God Object Monolith.
 * Combines validation, calculation, multiple payment SDKs, email formatting, and logging.
 */
public class LegacyOrderManagementMonolith {

    private final List<String> executionAudit = new ArrayList<>();

    // 8 Primitive Arguments, inline validation, hardcoded conditionals, tight SDK coupling
    public boolean processAndFulfillOrder(
            String orderId,
            String customerEmail,
            String customerCountry,
            double itemPrice,
            int quantity,
            String paymentType,
            boolean isExpressDelivery,
            boolean applyDiscount
    ) {
        // 1. Primitive validation inline
        if (orderId == null || !orderId.startsWith("ORD-")) {
            throw new IllegalArgumentException("Invalid order ID: " + orderId);
        }
        if (customerEmail == null || !customerEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email format: " + customerEmail);
        }
        if (itemPrice <= 0 || quantity <= 0) {
            throw new IllegalArgumentException("Price and quantity must be positive");
        }

        // 2. Complex calculation inline
        double subtotal = itemPrice * quantity;
        if (applyDiscount && quantity > 10) {
            subtotal = subtotal * 0.90; // 10% discount
        }

        // Tax calculation switch over country
        double taxRate = switch (customerCountry.toUpperCase()) {
            case "US" -> 0.08;
            case "DE" -> 0.19;
            case "EG" -> 0.14;
            default -> 0.05;
        };

        double total = subtotal + (subtotal * taxRate);
        if (isExpressDelivery) {
            total += 25.0; // Flat express delivery fee
        }

        // 3. Payment Processing via hardcoded if/else
        boolean paymentSuccess;
        if ("STRIPE".equalsIgnoreCase(paymentType)) {
            executionAudit.add("STRIPE_API_CHARGE:" + (long)(total * 100) + "_CENTS");
            paymentSuccess = true;
        } else if ("PAYPAL".equalsIgnoreCase(paymentType)) {
            executionAudit.add("PAYPAL_API_CAPTURE:" + total + "_USD");
            paymentSuccess = true;
        } else {
            throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }

        // 4. Notification inline
        if (paymentSuccess) {
            executionAudit.add("SMTP_SEND:" + customerEmail + ":TOTAL=" + String.format("%.2f", total));
            executionAudit.add("DB_SAVE_ORDER:" + orderId);
            return true;
        }
        return false;
    }

    public List<String> getExecutionAudit() {
        return List.copyOf(executionAudit);
    }
}
