package com.course.patterns.iterator.problem;

import com.course.patterns.iterator.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * STARTER PROBLEM CODE:
 *
 * Demonstrates the flaws BEFORE introducing the Iterator pattern:
 *
 * 1. Leaking Internal Representations:
 *    Mel's Diner uses an array (MenuItem[]), while Lou's Pancake House uses a List (List<MenuItem>).
 *    The client (NaiveWaitress) must know the internal storage structures of both restaurants.
 *
 * 2. Incompatible Traversal Loops:
 *    The client is forced to write separate, brittle loops (index and bounds checking for arrays,
 *    iterator/for-each for lists).
 *
 * 3. Fragility & OCP/SRP Violations:
 *    If Mel changes the Diner collection to a Map or Set, client code breaks.
 */
public class HeterogeneousCollectionProblem {

    public static class NaivePancakeHouse {
        private final List<MenuItem> menuItems = List.of(
                new MenuItem("Pancakes", "Fluffy pancakes", true, 2.99),
                new MenuItem("Waffles", "Belgian waffles", true, 3.59)
        );

        public List<MenuItem> getMenuItems() {
            return menuItems;
        }
    }

    public static class NaiveDiner {
        private final MenuItem[] menuItems = new MenuItem[]{
                new MenuItem("Burger", "Cheeseburger with fries", false, 6.99),
                new MenuItem("Soup", "Tomato soup", true, 3.29),
                null // Unfilled array slots!
        };

        public MenuItem[] getMenuItems() {
            return menuItems;
        }
    }

    public static class NaiveWaitress {
        public static List<String> getAllItemsNaive(NaivePancakeHouse pancakeHouse, NaiveDiner diner) {
            List<String> names = new ArrayList<>();

            // Loop 1: specialized for List
            List<MenuItem> pancakeItems = pancakeHouse.getMenuItems();
            for (MenuItem item : pancakeItems) {
                names.add(item.name());
            }

            // Loop 2: specialized for Array with manual null and bounds checks
            MenuItem[] dinerItems = diner.getMenuItems();
            for (int i = 0; i < dinerItems.length; i++) {
                if (dinerItems[i] != null) {
                    names.add(dinerItems[i].name());
                }
            }

            return names;
        }
    }
}
