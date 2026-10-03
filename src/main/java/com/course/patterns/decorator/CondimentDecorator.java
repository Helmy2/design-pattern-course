package com.course.patterns.decorator;

/**
 * Abstract Decorator: Must be interchangeable with Beverage.
 */
public abstract class CondimentDecorator extends Beverage {
    protected Beverage beverage;

    public abstract String getDescription();
}
