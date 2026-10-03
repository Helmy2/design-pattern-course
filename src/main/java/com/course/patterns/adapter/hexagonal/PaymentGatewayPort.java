package com.course.patterns.adapter.hexagonal;

/**
 * HEXAGONAL ARCHITECTURE - DRIVEN PORT (Domain Layer):
 * Pure domain contract with ZERO third-party library or Spring dependencies.
 */
public interface PaymentGatewayPort {

    record PaymentConfirmation(String transactionId, boolean successful, double amount) {}

    PaymentConfirmation charge(String customerId, double amount);
}
