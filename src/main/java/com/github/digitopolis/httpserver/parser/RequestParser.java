package com.github.digitopolis.httpserver.parser;

import com.github.digitopolis.httpserver.request.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class RequestParser {
    private String method;
    private String path;
    private String httpVersion;
    private HashMap<String, String> headers = new HashMap<>();
    private String body;

    public RequestParser() {}

    public HttpRequest parseInput(BufferedReader input) throws IOException {
        parseInitialLine(input);
        parseHeaders(input);
        if (headers.containsKey("Content-Length")) {
            System.out.println("headers includes content length");
            parseBody(input);
        }

        return new HttpRequest.Builder(method)
                .withPath(path)
                .withHttpVersion(httpVersion)
                .withHeaders(headers)
                .withBody(body)
                .build();
    }

    private void parseInitialLine(BufferedReader input) throws IOException {
        String initialLine = input.readLine();
        String[] parsedLine = initialLine.split(" ");
        this.method = parsedLine[0];
        this.path = parsedLine[1];
        this.httpVersion = parsedLine[2];
    }

    private void parseHeaders(BufferedReader input) throws IOException {
        String line;
        while ((line = input.readLine()) != null) {
            System.out.println(line);
            if (line.equals("")) break;
            parseHeader(line);
        }
    }

    private void parseHeader(String line) {
        String[] header = line.split(": ");
        if (header.length == 2) {
            headers.put(header[0], header[1]);
        }
    }

    private void parseBody(BufferedReader input) throws IOException {
        if (headers.get("Content-Length").equals("0")) return;
        System.out.println("parsing body");
        Integer contentLength = Integer.parseInt(headers.get("Content-Length"));
        StringBuilder requestBody = new StringBuilder();
        for (int i = 0; i < contentLength; i++) {
            requestBody.append((char) input.read());
        }
        body = requestBody.toString();
    }
}
