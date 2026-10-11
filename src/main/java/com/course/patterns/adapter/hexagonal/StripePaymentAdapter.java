package com.course.patterns.adapter.hexagonal;

/**
 * CLEAN CODE SOLUTION:
 * HEXAGONAL ARCHITECTURE - DRIVEN ADAPTER (Infrastructure Layer):
 * Adapts third-party vendor SDK calls to the clean domain PaymentGatewayPort contract.
 * Translates domain dollars to vendor cents and vendor response to domain model.
 */
public class StripePaymentAdapter implements PaymentGatewayPort {

    private final ThirdPartyStripeSdk stripeSdk;

    public StripePaymentAdapter(ThirdPartyStripeSdk stripeSdk) {
        this.stripeSdk = stripeSdk;
    }

    @Override
    public PaymentConfirmation charge(String customerId, double amount) {
        long cents = (long) (amount * 100);
        ThirdPartyStripeSdk.VendorChargeResponse response = stripeSdk.createCharge(customerId, cents, "usd");
        return new PaymentConfirmation(response.id(), response.paid(), amount);
    }
}
