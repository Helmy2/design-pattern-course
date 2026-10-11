package com.course.patterns.factorymethod;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FactoryMethodTest {

    @Test
    @DisplayName("Verify monolithic PizzaStore works for simple hardcoded cases")
    void testPizzaStore() {
        PizzaStore store = new PizzaStore();
        Pizza pizza = store.orderPizza("cheese");
        assertThat(pizza).isNotNull();
        assertThat(pizza.getName()).isEqualTo("Generic Cheese Pizza");
    }

    @Test
    @DisplayName("Verify monolithic PizzaStore cannot handle regional styles without modification (OCP violation)")
    void testPizzaStoreRegionalLimitation() {
        PizzaStore store = new PizzaStore();
        Pizza nyPizza = store.orderPizza("ny-cheese");
        Pizza chicagoPizza = store.orderPizza("chicago-deep-dish");

        // Returns null because PizzaStore is hardcoded and inflexible
        assertThat(nyPizza).isNull();
        assertThat(chicagoPizza).isNull();
    }
}
