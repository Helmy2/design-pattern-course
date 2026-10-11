package com.course.patterns.factorymethod;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FactoryMethodTest {

    @Test
    @DisplayName("Verify NYPizzaStore creates NY-style thin crust pizzas with diagonal cuts")
    void testNYPizzaStore() {
        PizzaStore nyStore = new NYPizzaStore();
        Pizza pizza = nyStore.orderPizza("cheese");

        assertThat(pizza.getName()).isEqualTo("NY Style Sauce and Cheese Pizza");
        assertThat(pizza.getDough()).isEqualTo("Thin Crust Dough");
        assertThat(pizza.getSauce()).isEqualTo("Marinara Sauce");
        assertThat(pizza.getCutStyle()).isEqualTo("diagonal");
        assertThat(pizza.getToppings()).contains("Grated Reggiano Cheese");
    }

    @Test
    @DisplayName("Verify ChicagoPizzaStore creates deep dish pizzas with square cut override")
    void testChicagoPizzaStore() {
        PizzaStore chicagoStore = new ChicagoPizzaStore();
        Pizza pizza = chicagoStore.orderPizza("cheese");

        assertThat(pizza.getName()).isEqualTo("Chicago Style Deep Dish Cheese Pizza");
        assertThat(pizza.getDough()).isEqualTo("Extra Thick Crust Dough");
        assertThat(pizza.getSauce()).isEqualTo("Plum Tomato Sauce");
        assertThat(pizza.getCutStyle()).isEqualTo("square");
        assertThat(pizza.getToppings()).contains("Shredded Mozzarella Cheese");
    }

    @Test
    @DisplayName("Verify orderPizza template method rejects unknown pizza items cleanly")
    void testUnknownPizzaType() {
        PizzaStore store = new NYPizzaStore();
        assertThatThrownBy(() -> store.orderPizza("pineapple"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unknown pizza type");
    }
}
