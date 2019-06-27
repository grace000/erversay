package github.grace000.erversay;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;


public class Server {
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
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