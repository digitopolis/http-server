package com.github.digitopolis.httpserver.response;

import com.github.digitopolis.httpserver.HTMLBuilder;

public class HTTPResponse {
    public String httpVersion;
    public String statusCode;
    public String reason;
    public String method;
    public String body;
    public String contentType;
    private final HTMLBuilder HTMLBuilder = new HTMLBuilder();

    public HTTPResponse(String httpVersion, String statusCode, String reason) {
        this.httpVersion = httpVersion;
        this.statusCode = statusCode;
        this.reason = reason;
        this.body = "";
        this.contentType = "";
    }

    public void addContentType(String contentType) {
        this.contentType = contentType;
    }

    public void addBody(String content) {
        this.body = content;
    }

    public void addBodyHTML(String content) {
        this.body = HTMLBuilder.addBody(content);
    }

    public String getStatusLine() {
        return String.format("%s %s %s", httpVersion, statusCode, reason);
    }
    public String getMethod() {
        return method;
    }
    public String getBody() {
        return body;
    }
    public String getContentType() {
        return contentType;
    }
}
