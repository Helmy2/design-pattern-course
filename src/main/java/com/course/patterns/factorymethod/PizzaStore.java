package com.course.patterns.factorymethod;

/**
 * CLEAN CODE SOLUTION:
 * Simple Factory Idiom:
 *
 * PizzaStore delegates concrete instantiation to SimplePizzaFactory via composition (HAS-A).
 * The store now focuses purely on orchestrating the order lifecycle (prepare, bake, cut, box).
 */
public class PizzaStore {

    private final SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza = factory.createPizza(type);
        if (pizza == null) {
            throw new IllegalArgumentException("Unknown pizza type: " + type);
        }

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}
