package com.course.patterns.composite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CompositePatternTest {

    @Test
    @DisplayName("Verify MenuItem leaf nodes throw UnsupportedOperationException on child management operations")
    void testLeafSafetyTradeoff() {
        MenuComponent pancake = new MenuItem("Pancake", "Fresh pancakes", true, 2.99);

        // Transparency vs Safety: Leaves throw when mutating children
        assertThatThrownBy(() -> pancake.add(new MenuItem("Sub", "Sub", true, 1.0)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Verify MenuItem properties and leaf print")
    void testMenuItemLeaf() {
        MenuItem item = new MenuItem("Pasta", "Spaghetti with Marinara", true, 3.89);
        StringBuilder out = new StringBuilder();
        item.print(out);

        assertThat(out.toString())
                .contains("Pasta(v), 3.89")
                .contains("Spaghetti with Marinara");
    }

    @Test
    @DisplayName("Verify composite tree with top-level menus and nested Dessert sub-menu traversed uniformly by Waitress")
    void testCompositeMenuHierarchy() {
        // 1. Create composites
        MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
        MenuComponent dinerMenu = new Menu("DINER MENU", "Lunch");
        MenuComponent dessertMenu = new Menu("DESSERT MENU", "Dessert of course!");
        MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");

        // 2. Assemble top-level tree
        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinerMenu);

        // 3. Add leaf items
        pancakeHouseMenu.add(new MenuItem("K&B's Pancake Breakfast", "Pancakes with scrambled eggs", true, 2.99));
        dinerMenu.add(new MenuItem("Pasta", "Spaghetti with Marinara", true, 3.89));

        // 4. Nest Dessert sub-menu inside Diner menu!
        dinerMenu.add(dessertMenu);
        dessertMenu.add(new MenuItem("Apple Pie", "Apple pie with a flakey crust", true, 1.59));

        // 5. Client Waitress prints entire tree uniformly
        Waitress waitress = new Waitress(allMenus);
        String printedMenu = waitress.printMenu();

        assertThat(printedMenu)
                .contains("ALL MENUS, All menus combined")
                .contains("PANCAKE HOUSE MENU, Breakfast")
                .contains("K&B's Pancake Breakfast(v), 2.99")
                .contains("DINER MENU, Lunch")
                .contains("Pasta(v), 3.89")
                .contains("DESSERT MENU, Dessert of course!")
                .contains("Apple Pie(v), 1.59");
    }
}
