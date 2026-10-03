package com.course.patterns.singleton;

/**
 * STARTER CODE:
 * Unsafe Lazy Initialization.
 * Multithreaded environments easily create multiple instances!
 */
public class UnsafeConnectionPool {
    private static UnsafeConnectionPool instance;
    private static int instanceCount = 0;

    private UnsafeConnectionPool() {
        instanceCount++;
    }

    public static UnsafeConnectionPool getInstance() {
        if (instance == null) {
            try {
                // Simulating initialization latency that triggers race conditions
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
            instance = new UnsafeConnectionPool();
        }
        return instance;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public static void reset() {
        instance = null;
        instanceCount = 0;
    }
}
