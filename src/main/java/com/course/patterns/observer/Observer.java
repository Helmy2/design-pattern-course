package com.course.patterns.observer;

/**
 * CLEAN CODE SOLUTION:
 * Observer Interface (Pull Architecture):
 * Parameterless update() notifies observers of state change,
 * allowing each observer to pull only the exact metrics it needs via getters on the Subject.
 */
public interface Observer {
    void update();
}
