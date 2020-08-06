package com.github.digitopolis.httpserver;

import com.github.digitopolis.httpserver.cli.CLI;
import com.github.digitopolis.httpserver.socket.SocketCreator;

import java.net.ServerSocket;

public class Server {
    private final int port;
    private final CLI cli = new CLI();

    public Server(int port) {
        this.port = port;
    }
    public void start() {
        try (
                ServerSocket serverSocket = SocketCreator.createServerSocket(port)
                ){
            cli.printMessage("Server started at port " + port);
        }catch (Exception e) {
            cli.printMessage(e.getMessage());
        }
    }
}
