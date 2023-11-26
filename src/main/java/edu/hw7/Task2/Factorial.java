package edu.hw7.Task2;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Factorial {
    private Factorial() {

    }

    public static BigInteger calculate(int n) {
        return IntStream.rangeClosed(1, n).boxed()
            .map(integer -> new BigInteger(integer.toString()))
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    public static BigInteger calculateParallelly(int n) {

        return IntStream.rangeClosed(1, n).boxed()
            .map(integer -> new BigInteger(integer.toString()))
            .parallel()
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
