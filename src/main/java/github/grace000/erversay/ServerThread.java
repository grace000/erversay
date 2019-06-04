package github.grace000.erversay;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {

            OutputStream outputFromServer = socket.getOutputStream();
            PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);


            serverPrintOut.println("HTTP/1.1 200 OK");
            serverPrintOut.println("Content-Length: 0");
            serverPrintOut.println("");
            serverPrintOut.flush();

            System.out.println("Message sent");

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

}