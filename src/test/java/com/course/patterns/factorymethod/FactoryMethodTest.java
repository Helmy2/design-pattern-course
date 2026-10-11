package com.course.patterns.factorymethod;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FactoryMethodTest {

    @Test
    @DisplayName("Verify SimplePizzaFactory creates generic pizzas directly")
    void testSimplePizzaFactory() {
        SimplePizzaFactory factory = new SimplePizzaFactory();
        Pizza pizza = factory.createPizza("cheese");

        assertThat(pizza).isNotNull();
        assertThat(pizza.getName()).isEqualTo("Generic Cheese Pizza");
    }

    @Test
    @DisplayName("Verify PizzaStore delegates creation to SimplePizzaFactory and executes lifecycle")
    void testPizzaStoreWithSimpleFactory() {
        SimplePizzaFactory factory = new SimplePizzaFactory();
        PizzaStore store = new PizzaStore(factory);

        Pizza pizza = store.orderPizza("pepperoni");
        assertThat(pizza).isNotNull();
        assertThat(pizza.getName()).isEqualTo("Generic Pepperoni Pizza");
    }

    @Test
    @DisplayName("Verify PizzaStore throws IllegalArgumentException on unknown pizza type")
    void testUnknownPizzaType() {
        SimplePizzaFactory factory = new SimplePizzaFactory();
        PizzaStore store = new PizzaStore(factory);

        assertThatThrownBy(() -> store.orderPizza("hawaiian"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unknown pizza type");
    }

    @Test
    @DisplayName("Limitation of Simple Factory: Cannot accommodate regional franchises without duplicating factories or losing franchisor control")
    void testSimpleFactoryLimitation() {
        SimplePizzaFactory factory = new SimplePizzaFactory();
        PizzaStore store = new PizzaStore(factory);

        // A single SimplePizzaFactory cannot satisfy regional New York or Chicago specialities
        assertThatThrownBy(() -> store.orderPizza("ny-cheese"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
