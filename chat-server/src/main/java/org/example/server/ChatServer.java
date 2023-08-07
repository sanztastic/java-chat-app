package org.example.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer extends Thread{

   private final int serverPort;


    public ChatServer(int port) throws IOException {
       this.serverPort = port;
    }

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(serverPort)){
            while(true){
                System.out.println("About to accept the connection");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted the connection from the client");
                OutputStream outputStream = clientSocket.getOutputStream();
                for(int i=0;i<15;i++){
                    outputStream.write("Hello World!!\n".getBytes());
                    Thread.sleep(1000);
                }
                outputStream.flush();
                clientSocket.close();
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
