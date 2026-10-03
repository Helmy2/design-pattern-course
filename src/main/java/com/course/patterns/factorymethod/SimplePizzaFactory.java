package com.course.patterns.factorymethod;

/**
 * WARM-UP IDIOM:
 * Simple Pizza Factory: Encapsulates instantiation in a standalone helper.
 */
public class SimplePizzaFactory {

    public Pizza createPizza(String type) {
        if ("cheese".equalsIgnoreCase(type)) {
            return new NaivePizzaStore.CheesePizza();
        } else if ("pepperoni".equalsIgnoreCase(type)) {
            return new NaivePizzaStore.PepperoniPizza();
        }
        return null;
    }
}
