package edu.hw7;

import edu.hw7.Task1.MultithreadIncrementer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MultithreadIncTest {
    private final static Logger LOGGER = LogManager.getLogger();
    @Test
    @DisplayName("One-thread incrementer test")
    void OneThreadTest() {
        MultithreadIncrementer inc = new MultithreadIncrementer();
        assertThat(inc.getStorage()).isInstanceOf(AtomicInteger.class);
        assertThat(inc.increment()).isEqualTo(0);
        assertThat(inc.get()).isEqualTo(1);

        for (int i = 1; i < 1001; ++i) {
            assertThat(inc.increment()).isEqualTo(i);
        }
        assertThat(inc.get()).isEqualTo(1001);
    }

    @Test
    @DisplayName("Multi-thread incrementer test")
    void MultiThreadTest() throws InterruptedException {
        MultithreadIncrementer inc = new MultithreadIncrementer();
        CountDownLatch latch = new CountDownLatch(3);
        var executor = Executors.newFixedThreadPool(3);
        executor.submit(() ->
        {
            for (int i = 0; i < 10000; ++i) {
                inc.increment();
            }
            latch.countDown();
        });
        executor.submit(() ->
        {
            for (int i = 0; i < 10000; ++i) {
                LOGGER.info("Received " + inc.get());
            }
            latch.countDown();
        });
        executor.submit(() ->
        {
            for (int i = 0; i < 10000; ++i) {
                LOGGER.info("Received "  + inc.increment() + " and updated");
            }
            latch.countDown();
        });
        latch.await();
        assertThat(inc.get()).isEqualTo(20000);

    }
}
