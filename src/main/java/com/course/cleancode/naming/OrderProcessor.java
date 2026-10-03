package com.course.cleancode.naming;

/**
 * CLEAN CODE SOLUTION:
 * - Class name is a noun phrase ('OrderProcessor')
 * - Methods are action verbs ('processPayment()') and standard predicates ('hasExpired()')
 */
public class OrderProcessor {
    private boolean expired = false;

    public void processPayment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
    }

    public boolean hasExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
