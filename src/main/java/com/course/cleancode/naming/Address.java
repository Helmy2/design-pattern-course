package com.course.cleancode.naming;

/**
 * STARTER ANTI-PATTERN: Redundant Context Prefixes
 * Field and getter names repeat the enclosing class context ('addrStreet', 'addrCity', 'addrZipCode').
 */
public class Address {
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
