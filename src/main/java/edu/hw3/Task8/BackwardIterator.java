package edu.hw3.Task8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<E> implements Iterator<E> {
    private final List<E> backwardCollection;
    private int idx;

    public BackwardIterator(Collection<E> collection) {
        backwardCollection = new ArrayList<>(collection);
        idx = backwardCollection.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return idx != -1;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return backwardCollection.get(idx--);
    }
}
