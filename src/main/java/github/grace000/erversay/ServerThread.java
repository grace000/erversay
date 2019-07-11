package github.grace000.erversay;

import github.grace000.erversay.Handlers.Handler;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
        BufferedReader input = convertInputToString();
        Request request = null;
        try {
            request = new RequestParser().parse(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Handler> routes = new Routes().routes;
        String response = new RequestRouter().route(request, routes);
        writeResponse(socket, response);

        System.out.println("method " + request.method);
        System.out.println("path "+request.path);
        System.out.println("body "+request.body);

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

//    public LinkedHashMap convertInputToString() {
//        socketGetInputStream();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        LinkedHashMap convertedInput = null;
//        try {
//            convertedInput = reader.readRequest(bufferedReader);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return convertedInput;
//    }

    public BufferedReader convertInputToString() {
        socketGetInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        return bufferedReader;
    }

    private void socketGetInputStream() {
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}