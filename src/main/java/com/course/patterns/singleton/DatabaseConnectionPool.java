package com.course.patterns.singleton;

/**
 * CLEAN CODE SOLUTION:
 * Thread-Safe Double-Checked Locking Singleton:
 * 1. 'volatile' keyword prevents instruction reordering hazards.
 * 2. 'synchronized' block is only acquired on the first uninitialized pass.
 * 3. Guard against reflection instantiation attack in the private constructor.
 */
public class DatabaseConnectionPool {

    private static volatile DatabaseConnectionPool instance;

    private DatabaseConnectionPool() {
        if (instance != null) {
            throw new IllegalStateException("DatabaseConnectionPool instance already initialized");
        }
    }

    public static DatabaseConnectionPool getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnectionPool.class) {
                if (instance == null) {
                    instance = new DatabaseConnectionPool();
                }
            }
        }
        return instance;
    }
}
