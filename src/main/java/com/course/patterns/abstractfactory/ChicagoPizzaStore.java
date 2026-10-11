package com.course.patterns.abstractfactory;

import com.course.patterns.abstractfactory.ingredients.Ingredients.FrozenClams;
import com.course.patterns.abstractfactory.ingredients.Ingredients.MozzarellaCheese;
import com.course.patterns.abstractfactory.ingredients.Ingredients.PlumTomatoSauce;
import com.course.patterns.abstractfactory.ingredients.Ingredients.ThickCrustDough;

/**
 * STARTER PROBLEM CODE (Carried over from Concept 07 Solution):
 *
 * ChicagoPizzaStore creates Chicago deep-dish pizzas via Factory Method.
 *
 * THE LIMITATION / PROBLEM:
 * Each pizza subclass (ChicagoStyleCheesePizza, ChicagoStyleClamPizza) hardcodes its concrete ingredients.
 */
public class ChicagoPizzaStore extends PizzaStore {

    public static class ChicagoStyleCheesePizza extends Pizza {
        public ChicagoStyleCheesePizza() {
            name = "Chicago Style Deep Dish Cheese Pizza";
        }

        @Override
        public void prepare() {
            // Hardcoded concrete Chicago ingredients (The Problem)
            this.dough = new ThickCrustDough();
            this.sauce = new PlumTomatoSauce();
            this.cheese = new MozzarellaCheese();
        }

        @Override
        public void cut() {
            cutStyle = "square";
        }
    }

    public static class ChicagoStyleClamPizza extends Pizza {
        public ChicagoStyleClamPizza() {
            name = "Chicago Style Deep Dish Clam Pizza";
        }

        @Override
        public void prepare() {
            this.dough = new ThickCrustDough();
            this.sauce = new PlumTomatoSauce();
            this.cheese = new MozzarellaCheese();
            this.clams = new FrozenClams();
        }

        @Override
        public void cut() {
            cutStyle = "square";
        }
    }

    @Override
    protected Pizza createPizza(String item) {
        if ("cheese".equalsIgnoreCase(item)) {
            return new ChicagoStyleCheesePizza();
        } else if ("clam".equalsIgnoreCase(item)) {
            return new ChicagoStyleClamPizza();
        }
        return null;
    }
}
