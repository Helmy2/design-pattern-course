package com.course.patterns.abstractfactory;

import com.course.patterns.abstractfactory.ingredients.Ingredients.FreshClams;
import com.course.patterns.abstractfactory.ingredients.Ingredients.MarinaraSauce;
import com.course.patterns.abstractfactory.ingredients.Ingredients.ReggianoCheese;
import com.course.patterns.abstractfactory.ingredients.Ingredients.ThinCrustDough;

/**
 * STARTER PROBLEM CODE (Carried over from Concept 07 Solution):
 *
 * NYPizzaStore creates NY-style pizzas via Factory Method.
 *
 * THE LIMITATION / PROBLEM:
 * Each pizza subclass (NYStyleCheesePizza, NYStyleClamPizza) hardcodes its concrete ingredients
 * directly in prepare() or constructor.
 *
 * Flaws:
 * 1. Subclass explosion: We need a distinct pizza class for EVERY style in EVERY region.
 * 2. Violates DIP: Pizza classes depend directly on concrete ingredient classes (ThinCrustDough, MarinaraSauce).
 * 3. No shared product family enforcement: Nothing prevents a regional franchise from swapping
 *    in unapproved or mismatched ingredients.
 */
public class NYPizzaStore extends PizzaStore {

    public static class NYStyleCheesePizza extends Pizza {
        public NYStyleCheesePizza() {
            name = "NY Style Sauce and Cheese Pizza";
        }

        @Override
        public void prepare() {
            // Hardcoded concrete New York ingredients (The Problem)
            this.dough = new ThinCrustDough();
            this.sauce = new MarinaraSauce();
            this.cheese = new ReggianoCheese();
        }
    }

    public static class NYStyleClamPizza extends Pizza {
        public NYStyleClamPizza() {
            name = "NY Style Clam Pizza";
        }

        @Override
        public void prepare() {
            this.dough = new ThinCrustDough();
            this.sauce = new MarinaraSauce();
            this.cheese = new ReggianoCheese();
            this.clams = new FreshClams();
        }
    }

    @Override
    protected Pizza createPizza(String item) {
        if ("cheese".equalsIgnoreCase(item)) {
            return new NYStyleCheesePizza();
        } else if ("clam".equalsIgnoreCase(item)) {
            return new NYStyleClamPizza();
        }
        return null;
    }
}
