package com.course.patterns.singleton;

/**
 * CLEAN CODE SOLUTION:
 * Initialization-on-demand Holder Idiom:
 * The static inner Holder class is not loaded until getInstance() is called,
 * guaranteeing lazy evaluation and thread safety via JVM class initialization locks.
 */
public class LazyRegistry {

    private LazyRegistry() {}

    private static class Holder {
        private static final LazyRegistry INSTANCE = new LazyRegistry();
    }

    public static LazyRegistry getInstance() {
        return Holder.INSTANCE;
    }
}
