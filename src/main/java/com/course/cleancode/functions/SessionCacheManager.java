package com.course.cleancode.functions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * CLEAN CODE SOLUTION:
 * Command-Query Separation (CQS):
 * - isExpired(key) is a query: reads state without side effects
 * - evictSession(key) is a command: mutates state, returns void
 */
public class SessionCacheManager {

    public record Session(String sessionId, Instant expiresAt) {
        public boolean isExpired() {
            return Instant.now().isAfter(expiresAt);
        }
    }

    private final Map<String, Session> cache = new HashMap<>();

    public void addSession(String key, Session session) {
        cache.put(key, session);
    }

    // Query: reads state, zero side effects
    public boolean isExpired(String sessionKey) {
        Session session = cache.get(sessionKey);
        return session != null && session.isExpired();
    }

    // Command: mutates state, returns void
    public void evictSession(String sessionKey) {
        cache.remove(sessionKey);
    }

    public boolean contains(String sessionKey) {
        return cache.containsKey(sessionKey);
    }
}
