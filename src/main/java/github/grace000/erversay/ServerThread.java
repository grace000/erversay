package github.grace000.erversay;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Request.RequestParser;
import github.grace000.erversay.Request.RequestReader;
import github.grace000.erversay.Router.RequestRouter;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.Response.ResponseWriter;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private RequestParser requestParser = new RequestParser();
    private RequestRouter requestRouter = new RequestRouter();
    private RequestReader requestReader = new RequestReader();
    private ResponseWriter responseWriter = new ResponseWriter();

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            socketGetInputStream();
            BufferedReader input = requestReader.read(inputStream);
            Request request = requestParser.parse(input);
            Response response = requestRouter.route(request);
            socketGetOutputStream();
            responseWriter.write(outputStream, response);
            System.out.println("Message sent");
            socket.close();
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
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

    private void socketGetOutputStream() {
        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}