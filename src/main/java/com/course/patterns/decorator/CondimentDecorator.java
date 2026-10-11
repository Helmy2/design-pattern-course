package com.course.patterns.decorator;

/**
 * CLEAN CODE SOLUTION:
 * Abstract Decorator in GoF Decorator Pattern:
 * Extends Beverage to maintain type conformance (IS-A),
 * while composing a Beverage component (HAS-A).
 */
public abstract class CondimentDecorator extends Beverage {
    protected Beverage beverage;

    public abstract String getDescription();
}
