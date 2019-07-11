package github.grace000.erversay;

import github.grace000.erversay.Handlers.Handler;

import java.io.*;
import java.net.*;
import java.util.HashMap;

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
        BufferedReader unparsedRequest = readRequest();
        Request request = null;
        try {
            request = new RequestParser().parse(unparsedRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        createResponse(request);

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createResponse(Request request) {
        HashMap<String, Handler> routes = new Routes().routes;
        String response = new RequestRouter().route(request, routes);
        writeResponse(socket, response);
        System.out.println("Message sent");
    }

    private void writeResponse(Socket socket, String response) {
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


    private BufferedReader readRequest() {
        socketGetInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private void socketGetInputStream() {
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}