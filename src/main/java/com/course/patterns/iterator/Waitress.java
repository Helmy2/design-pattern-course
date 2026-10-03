package com.course.patterns.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Waitress {

    private final Menu pancakeHouseMenu;
    private final Menu dinerMenu;

    public Waitress(Menu pancakeHouseMenu, Menu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public List<String> getAllItemNames() {
        List<String> names = new ArrayList<>();
        collectItemNames(pancakeHouseMenu.createIterator(), names);
        collectItemNames(dinerMenu.createIterator(), names);
        return names;
    }

    // Unified traversal algorithm decoupling client from ArrayList vs array
    private void collectItemNames(Iterator<MenuItem> iterator, List<String> names) {
        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            names.add(item.name());
        }
    }
}
