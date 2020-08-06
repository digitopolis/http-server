package com.github.digitopolis.httpserver.client;

import com.github.digitopolis.httpserver.socket.SocketCreator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientTest {
    @Test
    public void testConnectsToServerOnGivenPort() throws IOException {
        ServerSocket testServer = SocketCreator.createServerSocket(8080);
        final Client client = new Client();
        assertDoesNotThrow(() -> client.connect("127.0.0.1", 8080));
        testServer.close();
    }

    @Test
    public void throwsErrorWhenServerNotAvailable() throws IOException {
        final Client client = new Client();
        assertThrows(IOException.class, () -> client.connect("127.0.0.1", 5000));
    }
}
