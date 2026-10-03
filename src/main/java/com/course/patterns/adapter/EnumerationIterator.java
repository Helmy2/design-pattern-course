package com.course.patterns.adapter;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Classic JDK Adapter:
 * Adapts legacy java.util.Enumeration to modern java.util.Iterator.
 */
public class EnumerationIterator<T> implements Iterator<T> {

    private final Enumeration<T> enumeration;

    public EnumerationIterator(Enumeration<T> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public T next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove is not supported by legacy Enumeration");
    }
}
