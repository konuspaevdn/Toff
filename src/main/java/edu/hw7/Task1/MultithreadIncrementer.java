package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class MultithreadIncrementer {
    public MultithreadIncrementer() {
    }

    private final AtomicInteger counter = new AtomicInteger(0);

    public int increment() {
        return counter.getAndIncrement();
    }

    public int get() {
        return counter.get();
    }

    public AtomicInteger getStorage() {
        return counter;
    }
}
