package com.cyrille.server.handlers;

import com.cyrille.server.http.HttpRequest;
import com.cyrille.server.http.HttpResponse;
import com.cyrille.server.routing.RouteHandler;
import com.cyrille.server.util.JsonUtil;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public final class EchoHandler implements RouteHandler {
    @Override
    public HttpResponse handle(HttpRequest request) {
        String query = request.getQuery();
        String message = "No message provided";

        if (query != null && !query.isBlank()) {
            for (String pair : query.split("&")) {
                String[] parts = pair.split("=", 2);
                if (parts.length == 2 && "message".equals(parts[0])) {
                    message = URLDecoder.decode(parts[1], StandardCharsets.UTF_8);
                    break;
                }
            }
        }

        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("echo", message);
        payload.put("path", request.getPath());

        return HttpResponse.okJson(JsonUtil.object(payload));
    }
}