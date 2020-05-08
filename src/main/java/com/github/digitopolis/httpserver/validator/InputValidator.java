package com.github.digitopolis.httpserver.validator;

import com.github.digitopolis.httpserver.cli.CLI;

public class InputValidator {
    private static final CLI cli = new CLI();

    public int validatePort(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            cli.printMessage("Invalid port: " + e.getMessage() + "\nUsing default port(80)");
            return 80;
        }
    }

    public static boolean validGetMethod(String input) {
        String[] initialLine = input.split(" ");
        try {
            String method = initialLine[0];
            String path = initialLine[1];
            String httpVersion = initialLine[2];
            if (method.equals("GET")) {
                return true;
            } else {
                cli.printMessage("Please use GET method");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            cli.printMessage("Invalid format, should be: [METHOD] [PATH] [HTTP VERSION]");
            return false;
        }
    }
}
