package com.cyrille.server.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class SimpleLogger {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private SimpleLogger() {
    }

    public static void info(String message) {
        log("INFO", message);
    }

    public static void error(String message) {
        log("ERROR", message);
    }

    public static void error(String message, Exception exception) {
        log("ERROR", message + " - " + exception.getMessage());
    }

    private static void log(String level, String message) {
        System.out.println("[" + LocalDateTime.now().format(FORMATTER) + "] [" + level + "] " + message);
    }
}