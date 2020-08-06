package com.github.digitopolis.httpserver.response;

public class ResponseFormatter {
    private static final String CRLF = "\r\n";

    public static String format(String method, HTTPResponse response) {
        switch (method) {
            case "GET":
                return formatGet(response);
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
}
