package com.github.digitopolis.httpserver.client;

import com.github.digitopolis.httpserver.cli.CLI;
import com.github.digitopolis.httpserver.socket.SocketCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static CLI cli = new CLI();

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
