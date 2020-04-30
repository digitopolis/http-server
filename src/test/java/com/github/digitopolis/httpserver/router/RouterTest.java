package com.github.digitopolis.httpserver.router;

import com.github.digitopolis.httpserver.parser.RequestParser;
import org.junit.jupiter.api.Test;

import com.github.digitopolis.httpserver.response.HTTPResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {
    @Test
    public void testSends200ResponseToValidPath() {
        String validRequest = "GET /simple_get HTTP/1.1";
        HTTPResponse response = Router.handleRequest(RequestParser.parseInput(validRequest));
        assertEquals("200", response.statusCode);
    }
}
