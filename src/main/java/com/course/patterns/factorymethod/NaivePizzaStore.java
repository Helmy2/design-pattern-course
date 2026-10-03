package com.course.patterns.factorymethod;

/**
 * STARTER CODE:
 * Naive Pizza Store: Concrete coupling via 'new' directly inside orderPizza().
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

        // Code that STAYS THE SAME:
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}
