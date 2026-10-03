package com.course.patterns.proxy.problem;

import com.course.patterns.proxy.Person;
import com.course.patterns.proxy.PersonImpl;

/**
 * STARTER PROBLEM CODE:
 *
 * Demonstrates the security flaws BEFORE introducing the Protection Proxy pattern:
 *
 * 1. Self-Rating Manipulation:
 *    Without protection, a user can directly invoke setGeekRating(10) on their own profile,
 *    tampering with the integrity of the dating rating system.
 *
 * 2. Profile Vandalism:
 *    Without protection, other non-owner users can overwrite another person's personal
 *    interests and details directly via setInterests(...).
 */
public class UnprotectedAccessProblem {

    public static Person createVulnerableProfile(String name, String interests) {
        Person person = new PersonImpl();
        person.setName(name);
        person.setInterests(interests);
        return person;
    }
}
