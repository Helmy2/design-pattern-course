package com.course.patterns.factorymethod;

/**
 * STARTER PROBLEM CODE:
 *
 * PizzaStore before applying Factory Method:
 * 1. Hardcodes concrete 'new' instantiations directly inside orderPizza().
 * 2. Directly coupled to specific concrete pizza products.
 * 3. Violates OCP: any new pizza type requires modifying PizzaStore.
 * 4. Cannot support regional franchises without duplicating preparation logic.
 */
public class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza;

        // Code that VARIES as menu items change:
        if ("cheese".equalsIgnoreCase(type)) {
            pizza = new Pizza.GenericCheesePizza();
        } else if ("pepperoni".equalsIgnoreCase(type)) {
            pizza = new Pizza.GenericPepperoniPizza();
        } else {
            return null;
        }

        // Code that STAYS THE SAME (preparation lifecycle):
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}
