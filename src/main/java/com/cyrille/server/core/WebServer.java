package com.cyrille.server.core;

import com.cyrille.server.config.ServerConfig;
import com.cyrille.server.http.HttpRequestParser;
import com.cyrille.server.routing.Router;
import com.cyrille.server.util.SimpleLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class WebServer {
    private final ServerConfig config;
    private final Router router;
    private final HttpRequestParser parser;
    private final ExecutorService executorService;

    public WebServer(ServerConfig config, Router router) {
        this.config = config;
        this.router = router;
        this.parser = new HttpRequestParser();
        this.executorService = Executors.newFixedThreadPool(config.getThreadPoolSize());
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            SimpleLogger.info("Server started on port " + config.getPort());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(new ClientHandler(clientSocket, router, parser));
            }
        } catch (IOException exception) {
            SimpleLogger.error("Server stopped unexpectedly", exception);
        } finally {
            executorService.shutdown();
        }
    }
}
