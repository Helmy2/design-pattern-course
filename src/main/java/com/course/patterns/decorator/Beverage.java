package com.course.patterns.decorator;

/**
 * CLEAN CODE SOLUTION:
 * Abstract Component in GoF Decorator Pattern:
 * Defines the interface for objects that can have responsibilities added to them dynamically.
 */
public abstract class Beverage {
    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
