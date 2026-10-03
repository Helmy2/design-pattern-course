package com.course.patterns.composite.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * STARTER PROBLEM CODE:
 *
 * Demonstrates the flaws BEFORE introducing the Composite pattern:
 *
 * 1. Lack of Uniformity:
 *    Items and sub-menus are completely separate types (FlatMenuItem vs NestedSubMenu).
 *
 * 2. Inflexible Client Code:
 *    Client code must maintain separate collections and write bespoke nested loops.
 *    If a sub-menu contains another sub-sub-menu (arbitrary nesting), the client
 *    code breaks unless rewritten with ad-hoc recursion.
 */
public class InflexibleMenuHierarchyProblem {

    public static class FlatMenuItem {
        private final String name;
        private final double price;

        public FlatMenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
    }

    public static class NestedSubMenu {
        private final String title;
        private final List<FlatMenuItem> items = new ArrayList<>();

        public NestedSubMenu(String title) {
            this.title = title;
        }

        public void addItem(FlatMenuItem item) {
            items.add(item);
        }

        public String getTitle() { return title; }
        public List<FlatMenuItem> getItems() { return items; }
    }

    // Client unable to treat items and sub-menus uniformly:
    public static class RigidMenuPrinter {
        public static String printMenus(List<FlatMenuItem> flatItems, List<NestedSubMenu> subMenus) {
            StringBuilder sb = new StringBuilder();
            // Separate loop for flat items:
            for (FlatMenuItem item : flatItems) {
                sb.append(item.getName()).append(": ").append(item.getPrice()).append("\n");
            }
            // Separate loop for submenus:
            for (NestedSubMenu sub : subMenus) {
                sb.append("--- ").append(sub.getTitle()).append(" ---\n");
                for (FlatMenuItem item : sub.getItems()) {
                    sb.append("  ").append(item.getName()).append(": ").append(item.getPrice()).append("\n");
                }
            }
            return sb.toString();
        }
    }
}
