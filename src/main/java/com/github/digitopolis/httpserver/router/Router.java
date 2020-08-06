package com.github.digitopolis.httpserver.router;

import com.github.digitopolis.httpserver.request.HttpRequest;
import com.github.digitopolis.httpserver.response.HTTPResponse;

public class Router {
    public static HTTPResponse handleRequest(HttpRequest request) {
        switch (request.path) {
            case "/simple_get": return new HTTPResponse(request.httpVersion, "200", "OK");
            case "/simple_get_with_body":
                HTTPResponse response = new HTTPResponse(request.httpVersion, "200", "OK");
                response.addContentType("Content-Type: text/html");
                response.addBody("Hello world!");
                return response;
            default: return new HTTPResponse(request.httpVersion, "404", "Not Found");
        }
    }
}
