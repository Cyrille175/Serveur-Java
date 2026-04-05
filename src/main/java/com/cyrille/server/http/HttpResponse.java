package com.cyrille.server.http;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public final class HttpResponse {
    private final HttpStatus status;
    private final Map<String, String> headers = new LinkedHashMap<>();
    private byte[] body = new byte[0];

    public HttpResponse(HttpStatus status) {
        this.status = status;
        headers.put("Server", "CyrilleJavaServer/1.0");
        headers.put("Connection", "close");
    }

    public HttpResponse header(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public HttpResponse text(String content) {
        this.body = content.getBytes(StandardCharsets.UTF_8);
        headers.put("Content-Type", "text/plain; charset=UTF-8");
        headers.put("Content-Length", String.valueOf(body.length));
        return this;
    }

    public HttpResponse html(String content) {
        this.body = content.getBytes(StandardCharsets.UTF_8);
        headers.put("Content-Type", "text/html; charset=UTF-8");
        headers.put("Content-Length", String.valueOf(body.length));
        return this;
    }

    public HttpResponse json(String content) {
        this.body = content.getBytes(StandardCharsets.UTF_8);
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("Content-Length", String.valueOf(body.length));
        return this;
    }

    public HttpResponse bytes(byte[] content, String contentType) {
        this.body = content;
        headers.put("Content-Type", contentType);
        headers.put("Content-Length", String.valueOf(body.length));
        return this;
    }

    public void write(OutputStream outputStream) throws IOException {
        StringBuilder responseBuilder = new StringBuilder();

        responseBuilder.append("HTTP/1.1 ")
                .append(status.code())
                .append(' ')
                .append(status.reason())
                .append("\r\n");

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            responseBuilder.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\r\n");
        }

        responseBuilder.append("\r\n");

        outputStream.write(responseBuilder.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.write(body);
        outputStream.flush();
    }

    public static HttpResponse okText(String content) {
        return new HttpResponse(HttpStatus.OK).text(content);
    }

    public static HttpResponse okHtml(String content) {
        return new HttpResponse(HttpStatus.OK).html(content);
    }

    public static HttpResponse okJson(String content) {
        return new HttpResponse(HttpStatus.OK).json(content);
    }

    public static HttpResponse notFound(String message) {
        return new HttpResponse(HttpStatus.NOT_FOUND).html(
                "<html><body><h1>404 Not Found</h1><p>" + escapeHtml(message) + "</p></body></html>"
        );
    }

    public static HttpResponse badRequest(String message) {
        return new HttpResponse(HttpStatus.BAD_REQUEST).html(
                "<html><body><h1>400 Bad Request</h1><p>" + escapeHtml(message) + "</p></body></html>"
        );
    }

    public static HttpResponse methodNotAllowed(String message) {
        return new HttpResponse(HttpStatus.METHOD_NOT_ALLOWED).html(
                "<html><body><h1>405 Method Not Allowed</h1><p>" + escapeHtml(message) + "</p></body></html>"
        );
    }

    public static HttpResponse serverError(String message) {
        return new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR).html(
                "<html><body><h1>500 Internal Server Error</h1><p>" + escapeHtml(message) + "</p></body></html>"
        );
    }

    private static String escapeHtml(String value) {
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}