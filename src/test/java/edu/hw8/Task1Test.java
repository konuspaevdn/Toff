package edu.hw8;

import edu.hw8.Task1.Client;
import edu.hw8.Task1.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Vanya is trying out his server")
    void serverTest() throws IOException, InterruptedException {
        int port = 18080;
        Server server = new Server(2, port);
        ExecutorService service = Executors.newFixedThreadPool(3);
        CountDownLatch latch2 = new CountDownLatch(2);

        long start1 = System.nanoTime();

        service.submit(() -> {
            Client client1 = new Client();
            try {
                String response1 = client1.sendAndGet("оскорбления", port);
                assertThat(response1.contains("оскорбления")).isTrue();
                latch2.countDown();
            } catch (IOException | InterruptedException e) {
                System.out.println("Client 1: " + e.getMessage());
                throw new RuntimeException(e);
            }
        });

        service.submit(() -> {
            Client client2 = new Client();
            try {
                String response2 = client2.sendAndGet("личности", port);
                assertThat(response2.contains("личности")).isTrue();
                latch2.countDown();
            } catch (IOException | InterruptedException e) {
                System.out.println("Client 2: " + e.getMessage());
                throw new RuntimeException(e);
            }
        });

        server.process(2);
        latch2.await();

        long elapsedTime1 = System.nanoTime() - start1;
        assertThat(elapsedTime1 / 1e9).isLessThan(6);

        var latch3 = new CountDownLatch(3);

        long start2 = System.nanoTime();

        service.submit(() -> {
            Client client1 = new Client();
            try {
                String response1 = client1.sendAndGet("оскорбления", port);
                assertThat(response1.contains("оскорбления")).isTrue();
                latch3.countDown();
            } catch (IOException | InterruptedException e) {
                System.out.println("Client 1: " + e.getMessage());
                throw new RuntimeException(e);
            }
        });

        service.submit(() -> {
            Client client2 = new Client();
            try {
                String response2 = client2.sendAndGet("личности", port);
                assertThat(response2.contains("личности")).isTrue();
                latch3.countDown();
            } catch (IOException | InterruptedException e) {
                System.out.println("Client 2: " + e.getMessage());
                throw new RuntimeException(e);
            }
        });

        service.submit(() -> {
            Client client3 = new Client();
            try {
                String response3 = client3.sendAndGet("интеллект", port);
                assertThat(response3.contains("интеллект")).isTrue();
                latch3.countDown();
            } catch (IOException | InterruptedException e) {
                System.out.println("Client 3: " + e.getMessage());
                throw new RuntimeException(e);
            }
        });

        server.process(3);
        latch3.await();

        long elapsedTime2 = System.nanoTime() - start2;
        assertThat(elapsedTime2 / 1e9).isGreaterThan(10);

        server.close(10);
    }
}
