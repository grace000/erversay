package github.grace000.erversay;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RequestParser {
    public String handleEmptyRequest(String requestString) {
        if (requestString == null || requestString.isEmpty()) {
            System.out.println("REQUEST LINE CANNOT BE PARSED");
            requestString = "GET /NOT_FOUND HTTP/1.1";
        }
        return requestString;
    }

    public String getRequestLine(String requestString) {
        return requestString.split("\r\n")[0];
    }

    public String getMethod(String requestLine) {
      return requestLine.split("\\s")[0];
    }

    public String getPath(String requestLine) {
        return requestLine.split("\\s")[1];
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

    public Request parse(String requestString){
        String checkedRequest = handleEmptyRequest(requestString);

        String requestLine = getRequestLine(checkedRequest);
        String method = getMethod(requestLine);
        String path = getPath(requestLine);
        String body = getBody(checkedRequest);

        return new Request(method, path, body);
    }
}
