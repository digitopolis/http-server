package com.github.digitopolis.httpserver.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.digitopolis.httpserver.request.HttpRequest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CRL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestParserTest {
    RequestParser requestParser;
    private static final String CRLF = "\r\n";

    @BeforeEach
    public void initialize() {
        requestParser = new RequestParser();
    }
    @Test
    public void testParsesInitialLine() throws IOException {
        String request = "GET / HTTP/1.1";
        BufferedReader input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(request.getBytes())));
        HttpRequest httpRequest = requestParser.parseInput(input);
        assertEquals("GET", httpRequest.method);
        assertEquals("/", httpRequest.path);
        assertEquals("HTTP/1.1", httpRequest.httpVersion);
    }

    @Test
    public void testParsesRequestHeaders() throws IOException {
        String request = "GET /page HTTP/1.1" + CRLF + "Accept: */*" + CRLF + "Content-Type: application/json";
        BufferedReader input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(request.getBytes())));
        HttpRequest httpRequest = requestParser.parseInput(input);
        assertEquals("application/json", httpRequest.headers.get("Content-Type"));
        assertEquals("*/*", httpRequest.headers.get("Accept"));
    }

    @Test
    public void testParsesRequestWithBody() throws IOException {
        String request = "GET /page HTTP/1.1" +
                CRLF +
                "Accept: */*" +
                CRLF +
                "Content-Length: 16" +
                CRLF + CRLF +
                "This is the body";
        BufferedReader input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(request.getBytes())));
        HttpRequest httpRequest = requestParser.parseInput(input);
        assertEquals("16", httpRequest.headers.get("Content-Length"));
        assertEquals("This is the body", httpRequest.body);
    }
}
