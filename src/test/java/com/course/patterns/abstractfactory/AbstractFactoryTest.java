package com.course.patterns.abstractfactory;

import com.course.patterns.abstractfactory.ingredients.Ingredients;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AbstractFactoryTest {

    @Test
    @DisplayName("Verify ingredient components can be instantiated directly")
    void testDirectIngredients() {
        Ingredients.Dough thin = new Ingredients.ThinCrustDough();
        assertThat(thin.getName()).isEqualTo("Thin Crust Dough");

        Ingredients.Dough thick = new Ingredients.ThickCrustDough();
        assertThat(thick.getName()).isEqualTo("Thick Deep Dish Crust Dough");
    }

    @Test
    @DisplayName("Verify NYPizzaStore creates NY pizzas with hardcoded NY ingredients (Factory Method Baseline)")
    void testNYPizzaStoreFactoryMethodBaseline() {
        PizzaStore store = new NYPizzaStore();
        Pizza pizza = store.orderPizza("cheese");

        assertThat(pizza.getName()).isEqualTo("NY Style Sauce and Cheese Pizza");
        assertThat(pizza.getDough().getName()).isEqualTo("Thin Crust Dough");
        assertThat(pizza.getSauce().getName()).isEqualTo("Marinara Sauce");
        assertThat(pizza.getCheese().getName()).isEqualTo("Reggiano Cheese");
    }

    @Test
    @DisplayName("Verify ChicagoPizzaStore creates deep dish pizzas with hardcoded Chicago ingredients")
    void testChicagoPizzaStoreFactoryMethodBaseline() {
        PizzaStore store = new ChicagoPizzaStore();
        Pizza pizza = store.orderPizza("clam");

        assertThat(pizza.getName()).isEqualTo("Chicago Style Deep Dish Clam Pizza");
        assertThat(pizza.getDough().getName()).isEqualTo("Thick Deep Dish Crust Dough");
        assertThat(pizza.getSauce().getName()).isEqualTo("Plum Tomato Sauce");
        assertThat(pizza.getCheese().getName()).isEqualTo("Shredded Mozzarella Cheese");
        assertThat(pizza.getClams().getName()).isEqualTo("Frozen Lake Michigan Clams");
    }

    @Test
    @DisplayName("Problem Demonstration: Subclass explosion - adding a new region requires duplicating every pizza class")
    void testSubclassExplosionLimitation() {
        PizzaStore store = new NYPizzaStore();
        // A California store or new pizza type cannot reuse CheesePizza because it's hardcoded to NY ingredients
        assertThatThrownBy(() -> store.orderPizza("veggie"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
