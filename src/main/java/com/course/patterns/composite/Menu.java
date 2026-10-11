package com.course.patterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * CLEAN CODE SOLUTION:
 * Composite Node in GoF Composite Pattern:
 * - Implements child-management operations (add, remove, getChild).
 * - Implements polymorphic recursion in print(): traverses every child component seamlessly,
 *   regardless of whether the child is a Leaf (MenuItem) or another Composite (Sub-menu).
 */
public class Menu extends MenuComponent {

    private final List<MenuComponent> menuComponents = new ArrayList<>();
    private final String name;
    private final String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void print(StringBuilder out) {
        out.append("\n").append(getName()).append(", ").append(getDescription()).append("\n");
        out.append("---------------------\n");

        for (MenuComponent component : menuComponents) {
            component.print(out);
        }
    }
}
