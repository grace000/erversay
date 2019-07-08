package github.grace000.erversay;

public class Response {
    public String code;
    public String status;
    public String body;
    public int contentLength;

    public Response(String code, String status, String body, int contentLength) {
        this.code = code;
        this.status = status;
        this.body = body;
        this.contentLength = contentLength;
    }
}
