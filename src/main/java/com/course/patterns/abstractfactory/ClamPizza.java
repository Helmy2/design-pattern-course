package com.course.patterns.abstractfactory;

/**
 * CLEAN CODE SOLUTION:
 * ClamPizza receives an abstract factory and gathers ingredients dynamically in prepare().
 * We no longer need separate NYStyleClamPizza or ChicagoStyleClamPizza classes!
 */
public class ClamPizza extends Pizza {
    private final PizzaIngredientFactory ingredientFactory;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        this.dough = ingredientFactory.createDough();
        this.sauce = ingredientFactory.createSauce();
        this.cheese = ingredientFactory.createCheese();
        this.clams = ingredientFactory.createClams();
    }
}
