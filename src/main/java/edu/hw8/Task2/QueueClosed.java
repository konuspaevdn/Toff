package edu.hw8.Task2;

public class QueueClosed implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException("Hey, it's over!");
    }
}
