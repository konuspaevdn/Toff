package edu.hw7.Task4;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MonteCarlo {
    private MonteCarlo() {

    }

    private final static int SEED = 42;

    @SuppressWarnings("MagicNumber")
    public static Report runOneThread(int numberOfSamples) {
        long startTime = System.nanoTime();

        SecureRandom rand = new SecureRandom(new byte[]{SEED});

        int circleCount = 0;
        for (int i = 0; i < numberOfSamples; ++i) {
            double x = rand.nextDouble(2);
            double y = rand.nextDouble(2);
            if ((x - 1) * (x - 1) + (y - 1) * (y - 1) < 1) {
                ++circleCount;
            }
        }

        double calculatedPi =  (4d * circleCount) / numberOfSamples;

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        return new Report(elapsedTime, calculatedPi);
    }

    @SuppressWarnings("MagicNumber")
    public static Report runMultiThread(int numberOfSamples, int numberOfThreads) {
        long startTime = System.nanoTime();

        List<Thread> threads = new ArrayList<>(numberOfThreads);
        AtomicInteger circleCount = new AtomicInteger(0);
        for (int i = 0; i < numberOfThreads; ++i) {
            int threadNum = i;
            Thread thread = new Thread(() -> {
                int locCircleCount = 0;
                int samplesPerThread = numberOfSamples / numberOfThreads + 1;
                if (threadNum == numberOfThreads - 1) {
                    samplesPerThread = numberOfSamples % samplesPerThread;
                }
                for (int j = 0; j < samplesPerThread; ++j) {
                    double x = ThreadLocalRandom.current().nextDouble(2);
                    double y = ThreadLocalRandom.current().nextDouble(2);
                    if ((x - 1) * (x - 1) + (y - 1) * (y - 1) < 1) {
                        ++locCircleCount;
                    }
                }
                circleCount.getAndAdd(locCircleCount);
            });
            threads.add(thread);
            thread.start();
        }

        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        double calculatedPi =  (4d * circleCount.get()) / numberOfSamples;

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        return new Report(elapsedTime, calculatedPi);
    }
}
