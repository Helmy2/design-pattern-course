package com.course.patterns.abstractfactory;

/**
 * CLEAN CODE SOLUTION:
 * CheesePizza receives an abstract factory and gathers ingredients dynamically in prepare().
 * We no longer need separate NYStyleCheesePizza or ChicagoStyleCheesePizza classes!
 */
public class CheesePizza extends Pizza {
    private final PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        this.dough = ingredientFactory.createDough();
        this.sauce = ingredientFactory.createSauce();
        this.cheese = ingredientFactory.createCheese();
    }
}
