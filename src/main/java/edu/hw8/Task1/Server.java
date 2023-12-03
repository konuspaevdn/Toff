package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {
    private final ExecutorService service;
    private final ServerSocket server;
    private static final long SLEEP_TIME = 5000;

    public Server(int nWorkers, int port) throws IOException {
        service = Executors.newFixedThreadPool(nWorkers);
        server = new ServerSocket(port);
    }

    public void process(int tasksToProcess) {
        int count = 0;
        while (count++ < tasksToProcess) {
            try {
                var socket = server.accept();
                CompletableFuture.runAsync(() -> {
                    try {
                        var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        var key = reader.readLine();
                        Thread.sleep(SLEEP_TIME);  // for testing
                        var writer = new PrintWriter(socket.getOutputStream(), true);
                        String response = Citations.search(key);
                        writer.println(response);
                        socket.close();
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }, service);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void close(long time) throws InterruptedException, IOException {
        service.shutdown();
        service.awaitTermination(time, TimeUnit.SECONDS);
        server.close();
    }
}
