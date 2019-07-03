package github.grace000.erversay;

import github.grace000.erversay.Constants.StatusCodes;

import static github.grace000.erversay.Constants.HTTPLines.*;

public class ResponseBuilder {
    private StatusCodes status;
    private String body = " ";
    private int contentLength = 0;
    private String headers = "";

    public ResponseBuilder withStatus(StatusCodes status){
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

    public ResponseBuilder withHeaders(String headers) {
        this.headers = CRLF + headers;
        return this;
    }

    public String getResponse(Response response) {
        return withStatus(response.status).withBody(response.body).withContentLength(response.contentLength, response.body).withHeaders(response.headers).build();
    }

    public String build(){
        return DEFAULT_VERSION + SP + this.status + this.headers + CRLF + "Content-Length: " + this.contentLength + DOUBLE_LINE_FEED + this.body;
    }
}
