package com.github.digitopolis.httpserver.router;

import com.github.digitopolis.httpserver.request.HttpRequest;
import com.github.digitopolis.httpserver.response.HTTPResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Router {
    private static final HashMap<String, String[]> routes = new HashMap<>();
    static {
        routes.put("/simple_get", new String[] { "GET", "HEAD" });
        routes.put("/simple_get_with_body", new String[] { "GET", "HEAD" });
        routes.put("/head_request", new String[] { "HEAD", "OPTIONS" });
        routes.put("/echo_body", new String[] { "POST" });
        routes.put("/redirect", new String[] { "GET" });
    }

    public static HTTPResponse handleRequest(HttpRequest request) {
        if (routes.containsKey(request.path)) {
            HTTPResponse response;
            List<String> methodList = Arrays.asList(routes.get(request.path));
            if (!methodList.contains(request.method)) {
                 response = new HTTPResponse(request.httpVersion, "405", "Method Not Allowed");
                 response.addHeader("Allow", StringUtils.join(methodList, ", "));
                 return response;
            }
            switch (request.path) {
                case "/simple_get":
                    return new HTTPResponse(request.httpVersion, "200", "OK");
                case "/simple_get_with_body":
                    response = new HTTPResponse(request.httpVersion, "200", "OK");
                    response.addHeader("Content-Type", "text/html");
                    response.addBodyHTML("Hello world!");
                    return response;
                case "/echo_body":
                    response = new HTTPResponse(request.httpVersion, "200", "OK");
                    response.addContentType(request.headers.get("Content-Type"));
                    response.addBody(request.body);
                    return response;
                case "/redirect":
                    response = new HTTPResponse(request.httpVersion, "301", "Moved Permanently");
                    response.addHeader("Location", "http://127.0.0.1/simple_get");
                    return response;
                default:
                    return new HTTPResponse(request.httpVersion, "404", "Not Found");
            }
        }
        else return new HTTPResponse(request.httpVersion, "404", "Not Found");
    }
}
