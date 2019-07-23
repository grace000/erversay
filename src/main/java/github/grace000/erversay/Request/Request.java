package github.grace000.erversay.Request;

import java.util.HashMap;

public class Request {
    public String method;
    public String path;
    public String body;
    public HashMap<String, String> headers;

    public Request(String method, String path, HashMap<String, String> headers, String body) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.body = body;
    }
}
