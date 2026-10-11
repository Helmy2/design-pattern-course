package com.course.patterns.abstractfactory;

/**
 * CLEAN CODE SOLUTION:
 * Concrete Creator for Chicago regional franchise.
 * Composes ChicagoPizzaIngredientFactory and passes it into generic Pizza instances.
 */
public class ChicagoPizzaStore extends PizzaStore {

    private final PizzaIngredientFactory ingredientFactory;

    public ChicagoPizzaStore() {
        this.ingredientFactory = new ChicagoPizzaIngredientFactory();
    }

    @Override
    protected Pizza createPizza(String item) {
        Pizza pizza = null;

        if ("cheese".equalsIgnoreCase(item)) {
            pizza = new CheesePizza(ingredientFactory) {
                @Override
                public void cut() {
                    cutStyle = "square";
                }
            };
            pizza.setName("Chicago Style Deep Dish Cheese Pizza");
        } else if ("clam".equalsIgnoreCase(item)) {
            pizza = new ClamPizza(ingredientFactory) {
                @Override
                public void cut() {
                    cutStyle = "square";
                }
            };
            pizza.setName("Chicago Style Deep Dish Clam Pizza");
        }

        return pizza;
    }
}
