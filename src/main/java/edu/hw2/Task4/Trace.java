package edu.hw2.Task4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trace {
    private Trace() {

    }

    @SuppressWarnings("MultipleStringLiterals")
    public static CallingInfo trace() {
        String caller;
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            StackTraceElement[] arr = e.getStackTrace();
            caller = arr[1].toString();
        }
        StringBuilder reverse = new StringBuilder();
        reverse.append(caller);
        reverse.reverse();
        Pattern pattern = Pattern.compile("\\(.+?\\..+?\\.");
        Matcher matcher = pattern.matcher(reverse);
        while (matcher.find()) {
            caller = reverse.substring(matcher.start() + 1, matcher.end());
        }
        reverse = new StringBuilder();
        reverse.append(caller);
        reverse.reverse();
        caller = reverse.toString();
        String className = caller.split("\\.")[1];
        String methodName = caller.split("\\.")[2];
        return new CallingInfo(className, methodName);
    }
}
