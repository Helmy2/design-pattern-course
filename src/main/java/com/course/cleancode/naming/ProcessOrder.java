package com.course.cleancode.naming;

/**
 * STARTER ANTI-PATTERN: Naming Code Smells
 * - Class name is a verb phrase ('ProcessOrder') instead of a noun phrase
 * - Method names are nouns ('transaction()') instead of action verbs
 * - Predicate method uses vague flag name ('flag()') instead of standard boolean predicate
 */
public class ProcessOrder {
    private boolean expired = false;

    public void transaction(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public boolean flag() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
