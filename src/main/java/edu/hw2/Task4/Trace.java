package edu.hw2.Task4;

public class Trace {
    private Trace() {

    }

    public static CallingInfo trace(Throwable e) {
        StackTraceElement[] arr = e.getStackTrace();
        StackTraceElement caller = arr[0];
        return new CallingInfo(caller.getClassName(), caller.getMethodName());
    }
}
