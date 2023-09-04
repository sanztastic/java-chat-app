package org.example.server;

import org.example.server.worker.ServerWorker;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer extends Thread{
    private final int serverPort;
    private Map<String,ServerWorker> serverWorkerMap;

    private int count = 0;

    public ChatServer(int port) throws IOException {
       this.serverPort = port;
       serverWorkerMap = new HashMap<>();
    }

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(serverPort)){
            while(true){
                System.out.println("About to accept the connection");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted the connection from the client");
                ServerWorker serverWorker = new ServerWorker(clientSocket,serverWorkerMap);
                serverWorker.start();

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
