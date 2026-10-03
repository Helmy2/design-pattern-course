package com.course.patterns.singleton;

/**
 * STARTER CODE:
 * TODO: Implement Thread-Safe Double-Checked Locking with volatile and reflection guard.
 */
public class DatabaseConnectionPool {

    // TODO: Step 1 - Mark instance as volatile to prevent instruction reordering
    private static DatabaseConnectionPool instance;

    private DatabaseConnectionPool() {
        // TODO: Step 2 - Add reflection guard
    }

    public static DatabaseConnectionPool getInstance() {
        // TODO: Step 3 - Implement Double-Checked Locking
        if (instance == null) {
            instance = new DatabaseConnectionPool();
        }
        return instance;
    }
}
