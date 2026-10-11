package com.course.patterns.factorymethod;

/**
 * CLEAN CODE SOLUTION:
 * Concrete Creator for Chicago regional franchise.
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
        if ("cheese".equalsIgnoreCase(item)) {
            return new ChicagoStyleCheesePizza();
        } else if ("pepperoni".equalsIgnoreCase(item)) {
            return new ChicagoStylePepperoniPizza();
        }
        return null;
    }
}
