package com.github.digitopolis.httpserver.parser;

import com.github.digitopolis.httpserver.request.HttpRequest;

public class RequestParser {
    public static HttpRequest parseInput(String input) {
        String[] initialLine = input.split(" ");
        String method = initialLine[0];
        String path = initialLine[1];
        String httpVersion = initialLine[2];
        return new HttpRequest(method, path, httpVersion);
    }
}
