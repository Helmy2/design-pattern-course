package com.course.patterns.factorymethod;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FactoryMethodTest {

    @Test
    @DisplayName("Verify NaivePizzaStore works for simple hardcoded cases")
    void testNaivePizzaStore() {
        NaivePizzaStore store = new NaivePizzaStore();
        Pizza pizza = store.orderPizza("cheese");
        assertThat(pizza).isNotNull();
        assertThat(pizza.getName()).isEqualTo("Generic Cheese Pizza");
    }

    @Test
    @DisplayName("Verify NaivePizzaStore cannot handle regional styles without modification (OCP violation)")
    void testNaivePizzaStoreRegionalLimitation() {
        NaivePizzaStore store = new NaivePizzaStore();
        Pizza nyPizza = store.orderPizza("ny-cheese");
        Pizza chicagoPizza = store.orderPizza("chicago-deep-dish");

        // Returns null because NaivePizzaStore is hardcoded and inflexible
        assertThat(nyPizza).isNull();
        assertThat(chicagoPizza).isNull();
    }

    @Test
    @DisplayName("Verify SimplePizzaFactory encapsulates instantiation but lacks franchise extensibility")
    void testSimplePizzaFactory() {
        SimplePizzaFactory factory = new SimplePizzaFactory();
        Pizza pizza = factory.createPizza("pepperoni");
        assertThat(pizza).isNotNull();
        assertThat(pizza.getName()).isEqualTo("Generic Pepperoni Pizza");
    }
}
