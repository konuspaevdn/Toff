package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;

public class ThreadPoolWorker implements Runnable {
    private final BlockingQueue<Runnable> tasks;

    public ThreadPoolWorker(BlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (true) {
            try {
                var task = tasks.take();
                if (task instanceof QueueClosed) {
                    return;
                }
                task.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
