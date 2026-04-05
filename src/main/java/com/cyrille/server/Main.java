package com.cyrille.server;

import com.cyrille.server.config.ServerConfig;
import com.cyrille.server.core.WebServer;
import com.cyrille.server.handlers.EchoHandler;
import com.cyrille.server.handlers.HealthHandler;
import com.cyrille.server.handlers.HomeHandler;
import com.cyrille.server.handlers.HelloHandler;
import com.cyrille.server.handlers.NotFoundHandler;
import com.cyrille.server.handlers.StaticFileHandler;
import com.cyrille.server.handlers.TimeHandler;
import com.cyrille.server.routing.Router;

public final class Main {
    public static void main(String[] args) {
        ServerConfig config = ServerConfig.defaultConfig();

        Router router = new Router();
        router.get("/", new StaticFileHandler(config.getStaticDirectory()));
        router.get("/index.html", new StaticFileHandler(config.getStaticDirectory()));
        router.get("/style.css", new StaticFileHandler(config.getStaticDirectory()));
        router.get("/app.js", new StaticFileHandler(config.getStaticDirectory()));

        router.get("/hello", new HelloHandler());
        router.get("/health", new HealthHandler());
        router.get("/time", new TimeHandler());
        router.get("/echo", new EchoHandler());

router.setNotFoundHandler(new NotFoundHandler());

        WebServer server = new WebServer(config, router);
        server.start();
    }
}