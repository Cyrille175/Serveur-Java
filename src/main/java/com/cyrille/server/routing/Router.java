package com.cyrille.server.routing;

import com.cyrille.server.http.HttpMethod;
import com.cyrille.server.http.HttpRequest;
import com.cyrille.server.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public final class Router {
    private final Map<String, RouteHandler> getRoutes = new HashMap<>();
    private RouteHandler notFoundHandler;

    public void get(String path, RouteHandler handler) {
        getRoutes.put(path, handler);
    }

    public void setNotFoundHandler(RouteHandler notFoundHandler) {
        this.notFoundHandler = notFoundHandler;
    }

    public HttpResponse route(HttpRequest request) throws Exception {
        if (request.getMethod() != HttpMethod.GET && request.getMethod() != HttpMethod.POST) {
            return HttpResponse.methodNotAllowed("Only GET and POST are supported for now.");
        }

        if (request.getMethod() == HttpMethod.GET) {
            RouteHandler handler = getRoutes.get(request.getPath());
            if (handler != null) {
                return handler.handle(request);
            }
        }

        if (notFoundHandler != null) {
            return notFoundHandler.handle(request);
        }

        return HttpResponse.notFound("No handler found for " + request.getPath());
    }
}