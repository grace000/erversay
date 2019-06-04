package github.grace000.erversay;

import java.net.*;
import java.io.*;


public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8009)) {
            System.out.println("Server running ...");

            while (true) {
                Socket connectionSocket = serverSocket.accept();
                System.out.println("New client connected" + connectionSocket.getRemoteSocketAddress());
                new ServerThread(connectionSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}