package com.github.digitopolis.httpserver.request;

import java.util.HashMap;

public class HttpRequest {
    public String method;
    public String path;
    public String httpVersion;
    public HashMap<String, String> headers;
    public String body;

    public HttpRequest(String method, String path, String httpVersion) {
        this.method = method;
        this.path = path;
        this.httpVersion = httpVersion;
    }

    public void addHeaders(HashMap headers) {
        this.headers = headers;
    }

    public static class Builder {
        private String method;
        private String path;
        private String httpVersion;
        private HashMap<String, String> headers;
        private String body;

        public Builder(String method) {
            this.method = method;
        }

        public HttpRequest.Builder withPath(String path) {
            this.path = path;
            return this;
        }

        public HttpRequest.Builder withHttpVersion(String httpVersion) {
            this.httpVersion = httpVersion;
            return this;
        }

        public HttpRequest.Builder withHeaders(HashMap<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public HttpRequest.Builder withBody(String body) {
            this.body = body;
            return this;
        }

        public HttpRequest build() {
            HttpRequest httpRequest = new HttpRequest(this.method, this.path, this.httpVersion);
            httpRequest.headers = this.headers;
            httpRequest.body = this.body;
            return httpRequest;
        }
    }
}
