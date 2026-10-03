package com.course.patterns.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IteratorPatternTest {

    @Test
    @DisplayName("Verify DinerMenuIterator traverses array correctly without null pointers")
    void testDinerMenuIterator() {
        DinerMenu dinerMenu = new DinerMenu();
        var iterator = dinerMenu.createIterator();

        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next().name()).isEqualTo("Vegetarian BLT");
        assertThat(iterator.next().name()).isEqualTo("BLT");
        assertThat(iterator.next().name()).isEqualTo("Soup of the day");
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    @DisplayName("Problem Demonstration: Client forced to write distinct incompatible traversal loops for Array vs List")
    void testHeterogeneousCollectionProblem() {
        var pancake = new com.course.patterns.iterator.problem.HeterogeneousCollectionProblem.NaivePancakeHouse();
        var diner = new com.course.patterns.iterator.problem.HeterogeneousCollectionProblem.NaiveDiner();

        List<String> items = com.course.patterns.iterator.problem.HeterogeneousCollectionProblem.NaiveWaitress
                .getAllItemsNaive(pancake, diner);

        assertThat(items).containsExactly("Pancakes", "Waffles", "Burger", "Soup");
    }

    @Test
    @DisplayName("Verify Waitress iterates over both ArrayList and Array menus uniformly")
    void testWaitressUniformTraversal() {
        Menu pancakeMenu = new PancakeHouseMenu();
        Menu dinerMenu = new DinerMenu();

        Waitress waitress = new Waitress(pancakeMenu, dinerMenu);
        List<String> allItems = waitress.getAllItemNames();

        assertThat(allItems).containsExactly(
                "K&B's Pancake Breakfast",
                "Regular Pancake Breakfast",
                "Blueberry Pancakes",
                "Vegetarian BLT",
                "BLT",
                "Soup of the day"
        );
    }
}
