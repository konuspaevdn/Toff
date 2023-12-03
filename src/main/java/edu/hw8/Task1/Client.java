package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public String sendAndGet(String keyword, int port) throws IOException, InterruptedException {
        Socket client = new Socket(InetAddress.getByName("localhost"), port);
        var writer = new PrintWriter(client.getOutputStream(), true);
        writer.println(keyword);
        String response;
        var reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        response = reader.readLine();
        client.close();
        return response;
    }
}
