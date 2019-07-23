package github.grace000.erversay.Response;

import static github.grace000.erversay.Constants.StatusCodes.OK_STATUS;

public class ResponseBuilder {
    private String status = OK_STATUS;
    private byte[] body = new byte[0];
    private int contentLength = 0;
    private String headers = "";

    public ResponseBuilder withStatus(String status){
        this.status = status;
        return this;
    }

    public ResponseBuilder withBody(String body) {
        this.body = body.getBytes();
        return this;
    }

    public ResponseBuilder withBody(byte[] body) {
        this.body = body;
        return this;
    }

    public ResponseBuilder withContentLength(int contentLength, byte[] body) {
        contentLength = body.length;
        this.contentLength = contentLength;
        return this;
    }

    public ResponseBuilder withHeaders(String headers) {
        this.headers = headers;
        return this;
    }

    public Response build(){
        return new Response(this.status, this.body, this.contentLength, this.headers);
    }
}
