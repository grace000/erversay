package github.grace000.erversay;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class RequestParser {



    private String method;
    private String path;
    private String httpVersion;
    private HashMap<String, String> headers = new HashMap<>();
    private String body;

    public Request parse(BufferedReader bufferedReader)  throws IOException {

        parseRequestLine(bufferedReader);
        parseHeaders(bufferedReader);
        parseBody(bufferedReader);

        return new Request(method, path, body);
    }

    private void parseRequestLine(BufferedReader request)  throws IOException {
        String line = request.readLine();

        System.out.println("lines " + line);
        if (line == null) return;

        String[] requestLine = line.split(" ");
        if (requestLine.length != 3) return;


        method = requestLine[0];
        path = requestLine[1];
        httpVersion = requestLine[2];
    }

    private void parseHeaders(BufferedReader request) throws IOException {
        String line;
        while ((line = request.readLine()) != null) {
            if (line.equals("")) break;
            parseHeader(line);
        }
    }

    private void parseHeader(String header) {
        String[] headerPair = header.split(": ");
        if (headerPair.length == 2) {
            String field = headerPair[0];
            String value = headerPair[1];
            headers.put(field, value);
        }
    }

    private void parseBody(BufferedReader request) throws IOException {
        StringBuilder bodyBuilder = new StringBuilder();
        System.out.println(headers);
        System.out.println("Headers tup");
        String messageLength = headers.get("content-length");
        System.out.println(messageLength);
        int length;

        if (messageLength != null) {
            length = Integer.parseInt(messageLength);
            for (int i = 0; i < length; i++) {
                bodyBuilder.append((char) request.read());
            }
        }

        body = bodyBuilder.toString();
        System.out.println("body from parse " + body);
    }
//    private int requestLineIndex = 0;
//    private int methodIndex = 0;
//    private int pathIndex = 1;
//
//    public String handleEmptyRequest(String requestString) {
//        if (requestString == null || requestString.isEmpty()) {
//            System.out.println("REQUEST CANNOT BE PARSED");
//            requestString = "GET /NOT_FOUND HTTP/1.1";
//        }
//        return requestString;
//    }
//
//    public String getRequestLine(String requestString) {
//        return requestString.split("\r\n")[requestLineIndex];
//    }
//
//    public String getMethod(String requestLine) {
//      return requestLine.split("\\s")[methodIndex];
//    }
//
//    public String getPath(String requestLine) {
//        return requestLine.split("\\s")[pathIndex];
//    }
//
//    public String[] getHeaders(String requestString) {
//        String[] requestLines = requestString.split("\r\n");
//        List<String> requestList = new LinkedList<>(Arrays.asList(requestLines));
//        requestList.remove(requestLineIndex);
//        requestList.remove(requestList.size() - 1);
//        requestList.removeAll(Arrays.asList("", null));
//
//        return requestList.toArray(new String[requestList.size()]);
//    }
//
//    public String getBody(String requestString) {
//        String[] requestLines = requestString.split("\r\n");
//        return requestLines[requestLines.length - 1];
//    }

//    public Request parse(String requestString){
//        String checkedRequest = handleEmptyRequest(requestString);
//
//        String requestLine = getRequestLine(checkedRequest);
//        String method = getMethod(requestLine);
//        String path = getPath(requestLine);
//        String body = getBody(checkedRequest);
//        System.out.println(requestString);
//
//        return new Request(method, path, body);
//    }
}
