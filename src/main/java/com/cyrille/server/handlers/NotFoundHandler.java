package com.cyrille.server.handlers;

import com.cyrille.server.http.HttpRequest;
import com.cyrille.server.http.HttpResponse;
import com.cyrille.server.routing.RouteHandler;

public final class NotFoundHandler implements RouteHandler {
    @Override
    public HttpResponse handle(HttpRequest request) {
        return HttpResponse.notFound("No route matches " + request.getPath());
    }
}