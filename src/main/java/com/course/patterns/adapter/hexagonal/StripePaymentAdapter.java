package com.course.patterns.adapter.hexagonal;

/**
 * STARTER CODE:
 * HEXAGONAL ARCHITECTURE - DRIVEN ADAPTER (Infrastructure Layer):
 * Adapts third-party vendor SDK calls to the clean domain PaymentGatewayPort contract.
 */
public class StripePaymentAdapter implements PaymentGatewayPort {

    // Simulated proprietary vendor SDK
    public static class ThirdPartyStripeSdk {
        public record VendorChargeResponse(String id, boolean paid, long amountInCents) {}

        public VendorChargeResponse createCharge(String customerId, long cents, String currency) {
            return new VendorChargeResponse("ch_" + System.currentTimeMillis(), true, cents);
        }
    }

    private final ThirdPartyStripeSdk stripeSdk;

    public StripePaymentAdapter(ThirdPartyStripeSdk stripeSdk) {
        this.stripeSdk = stripeSdk;
    }

    @Override
    public PaymentConfirmation charge(String customerId, double amount) {
        // TODO: Step 3 - Translate domain dollars to vendor cents, invoke SDK, and map to PaymentConfirmation
        long cents = (long) (amount * 100);
        ThirdPartyStripeSdk.VendorChargeResponse response = stripeSdk.createCharge(customerId, cents, "usd");
        return new PaymentConfirmation(response.id(), response.paid(), amount);
    }
}
