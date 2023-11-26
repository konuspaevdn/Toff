package edu.hw7.Task4;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class MonteCarlo {
    private MonteCarlo() {

    }

    private final static int SEED = 42;

    @SuppressWarnings("MagicNumber")
    public static Report runOneThread(int numberOfSamples) {
        SecureRandom rand = new SecureRandom(new byte[]{SEED});
        int circleCount = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < numberOfSamples; ++i) {
            double x = rand.nextDouble(2);
            double y = rand.nextDouble(2);
            if ((x - 1) * (x - 1) + (y - 1) * (y - 1) < 1) {
                ++circleCount;
            }
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        double calculatedPi =  (4d * circleCount) / numberOfSamples;
        return new Report(elapsedTime, calculatedPi);
    }

    public static Report runMultiThread(int numberOfSamples, int numberOfThreads) {
        List<Thread> threads = new ArrayList<>(numberOfThreads);
        for (int i = 0; i < numberOfThreads; ++i) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                int locCircleCount = 0;
                int tasksPerThread = numberOfSamples / numberOfThreads + 1;
                if (finalI == numberOfThreads - 1) {
                    tasksPerThread = numberOfSamples % tasksPerThread;
                }
                //for (int i = 0; )
            });
        }
        return null;
    }
}
