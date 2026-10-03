package com.course.patterns.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * STARTER CODE:
 * Custom Iterator traversing a fixed-size array of MenuItem elements.
 */
public class DinerMenuIterator implements Iterator<MenuItem> {

    private final MenuItem[] list;
    private int position = 0;

    public DinerMenuIterator(MenuItem[] list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        // TODO: Step 1 - Return true if position < list.length and list[position] != null
        return position < list.length && list[position] != null;
    }

    @Override
    public MenuItem next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more items in diner menu");
        }
        // TODO: Step 2 - Fetch item and increment position
        MenuItem item = list[position];
        position++;
        return item;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove is not supported");
    }
}
