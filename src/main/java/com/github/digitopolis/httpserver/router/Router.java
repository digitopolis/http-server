package com.github.digitopolis.httpserver.router;

import com.github.digitopolis.httpserver.request.HttpRequest;
import com.github.digitopolis.httpserver.response.HTTPResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Router {
    private static final HashMap<String, String[]> routes = new HashMap<>();
    static {
        routes.put("/simple_get", new String[] { "GET", "HEAD"});
        routes.put("/simple_get_with_body", new String[] { "GET", "HEAD" });
        routes.put("/head_request", new String[] { "HEAD" });
    }

    public static HTTPResponse handleRequest(HttpRequest request) {
        if (routes.containsKey(request.path)) {
            List<String> methodList = Arrays.asList(routes.get(request.path));
            if (!methodList.contains(request.method)) {
                return new HTTPResponse(request.httpVersion, "405", "Method Not Allowed");
            }
            HTTPResponse response;
            switch (request.path) {
                case "/simple_get":
                    return new HTTPResponse(request.httpVersion, "200", "OK");
                case "/simple_get_with_body":
                    response = new HTTPResponse(request.httpVersion, "200", "OK");
                    response.addContentType("Content-Type: text/html");
                    response.addBodyHTML("Hello world!");
                    return response;
                default:
                    return new HTTPResponse(request.httpVersion, "404", "Not Found");
            }
        }
        else return new HTTPResponse(request.httpVersion, "404", "Not Found");
    }
}
