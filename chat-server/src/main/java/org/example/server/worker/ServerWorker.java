package org.example.server.worker;

import java.io.*;
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
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream,true);
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while(true){
                while((line= reader.readLine())!=null){
                    String[] tokens = line.split("\\s");
                    System.out.println(tokens[0]);
                    if(tokens[0].equalsIgnoreCase("username")){
                        this.clientName = tokens[1];
                        System.out.println("Client name is "+this.clientName);
                        this.serverWorkerMap.put(this.clientName,this);
                        System.out.println("Registration Completed!!");
                        writer.println("Registration Completed!!");
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "ServerWorker{" +
                "clientSocket=" + clientSocket +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
