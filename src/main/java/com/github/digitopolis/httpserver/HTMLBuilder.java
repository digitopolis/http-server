package com.github.digitopolis.httpserver;

public class HTMLBuilder {
    public static String addBody(String content) {
        return "<html><body><h1>" + content + "</h1></body></html>";
    }
}
