package org.example.main;

import org.example.server.ChatServer;

import java.io.IOException;
import java.util.Scanner;

public class ServerMain {

    private static final int SERVER_PORT = 6666;
    public static void main(String[] args) throws IOException {
        ChatServer chatServer = new ChatServer(SERVER_PORT);
        chatServer.start();
    }
}
