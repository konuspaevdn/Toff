package edu.hw8;

import edu.hw8.Task2.MyFixedThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    private static class Fibonacci implements Runnable {
        private final int n;
        public static Map<String, Integer> calculatedValues = Collections.synchronizedMap(new HashMap<>());

        public Fibonacci(int num) {
            n = num;
        }

        @Override
        public void run() {
            if (n == 1 || n == 2) {
                calculatedValues.put(Thread.currentThread().getName(), n - 1);
                return;
            }
            List<Integer> table = Collections.synchronizedList(new ArrayList<>());
            table.add(0);
            table.add(1);
            for (int i = 2; i < n; ++i) {
                table.add(table.get(i - 1) + table.get(i - 2));
            }
            calculatedValues.put(Thread.currentThread().getName(), table.getLast());
        }
    }
    @Test
    @DisplayName("Test MyThreadPool")
    void ThreadPoolTest() throws InterruptedException {
        var tp = MyFixedThreadPool.create(5);
        tp.execute(new Fibonacci(5));
        tp.execute(new Fibonacci(10));
        tp.execute(new Fibonacci(15));
        tp.execute(new Fibonacci(20));
        tp.execute(new Fibonacci(25));
        Thread.sleep(1000);
        assertThat(Fibonacci.calculatedValues.size()).isEqualTo(5);
        tp.close();
        tp.execute(new Fibonacci(42));
        tp.execute(new Fibonacci(56));
        tp.execute(new Fibonacci(12));
        Thread.sleep(1000);
        assertThat(Fibonacci.calculatedValues.size()).isEqualTo(5);
    }
}
