package github.grace000.erversay;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    private Socket socket;
    private InputStream inputStream;
    private RequestReader reader;
    private ResponseBuilder responseBuilder;

    public ServerThread(Socket socket) {
        this.socket = socket;
        reader = new RequestReader();
        responseBuilder = new ResponseBuilder();
    }

    public void run() {
        String input = convertInputToString();
        Request request = new RequestParser().parse(input);
        Response routedRequest = new RequestRouter().route(request);
        String response = responseBuilder.getResponse(routedRequest);
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

    public String convertInputToString() {
        socketGetInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String convertedInput = "";
        try {
            convertedInput = reader.readRequest(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedInput;
    }

    private void socketGetInputStream() {
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}