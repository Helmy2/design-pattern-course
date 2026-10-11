package com.course.patterns.adapter.hexagonal;

/**
 * Third-party vendor SDK with proprietary data types and method signatures:
 * - Expects amounts in cents (long), not dollars (double).
 * - Expects currency code.
 * - Returns VendorChargeResponse instead of domain PaymentConfirmation.
 */
public class ThirdPartyStripeSdk {

    public record VendorChargeResponse(String id, boolean paid, long amountInCents) {}

    public VendorChargeResponse createCharge(String customerId, long cents, String currency) {
        return new VendorChargeResponse("ch_" + System.currentTimeMillis(), true, cents);
    }
}
