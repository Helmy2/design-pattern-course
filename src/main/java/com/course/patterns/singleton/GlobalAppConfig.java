package com.course.patterns.singleton;

/**
 * STARTER CODE:
 * Enum Singleton (Effective Java Recommendation).
 */
public enum GlobalAppConfig {
    INSTANCE;

    private String environment = "PRODUCTION";

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
