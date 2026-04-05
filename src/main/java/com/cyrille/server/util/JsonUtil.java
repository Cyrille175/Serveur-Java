package com.cyrille.server.util;

import java.util.Map;
import java.util.StringJoiner;

public final class JsonUtil {
    private JsonUtil() {
    }

    public static String object(Map<String, Object> values) {
        StringJoiner joiner = new StringJoiner(", ", "{", "}");

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            joiner.add("\"" + escape(entry.getKey()) + "\": " + formatValue(entry.getValue()));
        }

        return joiner.toString();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof Number || value instanceof Boolean) {
            return String.valueOf(value);
        }

        return "\"" + escape(String.valueOf(value)) + "\"";
    }

    private static String escape(String input) {
        return input
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}