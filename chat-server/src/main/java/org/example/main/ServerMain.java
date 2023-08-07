package org.example.main;

import org.example.server.ChatServer;

import java.io.IOException;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the PORT you want the server to run on: ");
        int port = scanner.nextInt();
        ChatServer chatServer = new ChatServer(port);
        chatServer.start();
    }
}
