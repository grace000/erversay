package github.grace000.erversay;

import static github.grace000.erversay.Constants.*;

public class RequestRouter {
    private String code;
    private String status;
    private String body;
    private int contentLength;
    private String headers;

    public Response route(Request request) {
        String path = request.path;

        return getResponse(path);
    }

    private Response getResponse(String path) {
        switch(path) {
            case SIMPLE_GET_URI:
                getResponseForSimpleGet();
                break;
            case PIGGLY_URI:
                getResponseForPigglyGet();
                break;
            case "/methods_options":
                getResponseForOptions();
                break;
            default:
                getResponseForNotFound();
        }
        return new Response(code, status, body, contentLength, headers);
    }

    private void getResponseForSimpleGet() {
        code = DEFAULT_CODE;
        status = DEFAULT_STATUS;
        body = EMPTY_BODY;
    }

    private void getResponseForNotFound() {
        code = NOT_FOUND_CODE;
        status = NOT_FOUND_STATUS;
        body = EMPTY_BODY;
    }

    private void getResponseForPigglyGet() {
        code = DEFAULT_CODE;
        status = DEFAULT_STATUS;
        body = PIGGLY_BODY;
    }

    private void getResponseForOptions() {
        code = DEFAULT_CODE;
        status = DEFAULT_STATUS;
        body = EMPTY_BODY;
        headers = "Allow: OPTIONS, GET, HEAD";
    }
}
