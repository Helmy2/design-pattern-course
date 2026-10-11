package com.course.patterns.factorymethod;

/**
 * CLEAN CODE SOLUTION:
 * The Factory Method Pattern
 *
 * Abstract Creator declaring the factory method.
 * The orderPizza() template method coordinates the preparation lifecycle,
 * deferring the decision of which concrete pizza class to instantiate to regional subclasses.
 */
public abstract class PizzaStore {

    // Template method: orchestrates the immutable preparation procedure
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        if (pizza == null) {
            throw new IllegalArgumentException("Unknown pizza type: " + type);
        }

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    // The Factory Method: deferred to regional creator subclasses
    protected abstract Pizza createPizza(String type);
}
