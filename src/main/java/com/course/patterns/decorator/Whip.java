package com.course.patterns.decorator;

/**
 * CLEAN CODE SOLUTION:
 * Concrete Condiment Decorator: Whip ($0.10).
 */
public class Whip extends CondimentDecorator {

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }
}
