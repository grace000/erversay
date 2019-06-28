package github.grace000.erversay;

import static github.grace000.erversay.Constants.*;

public class ResponseBuilder {
    private String code = DEFAULT_CODE;
    private String status = DEFAULT_STATUS;
    private String body = " ";
    private int contentLength = 0;

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

    public ResponseBuilder withContentLength(int contentLength, String body) {
        contentLength = body.getBytes().length;
        this.contentLength = contentLength;
        return this;
    }

    public String getResponse(Response response) {
        return withCode(response.code).withStatus(response.status).withBody(response.body).withContentLength(response.contentLength, response.body).build();
    }

    public String build(){
        return DEFAULT_VERSION + SP + this.code + SP + this.status + CRLF + "Content-Length: " + this.contentLength + DOUBLE_LINE_FEED + this.body;
    }
}
