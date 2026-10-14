package com.course.cleancode.functions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * STARTER ANTI-PATTERN: Command-Query Separation (CQS) Violation
 * checkAndEvictIfExpired mutates state (evicts entry) AND queries expiration status simultaneously.
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
