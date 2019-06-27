package github.grace000.erversay;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RequestParser {
    public String[] parseRequestLine(String requestLine) {
        if (requestLine == null || requestLine.isEmpty()) {
            System.out.println("REQUEST LINE CANNOT BE PARSED");
            requestLine = "GET /NOT_FOUND HTTP/1.1";
        }

        System.out.println(requestLine);
        return requestLine.split("\\s");
    }

    public String[] parseRequest(String requestString) {
        return requestString.split("\r\n");
    }

    public String getRequestLine(String requestString) {
        return requestString.split("\r\n")[0];
    }

    public String[] getHeaders(String requestString) {
        String[] requestLines = requestString.split("\r\n");
        List<String> requestList = new LinkedList<>(Arrays.asList(requestLines));
        requestList.remove(0);
        requestList.remove(requestList.size() - 1);
        requestList.removeAll(Arrays.asList("", null));

        return requestList.toArray(new String[requestList.size()]);
    }

    public String getBody(String requestString) {
        String[] requestLines = requestString.split("\r\n");
        return requestLines[requestLines.length - 1];
    }
}
