package com.course.patterns.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * CLEAN CODE SOLUTION:
 * Prototype Registry / Cache:
 * Stores pre-configured prototypes and serves fresh, isolated clones on demand.
 */
public class SecurityProfileRegistry {

    private final Map<String, NetworkSecurityProfile> registry = new HashMap<>();

    public void register(String key, NetworkSecurityProfile profile) {
        registry.put(key, profile);
    }

    public NetworkSecurityProfile getClone(String key) {
        NetworkSecurityProfile prototype = registry.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("No prototype registered for key: " + key);
        }
        return prototype.clone();
    }
}
