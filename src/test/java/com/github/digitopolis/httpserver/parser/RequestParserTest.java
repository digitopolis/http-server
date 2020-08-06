package com.github.digitopolis.httpserver.parser;

import org.junit.jupiter.api.Test;

import com.github.digitopolis.httpserver.request.HttpRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestParserTest {
    @Test
    public void testDeterminesRequestMethodPathAndHTTPVersion() {
        String request = "GET / HTTP/1.1";
        HttpRequest httpRequest = RequestParser.parseInput(request);
        assertEquals("GET", httpRequest.method);
        assertEquals("/", httpRequest.path);
        assertEquals("HTTP/1.1", httpRequest.httpVersion);
    }
}
