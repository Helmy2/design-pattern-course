package com.course.capstone.domain;

/**
 * Domain Service: Encapsulates tax rate resolution.
 */
public class TaxPolicy {

    public double getTaxRate(String countryCode) {
        if (countryCode == null) return 0.05;
        return switch (countryCode.toUpperCase()) {
            case "US" -> 0.08;
            case "DE" -> 0.19;
            case "EG" -> 0.14;
            default -> 0.05;
        };
    }
}
