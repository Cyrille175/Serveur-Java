package com.cyrille.server.handlers;

import com.cyrille.server.http.ContentTypeResolver;
import com.cyrille.server.http.HttpRequest;
import com.cyrille.server.http.HttpResponse;
import com.cyrille.server.routing.RouteHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class StaticFileHandler implements RouteHandler {
    private final Path rootDirectory;

    public StaticFileHandler(Path rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    @Override
    public HttpResponse handle(HttpRequest request) throws IOException {
        String requestPath = request.getPath();

        if (requestPath.equals("/")) {
            requestPath = "/index.html";
        }

        Path resolvedFile = rootDirectory.resolve(requestPath.substring(1)).normalize();

        if (!resolvedFile.startsWith(rootDirectory.normalize())) {
            return HttpResponse.notFound("Invalid file path.");
        }

        if (!Files.exists(resolvedFile) || Files.isDirectory(resolvedFile)) {
            return HttpResponse.notFound("Static resource not found.");
        }

        byte[] content = Files.readAllBytes(resolvedFile);
        String contentType = ContentTypeResolver.resolve(resolvedFile.getFileName().toString());

        return new HttpResponse(com.cyrille.server.http.HttpStatus.OK)
                .bytes(content, contentType);
    }
}