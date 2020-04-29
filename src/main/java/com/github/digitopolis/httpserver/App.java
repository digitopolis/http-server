package com.github.digitopolis.httpserver;

import com.github.digitopolis.httpserver.cli.CLI;

public class App
{
    private static final CLI cli = new CLI();

    public static void main( String[] args )
    {
        startServer();
    }

    public static void startServer() {
        cli.printMessage("Enter a port number to start an echo server:");
        String port = cli.getInput();
        Server server = new Server(Integer.parseInt(port));
        server.start();
    }
}
