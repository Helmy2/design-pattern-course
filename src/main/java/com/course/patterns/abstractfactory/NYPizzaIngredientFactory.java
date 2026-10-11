package com.course.patterns.abstractfactory;

import com.course.patterns.abstractfactory.ingredients.Ingredients.Cheese;
import com.course.patterns.abstractfactory.ingredients.Ingredients.Clams;
import com.course.patterns.abstractfactory.ingredients.Ingredients.Dough;
import com.course.patterns.abstractfactory.ingredients.Ingredients.FreshClams;
import com.course.patterns.abstractfactory.ingredients.Ingredients.MarinaraSauce;
import com.course.patterns.abstractfactory.ingredients.Ingredients.ReggianoCheese;
import com.course.patterns.abstractfactory.ingredients.Ingredients.Sauce;
import com.course.patterns.abstractfactory.ingredients.Ingredients.ThinCrustDough;

/**
 * CLEAN CODE SOLUTION:
 * Concrete Factory responsible for producing the authentic New York ingredient family.
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Clams createClams() {
        return new FreshClams();
    }
}
