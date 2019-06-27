package github.grace000.erversay;

import java.util.HashMap;

import static github.grace000.erversay.Constants.*;

public class ResponseBuilder {
    private String code = "200";
    private String status = "OK";
    private String body = " ";

    public ResponseBuilder withCode(String code){
        this.code = code;
        return this;
    }

    public ResponseBuilder withStatus(String status){
        this.status = status;
        return this;
    }

    public ResponseBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public String getResponse(HashMap routeTable) {
        String code = (String) routeTable.get("code");
        String status = (String) routeTable.get("status");
        return withCode(code).withStatus(status).build();
    }

    public String build(){
        return DEFAULT_VERSION + SP + this.code + SP + this.status + CRLF + "Content-Length: 0" + DOUBLE_LINE_FEED + this.body;
    }

}
