package com.course.cleancode.smells;

/**
 * STARTER CODE:
 * Bloater Smell: Primitive Obsession.
 * Methods take raw Strings for email and phone numbers without type safety.
 */
public class UserRegistrationService {

    public boolean registerUser(String email, String phoneNumber) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (phoneNumber == null || !phoneNumber.matches("^\\+?[0-9]{10,13}$")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        return true;
    }
}
