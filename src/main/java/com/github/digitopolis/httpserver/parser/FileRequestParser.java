package com.github.digitopolis.httpserver.parser;

import com.github.digitopolis.httpserver.request.HttpRequest;
import com.github.digitopolis.httpserver.response.HTTPResponse;

public class FileRequestParser {
    public static HTTPResponse parse(HttpRequest request) {
        HTTPResponse response = new HTTPResponse(request.httpVersion, "200", "OK");
        String extension = request.path.split("\\.")[1].toLowerCase();
        switch (extension) {
            case "jpg":
            case "gif":
            case "png":
                response.addHeader("Content-Type", "image/" + extension);
                return response;
            case "html":
            case "css":
                response.addHeader("Content-Type", "text/" + extension);
                return response;
            default:
                response.addHeader("Content-Type", "text/plain");
        }
        return response;
    }
}
