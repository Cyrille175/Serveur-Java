package com.cyrille.server.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class HttpRequest {
    private final HttpMethod method;
    private final String path;
    private final String version;
    private final String query;
    private final Map<String, String> headers;
    private final String body;

    public HttpRequest(HttpMethod method, String path, String query, String version, Map<String, String> headers, String body) {
        this.method = method;
        this.path = path;
        this.query = query;
        this.version = version;
        this.headers = new HashMap<>(headers);
        this.body = body;
    }

    public String getQuery() {
        return query;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getVersion() {
        return version;
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    public String getBody() {
        return body;
    }
}