package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BackwardIterator<E> implements Iterator<E> {
    private final Stack<E> backwardCollection = new Stack<>();

    public BackwardIterator(Collection<E> collection) {
        for (E e : collection) {
            backwardCollection.push(e);
        }
    }

    @Override
    public boolean hasNext() {
        return !backwardCollection.empty();
    }

    @Override
    public E next() {
        if (backwardCollection.empty()) {
            throw new NoSuchElementException();
        }
        return backwardCollection.pop();
    }
}
