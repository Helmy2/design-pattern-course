package com.course.patterns.composite;

/**
 * The Client:
 * Interacts only with the root MenuComponent abstraction.
 * Completely unaware of whether an item is a leaf or a composite.
 */
public class Waitress {

    private final MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public String printMenu() {
        StringBuilder out = new StringBuilder();
        allMenus.print(out);
        return out.toString();
    }
}
