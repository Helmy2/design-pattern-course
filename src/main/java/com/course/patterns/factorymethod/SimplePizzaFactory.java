package com.course.patterns.factorymethod;

/**
 * CLEAN CODE SOLUTION:
 * Simple Factory (Programming Idiom)
 *
 * Encapsulates instantiation in a standalone factory object.
 * Any client needing pizzas can reuse this factory, localizing the 'new' keyword to one place.
 */
public class SimplePizzaFactory {

    public Pizza createPizza(String type) {
        if ("cheese".equalsIgnoreCase(type)) {
            return new Pizza.GenericCheesePizza();
        } else if ("pepperoni".equalsIgnoreCase(type)) {
            return new Pizza.GenericPepperoniPizza();
        }
        return null;
    }
}
