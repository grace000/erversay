package github.grace000.erversay;

public class Response {
    public String status;
    public String body;
    public int contentLength;
    public String headers;

    public Response(String status, String body, int contentLength, String headers) {
        this.status = status;
        this.body = body;
        this.contentLength = contentLength;
        this.headers = headers;
    }
}
