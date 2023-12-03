package edu.hw8.Task2;

public class MyFixedThreadPool {
    private MyFixedThreadPool() {
    }

    public static MyThreadPool create(int n) {
        var mtp = new MyThreadPool(n);
        mtp.start();
        return mtp;
    }
}
