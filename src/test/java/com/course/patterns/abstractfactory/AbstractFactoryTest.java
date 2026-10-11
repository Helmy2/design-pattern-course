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
    @DisplayName("Verify NY ingredient factory provides authentic NY product family")
    void testNYIngredientFactory() {
        PizzaIngredientFactory nyFactory = new NYPizzaIngredientFactory();
        CheesePizza cheesePizza = new CheesePizza(nyFactory);
        cheesePizza.setName("New York Style Cheese Pizza");
        cheesePizza.prepare();

        assertThat(cheesePizza.getDough().getName()).isEqualTo("Thin Crust Dough");
        assertThat(cheesePizza.getSauce().getName()).isEqualTo("Marinara Sauce");
        assertThat(cheesePizza.getCheese().getName()).isEqualTo("Reggiano Cheese");
    }

    @Test
    @DisplayName("Verify Chicago ingredient factory provides authentic Chicago product family")
    void testChicagoIngredientFactory() {
        PizzaIngredientFactory chicagoFactory = new ChicagoPizzaIngredientFactory();
        ClamPizza clamPizza = new ClamPizza(chicagoFactory);
        clamPizza.setName("Chicago Style Clam Pizza");
        clamPizza.prepare();

        assertThat(clamPizza.getDough().getName()).isEqualTo("Thick Deep Dish Crust Dough");
        assertThat(clamPizza.getSauce().getName()).isEqualTo("Plum Tomato Sauce");
        assertThat(clamPizza.getCheese().getName()).isEqualTo("Shredded Mozzarella Cheese");
        assertThat(clamPizza.getClams().getName()).isEqualTo("Frozen Lake Michigan Clams");
    }

    @Test
    @DisplayName("Verify NYPizzaStore creates NY pizzas using NYIngredientFactory family")
    void testNYPizzaStoreIntegration() {
        PizzaStore nyStore = new NYPizzaStore();
        Pizza pizza = nyStore.orderPizza("cheese");

        assertThat(pizza.getName()).isEqualTo("New York Style Cheese Pizza");
        assertThat(pizza.getDough().getName()).isEqualTo("Thin Crust Dough");
        assertThat(pizza.getSauce().getName()).isEqualTo("Marinara Sauce");
        assertThat(pizza.getCheese().getName()).isEqualTo("Reggiano Cheese");
        assertThat(pizza.getCutStyle()).isEqualTo("diagonal");
    }

    @Test
    @DisplayName("Verify ChicagoPizzaStore creates deep dish pizzas using ChicagoIngredientFactory family")
    void testChicagoPizzaStoreIntegration() {
        PizzaStore chicagoStore = new ChicagoPizzaStore();
        Pizza pizza = chicagoStore.orderPizza("clam");

        assertThat(pizza.getName()).isEqualTo("Chicago Style Deep Dish Clam Pizza");
        assertThat(pizza.getDough().getName()).isEqualTo("Thick Deep Dish Crust Dough");
        assertThat(pizza.getSauce().getName()).isEqualTo("Plum Tomato Sauce");
        assertThat(pizza.getCheese().getName()).isEqualTo("Shredded Mozzarella Cheese");
        assertThat(pizza.getClams().getName()).isEqualTo("Frozen Lake Michigan Clams");
        assertThat(pizza.getCutStyle()).isEqualTo("square");
    }

    @Test
    @DisplayName("Verify unknown pizza type is rejected cleanly")
    void testUnknownPizzaType() {
        PizzaStore store = new NYPizzaStore();
        assertThatThrownBy(() -> store.orderPizza("hawaiian"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unknown pizza type");
    }
}
