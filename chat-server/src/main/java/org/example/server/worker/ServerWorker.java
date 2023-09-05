package org.example.server.worker;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ServerWorker extends Thread{
    private final Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private final Map<String, ServerWorker> serverWorkerMap;
    private String clientName;

    public ServerWorker(Socket clientSocket, Map<String, ServerWorker> serverWorkerMap){
        this.clientSocket = clientSocket;
        this.serverWorkerMap = serverWorkerMap;
    }

    @Override
    public void run() {
        try {
            initializeServerWorker();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeServerWorker() throws IOException {
        OutputStream outputStream = clientSocket.getOutputStream();
        this.writer = new PrintWriter(outputStream, true);
        InputStream inputStream = clientSocket.getInputStream();
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        parseClientToken();
    }

    public void parseClientToken() throws IOException {
        String line;
        while ((line = this.reader.readLine()) != null) {
            String[] tokens = line.split("\\s");
            redirectTokens(tokens);
        }
    }

    private void redirectTokens(String[] tokens) {
        System.out.println(tokens[0]);
        if (tokens[0].equalsIgnoreCase("username")) {
            this.clientName = tokens[1];
            System.out.println("Client name is " + this.clientName);
            this.serverWorkerMap.put(this.clientName, this);
            writer.println("Registration Completed!!");
        }
    }

    private void closeSocketConnection() throws IOException {
        this.reader.close();
        this.writer.close();
        this.clientSocket.close();
    }
}
