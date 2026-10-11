package com.course.patterns.observer;

/**
 * CLEAN CODE SOLUTION:
 * Subject Interface in GoF Observer Pattern:
 * Decouples publishers from subscribers.
 */
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
