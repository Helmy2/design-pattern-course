package com.course.patterns.iterator;

import java.util.Iterator;

/**
 * CLEAN CODE SOLUTION:
 * Aggregate Interface:
 * Declares factory method to create an Iterator.
 */
public interface Menu {
    Iterator<MenuItem> createIterator();
}
