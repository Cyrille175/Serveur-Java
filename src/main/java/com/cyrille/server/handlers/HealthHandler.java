package com.cyrille.server.handlers;

import com.cyrille.server.http.HttpRequest;
import com.cyrille.server.http.HttpResponse;
import com.cyrille.server.routing.RouteHandler;
import com.cyrille.server.util.JsonUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public final class HealthHandler implements RouteHandler {
    @Override
    public HttpResponse handle(HttpRequest request) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("status", "UP");
        payload.put("service", "Serveur-Java");
        payload.put("method", request.getMethod().name());

        return HttpResponse.okJson(JsonUtil.object(payload));
    }
}