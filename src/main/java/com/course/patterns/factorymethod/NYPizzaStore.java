package com.course.patterns.factorymethod;

/**
 * STARTER CODE:
 * TODO: Implement NYPizzaStore creating NY-style pizzas.
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
        // TODO: Step 1 - Return NYStyleCheesePizza or NYStylePepperoniPizza
        return null;
    }
}
