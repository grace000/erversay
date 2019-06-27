package github.grace000.erversay;

import java.util.HashMap;

public class ResponseBuilder {
    private String code = "200";
    public String protocol = "HTTP/1.1";
    private String status = "OK";

    public ResponseBuilder withCode(String code){
        this.code = code;
        return this;
    }

    public ResponseBuilder withStatus(String status){
        this.status = status;
        return this;
    }

    public String getResponse(HashMap routeTable) {
        String code = (String) routeTable.get("code");
        String status = (String) routeTable.get("status");
        return withCode(code).withStatus(status).build();
    }

    public String build(){
        return "HTTP/1.1 " + this.code + " " + this.status + "\r\nContent-Length: 0\r\n\r\n";
    }

}
