package com.github.digitopolis.httpserver.client;

import com.github.digitopolis.httpserver.cli.CLI;
import com.github.digitopolis.httpserver.socket.SocketCreator;
import com.github.digitopolis.httpserver.validator.InputValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static CLI cli = new CLI();
    private static InputValidator inputValidator = new InputValidator();

    public static void main(String[] args) {
        cli.printMessage("Enter port number: ");
        String port = cli.getInput();
        try {
            connect("127.0.0.1", inputValidator.validatePort(port));
        } catch (Exception e) {
            cli.printMessage("Please try again on an available port");
        }
    }

    public static void connect(String address, int port) throws IOException {
        try (
                Socket clientSocket = SocketCreator.createClientSocket(address, port);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ){
            cli.printMessage("Client connected at " + address + " on port " + port);
        } catch (Exception e) {
            cli.printMessage("Unable to connect at port " + port + ": " + e.getMessage());
            throw e;
        }
    }
}
