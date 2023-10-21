package edu.hw1;

public class Task3 {
    private Task3() {

    }

    private static int min(int[] a) {
        int min = a[0];
        for (int i = 1; i < a.length; ++i) {
            min = Math.min(a[i], min);
        }
        return min;
    }

    private static int max(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; ++i) {
            max = Math.max(a[i], max);
        }
        return max;
    }

    public static boolean isNestable(int[] a, int[] b) {
        return min(a) > min(b) && max(a) < max(b);
    }
}
