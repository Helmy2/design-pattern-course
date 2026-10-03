package com.course.patterns.decorator;

/**
 * STARTER CODE:
 * TODO: Implement Whip condiment decorator ($0.10).
 */
public class Whip extends CondimentDecorator {

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription();
    }

    @Override
    public double cost() {
        return beverage.cost();
    }
}
