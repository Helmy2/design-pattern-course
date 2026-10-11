package com.course.patterns.abstractfactory;

import com.course.patterns.abstractfactory.ingredients.Ingredients.Cheese;
import com.course.patterns.abstractfactory.ingredients.Ingredients.Clams;
import com.course.patterns.abstractfactory.ingredients.Ingredients.Dough;
import com.course.patterns.abstractfactory.ingredients.Ingredients.Sauce;

/**
 * THE ABSTRACT FACTORY INTERFACE:
 * Declares creation methods for each member of the ingredient family.
 */
public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Clams createClams();
}
