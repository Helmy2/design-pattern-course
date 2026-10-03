package com.course.cleancode.functions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * STARTER CODE:
 * CQS Violation: checkAndEvictIfExpired mutates state AND queries expiration status simultaneously.
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

    // TODO: Step 3 - Separate command from query:
    // Query: isExpired(key) -> returns boolean, zero mutation
    // Command: evictSession(key) -> removes entry, returns void
    public boolean checkAndEvictIfExpired(String sessionKey) {
        Session session = cache.get(sessionKey);
        if (session != null && session.isExpired()) {
            cache.remove(sessionKey);
            return true;
        }
        return false;
    }

    public boolean contains(String sessionKey) {
        return cache.containsKey(sessionKey);
    }
}
