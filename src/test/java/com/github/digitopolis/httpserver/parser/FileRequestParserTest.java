package com.github.digitopolis.httpserver.parser;

import com.github.digitopolis.httpserver.request.HttpRequest;
import com.github.digitopolis.httpserver.response.HTTPResponse;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileRequestParserTest {
    @Test
    public void testResponseHasCorrectContentTypeHeader() {
        HttpRequest fileRequest = new HttpRequest("GET", "/doggo.jpg", "HTTP/1.1");
        HTTPResponse response = FileRequestParser.parse(fileRequest);
        assertEquals("image/jpg", response.headers.get("Content-Type"));
    }

    @Test
    public void testResponseHasCorrectMIMEType() {
        HttpRequest gifRequest = new HttpRequest("GET", "kisses.gif", "HTTP/1.1");
        HttpRequest htmlRequest = new HttpRequest("GET", "health-check.html", "HTTP/1.1");
        HttpRequest cssRequest = new HttpRequest("GET", "layout-styles.css", "HTTP/1.1");
        HTTPResponse gifResponse = FileRequestParser.parse(gifRequest);
        HTTPResponse htmlResponse = FileRequestParser.parse(htmlRequest);
        HTTPResponse cssResponse = FileRequestParser.parse(cssRequest);
        assertEquals("image/gif", gifResponse.headers.get("Content-Type"));
        assertEquals("text/html", htmlResponse.headers.get("Content-Type"));
        assertEquals("text/css", cssResponse.headers.get("Content-Type"));
    }
}
