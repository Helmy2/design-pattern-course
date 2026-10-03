package com.course.cleancode.smells;

/**
 * CLEAN CODE SOLUTION:
 * Replaces Primitive Obsession with Domain Value Objects.
 * Validation occurs within compact constructors, ensuring invalid state cannot enter the domain.
 */
public class UserRegistrationService {

    public record Email(String value) {
        public Email {
            if (value == null || !value.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                throw new IllegalArgumentException("Invalid email format: " + value);
            }
        }
    }

    public record PhoneNumber(String value) {
        public PhoneNumber {
            if (value == null || !value.matches("^\\+?[0-9]{10,13}$")) {
                throw new IllegalArgumentException("Invalid phone number format: " + value);
            }
        }
    }

    public boolean registerUser(Email email, PhoneNumber phoneNumber) {
        // Business logic proceeds safely without defensive checks
        return email != null && phoneNumber != null;
    }
}
