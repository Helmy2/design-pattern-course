package com.course.cleancode.naming;

import java.time.Instant;
import java.util.Set;

/**
 * STARTER CODE: Plagued with naming smells.
 * - Unpronounceable class and field names
 * - Disinformation ('userList' is a Set)
 * - Cryptic single letter variable 'd'
 * - Magic numbers (7, 86400)
 */
public class DtaRcrd102 {
    // TODO: Step 1 - Rename fields to meaningful, pronounceable names
    private Instant genymdhms;
    private Instant modymdhms;
    private final String pszrId;

    public DtaRcrd102(Instant genymdhms, Instant modymdhms, String pszrId) {
        this.genymdhms = genymdhms;
        this.modymdhms = modymdhms;
        this.pszrId = pszrId;
    }

    public Instant getGenymdhms() { return genymdhms; }
    public Instant getModymdhms() { return modymdhms; }
    public String getPszrId() { return pszrId; }

    // TODO: Step 2 - Replace magic numbers 7 and 86400 with well-named constants
    public int calculateSessionExpiry(int userRole) {
        if (userRole == 7) {
            return 86400;
        }
        return 3600;
    }

    // TODO: Step 3 - Fix disinformation: 'userList' is actually a Set
    public int countActiveUsers(Set<String> userList) {
        int d = 0;
        for (String temp : userList) {
            if (temp != null && !temp.isBlank()) {
                d++;
            }
        }
        return d;
    }
}
