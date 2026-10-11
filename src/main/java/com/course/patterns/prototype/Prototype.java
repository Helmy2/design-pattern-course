package com.course.patterns.prototype;

/**
 * THE PROTOTYPE INTERFACE:
 * Explicit generic prototype interface replacing the flawed java.lang.Cloneable.
 */
public interface Prototype<T> {
    T clone();
}
