package com.course.cleancode.naming;

/**
 * STARTER CODE:
 * Redundant 'addr*' prefixes inside Address class context.
 */
public class Address {
    // TODO: Step 6 - Eliminate redundant prefixes ('street', 'city', 'zipCode')
    private String addrStreet;
    private String addrCity;
    private String addrZipCode;

    public Address(String addrStreet, String addrCity, String addrZipCode) {
        this.addrStreet = addrStreet;
        this.addrCity = addrCity;
        this.addrZipCode = addrZipCode;
    }

    public String getAddrStreet() { return addrStreet; }
    public String getAddrCity() { return addrCity; }
    public String getAddrZipCode() { return addrZipCode; }
}
