package com.github.digitopolis.httpserver;

import com.github.digitopolis.httpserver.cli.CLI;
import com.github.digitopolis.httpserver.validator.InputValidator;

public class App
{
    private static final CLI cli = new CLI();
    private static final InputValidator inputValidator = new InputValidator();

    public static void main( String[] args )
    {
        startServer();
    }

    public static void startServer() {
        cli.printMessage("Enter a port number to start an echo server:");
        String port = cli.getInput();
        Server server = new Server(inputValidator.validatePort(port));
        server.start();
    }
}
