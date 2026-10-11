package com.course.patterns.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * STARTER ANTI-PATTERN: Leaking Internal Collection Representations
 *
 * Flaws:
 * 1. Waitress knows about internal storage details (List vs. MenuItem[]).
 * 2. Forced to write disparate, bespoke loops (for-each for List, index-based with null checks for array).
 * 3. Adding a new restaurant menu (e.g. CafeMenu with a HashMap) forces modifying Waitress with yet another loop.
 */
public class Waitress {

    private final PancakeHouseMenu pancakeHouseMenu;
    private final DinerMenu dinerMenu;

    public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public List<String> getAllItemNames() {
        List<String> names = new ArrayList<>();

        // Loop 1: specific to List<MenuItem>
        List<MenuItem> breakfastItems = pancakeHouseMenu.getMenuItems();
        for (MenuItem item : breakfastItems) {
            names.add(item.name());
        }

        // Loop 2: specific to MenuItem[] array with null and bounds checking
        MenuItem[] dinerItems = dinerMenu.getMenuItems();
        for (int i = 0; i < dinerItems.length; i++) {
            if (dinerItems[i] != null) {
                names.add(dinerItems[i].name());
            }
        }

        return names;
    }
}
