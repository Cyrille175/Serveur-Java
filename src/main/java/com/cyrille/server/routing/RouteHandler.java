package com.cyrille.server.routing;

import com.cyrille.server.http.HttpRequest;
import com.cyrille.server.http.HttpResponse;

public interface RouteHandler {
    HttpResponse handle(HttpRequest request) throws Exception;
}