package com.github.digitopolis.httpserver.socket;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SocketCreatorTest {
    @Test
    public void testCreatesServerSocket() throws IOException {
        ServerSocket testSocket = SocketCreator.createServerSocket(8080);
        assertNotNull(testSocket);
        testSocket.close();
    }

    @Test
    public void testThrowsErrorWhenUnableToCreateSocket() {
        assertThrows(Exception.class, () -> SocketCreator.createServerSocket(999999));
    }
}
