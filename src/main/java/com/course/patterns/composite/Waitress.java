package com.course.patterns.composite;

import java.util.List;

/**
 * Starter Client:
 * Must explicitly manage a collection of flat Menu instances.
 * Cannot treat a menu and a menu item uniformly, and cannot handle arbitrary sub-menu nesting.
 */
public class Waitress {

    private final List<Menu> menus;

    public Waitress(List<Menu> menus) {
        this.menus = menus;
    }

    public String printMenu() {
        StringBuilder out = new StringBuilder();
        for (Menu menu : menus) {
            menu.print(out);
        }
        return out.toString();
    }
}
