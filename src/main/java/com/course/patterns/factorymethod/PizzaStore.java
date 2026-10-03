package com.course.patterns.factorymethod;

/**
 * THE FACTORY METHOD PATTERN:
 * Abstract Creator declaring the factory method.
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

    // The Factory Method: deferred to regional subclasses
    protected abstract Pizza createPizza(String type);
}
