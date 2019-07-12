package github.grace000.erversay;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import static github.grace000.erversay.Constants.HTTPLines.SP;
import static github.grace000.erversay.Constants.Headers.CONTENT_LENGTH;

public class RequestParser {
    private int methodIndex = 0;
    private int pathIndex = 1;
    private String method;
    private String path;
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

        if (line == null) return;

        String[] requestLine = line.split(SP);
        if (requestLine.length != 3) return;

        method = requestLine[methodIndex];
        path = requestLine[pathIndex];
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
        String messageLength = headers.get(CONTENT_LENGTH);

        int length;

        if (messageLength != null) {
            length = Integer.parseInt(messageLength);
            for (int i = 0; i < length; i++) {
                bodyBuilder.append((char) request.read());
            }
        }
        body = bodyBuilder.toString();
    }
}
