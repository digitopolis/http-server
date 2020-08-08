package com.github.digitopolis.httpserver.response;

import com.github.digitopolis.httpserver.HTMLBuilder;

import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class HTTPResponse {
    public String httpVersion;
    public String statusCode;
    public String reason;
    public String method;
    public String body;
    public String contentType;
    public HashMap<String, String> headers;
    private final HTMLBuilder HTMLBuilder = new HTMLBuilder();

    public HTTPResponse(String httpVersion, String statusCode, String reason) {
        this.httpVersion = httpVersion;
        this.statusCode = statusCode;
        this.reason = reason;
        this.body = "";
        this.contentType = "";
        this.headers = new HashMap<>();
    }

    public void addContentType(String contentType) {
        this.contentType = "Content-Type: " + contentType;
    }

    public void addBody(String content) {
        this.body = content;
    }

    public void addBodyHTML(String content) {
        this.body = HTMLBuilder.addBody(content);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public String getStatusLine() {
        return String.format("%s %s %s", httpVersion, statusCode, reason);
    }

    public String getHeaders() {
        StringBuilder headerString = new StringBuilder();
        if (!headers.isEmpty()) {
            headers.forEach((header, value) -> {
                headerString.append(String.format("%s: %s\n", header, value));
            });
            return StringUtils.chomp(headerString.toString());
        }
        return "";
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
