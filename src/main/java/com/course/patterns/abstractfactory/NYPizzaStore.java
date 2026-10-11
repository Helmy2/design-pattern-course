package com.course.patterns.abstractfactory;

/**
 * CLEAN CODE SOLUTION:
 * Concrete Creator for New York regional franchise.
 * Composes NYPizzaIngredientFactory and passes it into generic Pizza instances.
 */
public class NYPizzaStore extends PizzaStore {

    private final PizzaIngredientFactory ingredientFactory;

    public NYPizzaStore() {
        this.ingredientFactory = new NYPizzaIngredientFactory();
    }

    @Override
    protected Pizza createPizza(String item) {
        Pizza pizza = null;

        if ("cheese".equalsIgnoreCase(item)) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        } else if ("clam".equalsIgnoreCase(item)) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("New York Style Clam Pizza");
        }

        return pizza;
    }
}
