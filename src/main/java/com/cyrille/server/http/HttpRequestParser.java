package com.cyrille.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public final class HttpRequestParser {

    public HttpRequest parse(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.isBlank()) {
            throw new IOException("Empty request received");
        }

        String[] requestParts = requestLine.split(" ");
        if (requestParts.length < 3) {
            throw new IOException("Invalid HTTP request line: " + requestLine);
        }

        HttpMethod method = HttpMethod.fromString(requestParts[0]);
        String rawPath = requestParts[1];

        String path;
        String query = null;

        int queryIndex = rawPath.indexOf('?');
        if (queryIndex >= 0) {
            path = rawPath.substring(0, queryIndex);
            query = rawPath.substring(queryIndex + 1);
        } else {
            path = rawPath;
        }
        String version = requestParts[2];

        Map<String, String> headers = new HashMap<>();
        String line;

        while ((line = reader.readLine()) != null && !line.isBlank()) {
            int separatorIndex = line.indexOf(':');
            if (separatorIndex > 0) {
                String headerName = line.substring(0, separatorIndex).trim();
                String headerValue = line.substring(separatorIndex + 1).trim();
                headers.put(headerName, headerValue);
            }
        }

        int contentLength = 0;
        if (headers.containsKey("Content-Length")) {
            try {
                contentLength = Integer.parseInt(headers.get("Content-Length"));
            } catch (NumberFormatException ignored) {
                contentLength = 0;
            }
        }

        String body = "";
        if (contentLength > 0) {
            char[] bodyChars = new char[contentLength];
            int read = reader.read(bodyChars);
            if (read > 0) {
                body = new String(bodyChars, 0, read);
            }
        }

        return new HttpRequest(method, path, query, version, headers, body);
    }
}