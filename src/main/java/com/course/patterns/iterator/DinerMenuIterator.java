package com.course.patterns.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CLEAN CODE SOLUTION:
 * Concrete Iterator for fixed-size arrays:
 * Encapsulates array traversal, index management, and skips uninitialized slots cleanly.
 */
public class DinerMenuIterator implements Iterator<MenuItem> {

    private final MenuItem[] list;
    private int position = 0;

    public DinerMenuIterator(MenuItem[] list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return position < list.length && list[position] != null;
    }

    @Override
    public MenuItem next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more items in diner menu");
        }
        MenuItem menuItem = list[position];
        position++;
        return menuItem;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove is not supported");
    }
}
