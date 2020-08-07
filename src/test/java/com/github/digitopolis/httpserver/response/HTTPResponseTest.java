package com.github.digitopolis.httpserver.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HTTPResponseTest {
    @Test
    public void testAddsHeadersToResponse() {
        HTTPResponse response = new HTTPResponse("HTTP/1.1", "200", "OK");
        response.addHeader("Content-Length", "10");
        assertEquals("10", response.headers.get("Content-Length"));
        assertTrue(response.headers.containsKey("Content-Length"));
    }

    @Test
    public void testConstructsHeadersString() {
        HTTPResponse response = new HTTPResponse("HTTP/1.1", "200", "OK");
        response.addHeader("Content-Length", "15");
        response.addHeader("Content-Type", "text/plain");
        String expectedHeaders = "Content-Length: 15\nContent-Type: text/plain";
        assertEquals(expectedHeaders, response.getHeaders());
    }
}
