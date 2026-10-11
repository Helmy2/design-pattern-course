package com.course.patterns.abstractfactory;

/**
 * THE FACTORY METHOD PATTERN (From Concept 07 Solution):
 * Abstract Creator declaring orderPizza() template method and createPizza() factory method.
 */
public abstract class PizzaStore {

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

    protected abstract Pizza createPizza(String type);
}
