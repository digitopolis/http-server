package com.github.digitopolis.httpserver.request;

public class HttpRequest {
    public String method;
    public String path;
    public String httpVersion;

    public HttpRequest(String method, String path, String httpVersion) {
        this.method = method;
        this.path = path;
        this.httpVersion = httpVersion;
    }
}
