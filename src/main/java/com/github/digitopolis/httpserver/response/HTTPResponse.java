package com.github.digitopolis.httpserver.response;

public class HTTPResponse {
    public String httpVersion;
    public String statusCode;
    public String reason;

    public HTTPResponse(String httpVersion, String statusCode, String reason) {
        this.httpVersion = httpVersion;
        this.statusCode = statusCode;
        this.reason = reason;
    }

    public String getStatusLine() {
        return String.format("%s %s %s", httpVersion, statusCode, reason) + "\r\n";
    }
}
