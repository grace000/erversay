package github.grace000.erversay;

import github.grace000.erversay.Constants.StatusCodes;

public class Response {
    public StatusCodes status;
    public String body;
    public int contentLength;
    public String headers;

    public Response(StatusCodes status, String body, int contentLength, String headers) {
        this.status = status;
        this.body = body;
        this.contentLength = contentLength;
        this.headers = headers;
    }
}
