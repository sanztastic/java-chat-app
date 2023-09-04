package org.example.server.worker;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class ServerWorker extends Thread{
    private final Socket clientSocket;
    private final Map<String, ServerWorker> serverWorkerMap;
    private String clientName;

    public ServerWorker(Socket clientSocket, Map<String, ServerWorker> serverWorkerMap){
        this.clientSocket = clientSocket;
        this.serverWorkerMap = serverWorkerMap;
    }

    @Override
    public void run() {
        super.run();
    }
}
