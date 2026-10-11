package com.course.patterns.factorymethod;

/**
 * CLEAN CODE SOLUTION:
 * Concrete Creator for New York regional franchise.
 */
public class NYPizzaStore extends PizzaStore {

    public static class NYStyleCheesePizza extends Pizza {
        public NYStyleCheesePizza() {
            name = "NY Style Sauce and Cheese Pizza";
            dough = "Thin Crust Dough";
            sauce = "Marinara Sauce";
            toppings.add("Grated Reggiano Cheese");
        }
    }

    public static class NYStylePepperoniPizza extends Pizza {
        public NYStylePepperoniPizza() {
            name = "NY Style Pepperoni Pizza";
            dough = "Thin Crust Dough";
            sauce = "Marinara Sauce";
            toppings.add("Sliced Pepperoni");
        }
    }

    @Override
    protected Pizza createPizza(String item) {
        if ("cheese".equalsIgnoreCase(item)) {
            return new NYStyleCheesePizza();
        } else if ("pepperoni".equalsIgnoreCase(item)) {
            return new NYStylePepperoniPizza();
        }
        return null;
    }
}
