package com.github.digitopolis.httpserver.validator;

import com.github.digitopolis.httpserver.cli.CLI;

public class InputValidator {
    private static final CLI cli = new CLI();

    public int validatePort(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            cli.printMessage("Invalid port: " + e.getMessage() + "\nUsing default port(5000)");
            return 5000;
        }
    }
}
