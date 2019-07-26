package github.grace000.erversay.Response;

public class Response {
    public String status;
    public byte[] body;
    public int contentLength;
    public String headers;

    public Response(String status, byte[] body, int contentLength, String headers) {
        this.status = status;
        this.body = body;
        this.contentLength = contentLength;
        this.headers = headers;
    }
}
