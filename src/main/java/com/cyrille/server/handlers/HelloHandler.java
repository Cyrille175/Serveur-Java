package com.cyrille.server.handlers;

import com.cyrille.server.http.HttpRequest;
import com.cyrille.server.http.HttpResponse;
import com.cyrille.server.routing.RouteHandler;

public final class HelloHandler implements RouteHandler {
    @Override
    public HttpResponse handle(HttpRequest request) {
        return HttpResponse.okText("Hello from the Java server!");
    }
}