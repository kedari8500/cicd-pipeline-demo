package com.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class App {

    public static String greet(String name) {
        return "Hello, " + name + "! Deployed automatically by Jenkins.";
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(9090), 0);
        server.createContext("/", ex -> send(ex, greet("Kedari")));
        server.createContext("/health", ex -> send(ex, "OK"));
        server.start();
        System.out.println("Server running on http://localhost:9090");
    }

    private static void send(HttpExchange ex, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.sendResponseHeaders(200, bytes.length);
        try (var os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }
}
