package edu.hw1;

public class Task2 {
    private Task2() {

    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(int a) {
        int num = a;
        int count = 0;
        do {
            num /= 10;
            ++count;
        } while (num != 0);
        return count;
    }
}
