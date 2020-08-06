package com.github.digitopolis.httpserver.socket;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketCreator {
    public static ServerSocket createServerSocket(int port) throws IOException {
        return new ServerSocket(port);
    }
}
