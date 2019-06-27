package github.grace000.erversay;

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
        requestString.split("\r\n");
    }
}
