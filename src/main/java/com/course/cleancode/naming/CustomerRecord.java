package com.course.cleancode.naming;

import java.time.Instant;
import java.util.Set;

/**
 * CLEAN CODE SOLUTION:
 * - Pronounceable, searchable class name
 * - Intent-revealing field names
 * - Named constants replacing magic numbers
 * - Accurate variable names without disinformation
 */
public class CustomerRecord {

    public static final int ROLE_ADMIN = 7;
    public static final int SECONDS_IN_A_DAY = 86400;
    public static final int DEFAULT_SESSION_EXPIRY_SECONDS = 3600;

    private final Instant generationTimestamp;
    private final Instant modificationTimestamp;
    private final String recordId;

    public CustomerRecord(Instant generationTimestamp, Instant modificationTimestamp, String recordId) {
        this.generationTimestamp = generationTimestamp;
        this.modificationTimestamp = modificationTimestamp;
        this.recordId = recordId;
    }

    public Instant getGenerationTimestamp() { return generationTimestamp; }
    public Instant getModificationTimestamp() { return modificationTimestamp; }
    public String getRecordId() { return recordId; }

    public int calculateSessionExpiry(int userRole) {
        if (userRole == ROLE_ADMIN) {
            return SECONDS_IN_A_DAY;
        }
        return DEFAULT_SESSION_EXPIRY_SECONDS;
    }

    public int countActiveUsers(Set<String> activeUsers) {
        int activeUserCount = 0;
        for (String username : activeUsers) {
            if (username != null && !username.isBlank()) {
                activeUserCount++;
            }
        }
        return activeUserCount;
    }
}
