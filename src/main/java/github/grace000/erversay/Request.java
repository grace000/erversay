package github.grace000.erversay;

public class Request {
    public String method;
    public String path;
    public String body;

    public Request(String method, String path, String body) {
        this.method = method;
        this.path = path;
        this.body = body;
    }
}
