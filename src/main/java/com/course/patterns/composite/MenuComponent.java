package com.course.patterns.composite;

/**
 * Abstract Component: Defines operations for both Leaves and Composites.
 * Default implementations throw UnsupportedOperationException to prioritize transparency.
 */
public abstract class MenuComponent {

    // Composite operations (child management)
    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    // Leaf & Composite shared operations
    public String getName() {
        throw new UnsupportedOperationException();
    }

    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }

    public void print(StringBuilder out) {
        throw new UnsupportedOperationException();
    }
}
