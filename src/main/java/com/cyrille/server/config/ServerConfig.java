package com.cyrille.server.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class ServerConfig {
    private final int port;
    private final int threadPoolSize;
    private final Path staticDirectory;

    public ServerConfig(int port, int threadPoolSize, Path staticDirectory) {
        this.port = port;
        this.threadPoolSize = threadPoolSize;
        this.staticDirectory = staticDirectory;
    }

    public static ServerConfig defaultConfig() {
        return new ServerConfig(
                9090,
                10,
                Paths.get("src", "main", "resources", "public")
        );
    }

    public int getPort() {
        return port;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public Path getStaticDirectory() {
        return staticDirectory;
    }
}