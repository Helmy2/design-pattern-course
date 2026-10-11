package com.course.patterns.abstractfactory;

import com.course.patterns.abstractfactory.ingredients.Ingredients.Cheese;
import com.course.patterns.abstractfactory.ingredients.Ingredients.Clams;
import com.course.patterns.abstractfactory.ingredients.Ingredients.Dough;
import com.course.patterns.abstractfactory.ingredients.Ingredients.FrozenClams;
import com.course.patterns.abstractfactory.ingredients.Ingredients.MozzarellaCheese;
import com.course.patterns.abstractfactory.ingredients.Ingredients.PlumTomatoSauce;
import com.course.patterns.abstractfactory.ingredients.Ingredients.Sauce;
import com.course.patterns.abstractfactory.ingredients.Ingredients.ThickCrustDough;

/**
 * CLEAN CODE SOLUTION:
 * Concrete Factory responsible for producing the authentic Chicago ingredient family.
 */
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    @Override
    public Clams createClams() {
        return new FrozenClams();
    }
}
