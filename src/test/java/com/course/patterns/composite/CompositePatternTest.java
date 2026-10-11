package com.course.patterns.composite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CompositePatternTest {

    @Test
    @DisplayName("Verify MenuItem leaf properties and formatting")
    void testMenuItemLeaf() {
        MenuItem item = new MenuItem("Pasta", "Spaghetti with Marinara", true, 3.89);
        StringBuilder out = new StringBuilder();
        item.print(out);

        assertThat(out.toString())
                .contains("Pasta(v), 3.89")
                .contains("Spaghetti with Marinara");
    }

    @Test
    @DisplayName("Verify starter flat Menu prints its direct items")
    void testFlatMenu() {
        Menu pancakeMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
        pancakeMenu.add(new MenuItem("K&B's Pancake Breakfast", "Pancakes with scrambled eggs", true, 2.99));

        StringBuilder out = new StringBuilder();
        pancakeMenu.print(out);

        assertThat(out.toString())
                .contains("PANCAKE HOUSE MENU, Breakfast")
                .contains("K&B's Pancake Breakfast(v), 2.99");
    }

    @Test
    @DisplayName("Problem Demonstration: Flat Menu cannot nest sub-menus (e.g. Dessert menu inside Diner menu)")
    void testFlatMenuNestingLimitation() {
        Menu dinerMenu = new Menu("DINER MENU", "Lunch");
        dinerMenu.add(new MenuItem("Pasta", "Spaghetti with Marinara", true, 3.89));

        Menu dessertMenu = new Menu("DESSERT MENU", "Dessert of course!");
        dessertMenu.add(new MenuItem("Apple Pie", "Apple pie with a flakey crust", true, 1.59));

        // In starter, Menu only accepts MenuItem, NOT another Menu:
        // dinerMenu.add(dessertMenu); // COMPILATION ERROR! dinerMenu.add(MenuItem) cannot take Menu.

        Waitress waitress = new Waitress(List.of(dinerMenu, dessertMenu));
        String output = waitress.printMenu();

        // Menus are forced into a flat sibling list rather than true hierarchical nesting:
        assertThat(output)
                .contains("DINER MENU, Lunch")
                .contains("Pasta(v), 3.89")
                .contains("DESSERT MENU, Dessert of course!")
                .contains("Apple Pie(v), 1.59");
    }
}
