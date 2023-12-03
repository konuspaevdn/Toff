package edu.hw8.Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool implements ThreadPool {
    private final List<Thread> workers;
    private final BlockingQueue<Runnable> tasks;
    private final Lock lock;
    private boolean closed;
    private final int num;

    public MyThreadPool(int n) {
        workers = new ArrayList<>(n);
        tasks = new LinkedBlockingQueue<>();
        num = n;
        lock = new ReentrantLock();
        closed = false;
    }

    @Override
    public void start() {
        for (int i = 0; i < num; ++i) {
            Thread t = new Thread(new ThreadPoolWorker(tasks), "Worker №" + i);
            workers.add(t);
            t.start();
        }
    }

    @Override
    public void execute(Runnable runnable) throws InterruptedException {
        lock.lock();
        if (closed) {
            return;
        }
        lock.unlock();
        tasks.put(runnable);
    }

    @Override
    public void close() throws InterruptedException {
        lock.lock();
        closed = true;
        lock.unlock();
        for (int i = 0; i < num; ++i) {
            tasks.put(new QueueClosed());
        }
    }
}
