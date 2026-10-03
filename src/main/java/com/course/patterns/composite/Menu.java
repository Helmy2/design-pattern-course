package com.course.patterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * STARTER CODE:
 * Composite Node:
 * Holds children (leaves and sub-menus) and delegates operations down the tree branch.
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
        // TODO: Step 1 - Add to child list
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        // TODO: Step 2 - Remove from child list
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
        // TODO: Step 3 - Print menu header, then polymorphically iterate and call child.print(out)
        out.append("\n").append(getName()).append(", ").append(getDescription()).append("\n");
        out.append("---------------------\n");
    }
}
