package com.course.patterns.factorymethod;

/**
 * STARTER CODE:
 * TODO: Implement ChicagoPizzaStore creating Chicago-style deep dish pizzas.
 */
public class ChicagoPizzaStore extends PizzaStore {

    public static class ChicagoStyleCheesePizza extends Pizza {
        public ChicagoStyleCheesePizza() {
            name = "Chicago Style Deep Dish Cheese Pizza";
            dough = "Extra Thick Crust Dough";
            sauce = "Plum Tomato Sauce";
            toppings.add("Shredded Mozzarella Cheese");
        }

        @Override
        public void cut() {
            cutStyle = "square";
        }
    }

    public static class ChicagoStylePepperoniPizza extends Pizza {
        public ChicagoStylePepperoniPizza() {
            name = "Chicago Style Deep Dish Pepperoni Pizza";
            dough = "Extra Thick Crust Dough";
            sauce = "Plum Tomato Sauce";
            toppings.add("Sliced Pepperoni");
        }

        @Override
        public void cut() {
            cutStyle = "square";
        }
    }

    @Override
    protected Pizza createPizza(String item) {
        // TODO: Step 2 - Return ChicagoStyleCheesePizza or ChicagoStylePepperoniPizza
        return null;
    }
}
