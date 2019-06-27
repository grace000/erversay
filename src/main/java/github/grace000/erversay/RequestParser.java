package github.grace000.erversay;

public class RequestParser {
    public String[] parse(String requestLine) {
        if (requestLine == null || requestLine.isEmpty()) {
            System.out.println("REQUEST LINE CANNOT BE PARSED");
            requestLine = "/GET NOT_FOUND HTTP/1.1";
        }

        System.out.println(requestLine);
        return requestLine.split("\\s");
    }
}
