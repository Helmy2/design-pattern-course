package com.course.cleancode.naming;

/**
 * STARTER CODE:
 * Class name is a verb phrase ('ProcessOrder'), method names are nouns ('transaction()') or vague flags ('flag()').
 */
public class ProcessOrder {
    private boolean expired = false;

    // TODO: Step 4 - Rename method to a clear verb phrase ('processPayment')
    public void transaction(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    // TODO: Step 5 - Rename method to a predicate conforming to standard conventions ('hasExpired')
    public boolean flag() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
