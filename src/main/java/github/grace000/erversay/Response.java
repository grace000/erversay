package github.grace000.erversay;

public class Response {
    public String code;
    public String status;
    public String body;

    public Response(String code, String status, String body) {
        this.code = code;
        this.status = status;
        this.body = body;
    }
}
