package github.grace000.erversay;

import java.util.HashMap;

import static github.grace000.erversay.Constants.*;

public class RequestRouter {
    private String code;
    private String status;
    private String body;

    public Response route(Request request) {
        String method = request.method;
        String path = request.path;

        return getResponse(method, path);
    }

    private Response getResponse(String method, String path) {
        switch(path) {
            case SIMPLE_GET_URI:
                getResponseForSimpleGet();
                break;
            case PIGGLY_URI:
                getResponseForPigglyGet();
                break;
            default:
                getResponseForNotFound();
        }
        return new Response(code, status, body);
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
}
