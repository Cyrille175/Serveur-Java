package com.cyrille.server.handlers;

import com.cyrille.server.http.HttpRequest;
import com.cyrille.server.http.HttpResponse;
import com.cyrille.server.routing.RouteHandler;
import com.cyrille.server.util.JsonUtil;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public final class TimeHandler implements RouteHandler {
    @Override
    public HttpResponse handle(HttpRequest request) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("serverTime", LocalDateTime.now().toString());
        payload.put("path", request.getPath());

        return HttpResponse.okJson(JsonUtil.object(payload));
    }
}