package com.cyrille.server.http;

public enum HttpMethod {
    GET,
    POST,
    UNKNOWN;

    public static HttpMethod fromString(String value) {
        if (value == null) {
            return UNKNOWN;
        }

        try {
            return HttpMethod.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException exception) {
            return UNKNOWN;
        }
    }
}