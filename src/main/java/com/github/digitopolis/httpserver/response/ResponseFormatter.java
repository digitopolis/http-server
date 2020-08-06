package com.github.digitopolis.httpserver.response;

public class ResponseFormatter {
    private static final String CRLF = "\r\n";

    public static String format(String method, HTTPResponse response) {
        if (response.statusCode.equals("404")) {
            return response.getStatusLine() + CRLF;
        }
        switch (method) {
            case "GET":
            case "POST":
                return formatGet(response);
            case "HEAD":
                return formatHead(response);
        }
        return "Something went wrong";
    }

    private static String formatGet(HTTPResponse response) {
        StringBuilder formattedGet = new StringBuilder();
        formattedGet.append(response.getStatusLine());
        formattedGet.append(CRLF);
        formattedGet.append(response.getContentType());
        formattedGet.append(CRLF);
        formattedGet.append(CRLF);
        formattedGet.append(response.getBody());
        return formattedGet.toString();
    }

    private static String formatHead(HTTPResponse response) {
        StringBuilder formattedHead = new StringBuilder();
        formattedHead.append(response.getStatusLine());
        formattedHead.append(CRLF);
        return formattedHead.toString();
    }
}
