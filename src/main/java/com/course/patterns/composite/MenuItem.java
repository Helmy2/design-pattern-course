package com.course.patterns.composite;

/**
 * Leaf item representing an individual menu dish.
 */
public class MenuItem {

    private final String name;
    private final String description;
    private final boolean vegetarian;
    private final double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void print(StringBuilder out) {
        out.append("  ").append(getName());
        if (isVegetarian()) {
            out.append("(v)");
        }
        out.append(", ").append(getPrice()).append("\n");
        out.append("     -- ").append(getDescription()).append("\n");
    }
}
