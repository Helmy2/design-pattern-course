package com.course.cleancode.naming;

import java.time.Instant;
import java.util.Set;

/**
 * STARTER ANTI-PATTERN: Naming Code Smells
 * - Unpronounceable class and field names (DtaRcrd102, genymdhms, modymdhms, pszrId)
 * - Disinformation ('userList' is a Set, not a List)
 * - Cryptic single letter variable 'd'
 * - Unsearchable magic numbers (7, 86400, 3600)
 */
public class DtaRcrd102 {
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

    public int calculateSessionExpiry(int userRole) {
        if (userRole == 7) {
            return 86400;
        }
        return 3600;
    }

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
