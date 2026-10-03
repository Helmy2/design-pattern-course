package com.course.patterns.decorator;

/**
 * STARTER CODE:
 * TODO: Implement Mocha condiment decorator ($0.20).
 */
public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        // TODO: Step 1 - delegate to beverage.getDescription() + ", Mocha"
        return beverage.getDescription();
    }

    @Override
    public double cost() {
        // TODO: Step 2 - delegate to beverage.cost() + 0.20
        return beverage.cost();
    }
}
