package com.course.patterns.factorymethod;

/**
 * STARTER PROBLEM CODE:
 *
 * Demonstrates the architectural problems BEFORE introducing the Factory Method pattern:
 *
 * 1. Tight Coupling via 'new':
 *    orderPizza() hardcodes concrete 'new' instantiations for every pizza type.
 *    Client code is directly bound to concrete product implementations.
 *
 * 2. Violation of the Open/Closed Principle (OCP):
 *    Every time a new pizza type or regional variation is introduced,
 *    NaivePizzaStore must be modified and recompiled.
 *
 * 3. Franchise Inconsistency & Lack of Regional Flexibility:
 *    Opening regional franchises (NY, Chicago) requires either:
 *    - Duplicating the entire preparation lifecycle (prepare, bake, cut, box), or
 *    - Polluting orderPizza() with nested regional conditionals.
 */
public class NaivePizzaStore {

    public static class CheesePizza extends Pizza {
        public CheesePizza() {
            this.name = "Generic Cheese Pizza";
        }
    }

    public static class PepperoniPizza extends Pizza {
        public PepperoniPizza() {
            this.name = "Generic Pepperoni Pizza";
        }
    }

    public Pizza orderPizza(String type) {
        Pizza pizza;

        // Code that VARIES as menu items change:
        if ("cheese".equalsIgnoreCase(type)) {
            pizza = new CheesePizza();
        } else if ("pepperoni".equalsIgnoreCase(type)) {
            pizza = new PepperoniPizza();
        } else {
            return null;
        }

        // Code that STAYS THE SAME (preparation lifecycle):
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}
