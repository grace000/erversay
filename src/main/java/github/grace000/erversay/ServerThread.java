package github.grace000.erversay;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    private Socket socket;
    private InputStream inputStream;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        socketGetInputStream();
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
        String response = new Response().build();
        writeResponse(socket, response);

        System.out.println("Message sent");
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void writeResponse(Socket socket, String response) {
        try {
            OutputStream outputFromServer = socket.getOutputStream();
            PrintWriter output = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);
            output.println(response);
            output.flush();
        } catch (IOException e) {
            System.out.println("WRITE RESPONSE ERROR");
            e.printStackTrace();
        }
    }

    private void socketGetInputStream() {
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}