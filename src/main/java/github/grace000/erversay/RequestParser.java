package github.grace000.erversay;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RequestParser {
    private int requestLineIndex = 0;
    private int methodIndex = 0;
    private int pathIndex = 1;

    public String handleEmptyRequest(String requestString) {
        if (requestString == null || requestString.isEmpty()) {
            System.out.println("REQUEST CANNOT BE PARSED");
            requestString = "GET /NOT_FOUND HTTP/1.1";
        }
        return requestString;
    }

    public String getRequestLine(String requestString) {
        return requestString.split("\r\n")[requestLineIndex];
    }

    public String getMethod(String requestLine) {
      return requestLine.split("\\s")[methodIndex];
    }

    public String getPath(String requestLine) {
        return requestLine.split("\\s")[pathIndex];
    }

    public String[] getHeaders(String requestString) {
        String[] requestLines = requestString.split("\r\n");
        List<String> requestList = new LinkedList<>(Arrays.asList(requestLines));
        requestList.remove(requestLineIndex);
        requestList.remove(requestList.size() - 1);
        requestList.removeAll(Arrays.asList("", null));

        return requestList.toArray(new String[requestList.size()]);
    }

    public String getBody(String requestString) {
        String[] requestLines = requestString.split("\r\n");
        return requestLines[requestLines.length - 1];
    }

    public Request parse(String requestString){
        String checkedRequest = handleEmptyRequest(requestString);

        String requestLine = getRequestLine(checkedRequest);
        String method = getMethod(requestLine);
        String path = getPath(requestLine);
        String body = getBody(checkedRequest);

        return new Request(method, path, body);
    }
}
