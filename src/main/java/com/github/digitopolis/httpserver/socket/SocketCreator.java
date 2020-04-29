package com.github.digitopolis.httpserver.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketCreator {
    public static ServerSocket createServerSocket(int port) throws IOException {
        return new ServerSocket(port);
    }

    public static Socket createServerClientSocket(ServerSocket serverSocket) throws IOException {
        return serverSocket.accept();
    }

    public static Socket createClientSocket(String address, int port) throws IOException {
        return new Socket(address, port);
    }
}
