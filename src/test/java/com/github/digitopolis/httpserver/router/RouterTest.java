package com.github.digitopolis.httpserver.router;

import com.github.digitopolis.httpserver.parser.RequestParser;
import com.github.digitopolis.httpserver.request.HttpRequest;
import org.junit.jupiter.api.Test;

import com.github.digitopolis.httpserver.response.HTTPResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouterTest {
    @Test
    public void testSends200ResponseToValidPath() {
        HttpRequest validRequest = new HttpRequest("GET","/simple_get","HTTP/1.1");
        HTTPResponse response = Router.handleRequest(validRequest);
        assertEquals("200", response.statusCode);
    }

    @Test
    public void testResponseForRedirectHas301StatusCode() {
        HttpRequest redirectRequest = new HttpRequest("GET", "/redirect", "HTTP/1.1");
        HTTPResponse response = Router.handleRequest(redirectRequest);
        assertEquals("301", response.statusCode);
    }

    @Test
    public void testResponseForRedirectHasLocationHeader() {
        HttpRequest redirectRequest = new HttpRequest("GET", "/redirect", "HTTP/1.1");
        HTTPResponse response = Router.handleRequest(redirectRequest);
        assertTrue(response.headers.containsKey("Location"));
    }

    @Test
    public void testResponseForWrongMethodHas405StatusCode() {
        HttpRequest wrongMethodRequest = new HttpRequest("GET", "/head_request", "HTTP/1.1");
        HTTPResponse response = Router.handleRequest(wrongMethodRequest);
        assertEquals("405", response.statusCode);
    }

    @Test
    public void testResponseForWrongMethodHasAllowedHeader() {
        HttpRequest wrongMethodRequest = new HttpRequest("GET", "/head_request", "HTTP/1.1");
        HTTPResponse response = Router.handleRequest(wrongMethodRequest);
        assertTrue(response.headers.containsKey("Allow"));
        assertEquals("HEAD, OPTIONS", response.headers.get("Allow"));
    }

    @Test
    public void testResponseForOptionsHasAllowHeader() {
        HttpRequest wrongMethodRequest = new HttpRequest("OPTIONS", "/method_options", "HTTP/1.1");
        HTTPResponse response = Router.handleRequest(wrongMethodRequest);
        assertTrue(response.headers.containsKey("Allow"));
        assertEquals("GET, HEAD, OPTIONS", response.headers.get("Allow"));
    }

    @Test
    public void testResponseForOptionsHasContentLengthHeader() {
        HttpRequest wrongMethodRequest = new HttpRequest("OPTIONS", "/method_options", "HTTP/1.1");
        HTTPResponse response = Router.handleRequest(wrongMethodRequest);
        assertTrue(response.headers.containsKey("Content-Length"));
        assertEquals("0", response.headers.get("Content-Length"));
    }
}
