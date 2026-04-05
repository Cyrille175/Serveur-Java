package com.cyrille.server.handlers;

import com.cyrille.server.http.HttpRequest;
import com.cyrille.server.http.HttpResponse;
import com.cyrille.server.routing.RouteHandler;

public final class HomeHandler implements RouteHandler {
    @Override
    public HttpResponse handle(HttpRequest request) {
        String html = """
                <html>
                <head>
                    <title>Serveur Java</title>
                    <link rel="stylesheet" href="/style.css">
                </head>
                <body>
                    <div class="container">
                        <h1>Serveur Java maison</h1>
                        <p>This project is a lightweight HTTP server written in Java.</p>
                        <ul>
                            <li><a href="/health">/health</a></li>
                            <li><a href="/time">/time</a></li>
                            <li><a href="/echo?message=hello">/echo?message=hello</a></li>
                            <li><a href="/index.html">Static index page</a></li>
                        </ul>
                    </div>
                </body>
                </html>
                """;

        return HttpResponse.okHtml(html);
    }
}