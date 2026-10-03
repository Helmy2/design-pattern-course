package com.course.patterns.factorymethod;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FactoryMethodTest {

    @Test
    @DisplayName("Verify NaivePizzaStore works for simple cases but lacks extensibility")
    void testNaivePizzaStore() {
        NaivePizzaStore store = new NaivePizzaStore();
        Pizza pizza = store.orderPizza("cheese");
        assertThat(pizza.getName()).isEqualTo("Generic Cheese Pizza");
    }

    @Test
    @DisplayName("Verify SimplePizzaFactory creates pizzas")
    void testSimplePizzaFactory() {
        SimplePizzaFactory factory = new SimplePizzaFactory();
        Pizza pizza = factory.createPizza("pepperoni");
        assertThat(pizza.getName()).isEqualTo("Generic Pepperoni Pizza");
    }
}
