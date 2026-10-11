package com.course.patterns.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IteratorPatternTest {

    @Test
    @DisplayName("Verify PancakeHouseMenu stores items in a List")
    void testPancakeHouseMenuList() {
        PancakeHouseMenu menu = new PancakeHouseMenu();
        assertThat(menu.getMenuItems()).hasSize(3);
        assertThat(menu.getMenuItems().get(0).name()).isEqualTo("K&B's Pancake Breakfast");
    }

    @Test
    @DisplayName("Verify DinerMenu stores items in a fixed-size array with null slots")
    void testDinerMenuArray() {
        DinerMenu menu = new DinerMenu();
        MenuItem[] items = menu.getMenuItems();
        assertThat(items).hasSize(6);
        assertThat(items[0].name()).isEqualTo("Vegetarian BLT");
        assertThat(items[3]).isNull(); // Unfilled slots
    }

    @Test
    @DisplayName("Problem Demonstration: Waitress forced to handle disparate collections with bespoke loops")
    void testHeterogeneousCollectionProblem() {
        PancakeHouseMenu pancakeMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();

        Waitress waitress = new Waitress(pancakeMenu, dinerMenu);
        List<String> names = waitress.getAllItemNames();

        assertThat(names).containsExactly(
                "K&B's Pancake Breakfast",
                "Regular Pancake Breakfast",
                "Blueberry Pancakes",
                "Vegetarian BLT",
                "BLT",
                "Soup of the day"
        );
    }
}
