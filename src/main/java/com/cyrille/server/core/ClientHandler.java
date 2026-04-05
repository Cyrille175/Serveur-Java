package com.cyrille.server.core;

import com.cyrille.server.http.HttpRequest;
import com.cyrille.server.http.HttpRequestParser;
import com.cyrille.server.http.HttpResponse;
import com.cyrille.server.routing.Router;
import com.cyrille.server.util.SimpleLogger;

import java.net.Socket;

public final class ClientHandler implements Runnable {
    private final Socket socket;
    private final Router router;
    private final HttpRequestParser parser;

    public ClientHandler(Socket socket, Router router, HttpRequestParser parser) {
        this.socket = socket;
        this.router = router;
        this.parser = parser;
    }

    @Override
    public void run() {
        try (Socket client = socket) {
            HttpRequest request = parser.parse(client.getInputStream());
            HttpResponse response = router.route(request);
            response.write(client.getOutputStream());

            SimpleLogger.info("Handled " + request.getMethod() + " " + request.getPath()
                    + " from " + client.getInetAddress().getHostAddress());
        } catch (Exception exception) {
            SimpleLogger.error("Request processing failed", exception);
            try {
                HttpResponse.serverError("Unexpected server error.")
                        .write(socket.getOutputStream());
            } catch (Exception ignored) {
            }
        }
    }
}