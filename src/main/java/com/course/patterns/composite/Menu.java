package com.course.patterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * STARTER ANTI-PATTERN: Flat Menu Container
 *
 * Flaws:
 * 1. Lack of Uniformity: Can only hold leaf MenuItem instances, not sub-menus.
 * 2. Inflexible Hierarchy: Cannot support arbitrary tree nesting (e.g. Dessert sub-menu inside Diner menu).
 */
public class Menu {

    private final List<MenuItem> menuItems = new ArrayList<>();
    private final String name;
    private final String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void remove(MenuItem menuItem) {
        menuItems.remove(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void print(StringBuilder out) {
        out.append("\n").append(getName()).append(", ").append(getDescription()).append("\n");
        out.append("---------------------\n");
        for (MenuItem item : menuItems) {
            item.print(out);
        }
    }
}
