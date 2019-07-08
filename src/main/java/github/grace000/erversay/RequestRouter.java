package github.grace000.erversay;

import static github.grace000.erversay.Constants.Headers.*;
import static github.grace000.erversay.Constants.Body.*;
import static github.grace000.erversay.Constants.Paths.*;
import static github.grace000.erversay.Constants.StatusCodes.*;

public class RequestRouter {
    private String status;
    private String body;
    private int contentLength;
    private String headers;

    public Response route(Request request) {
        String path = request.path;
        String method = request.method;

        return getResponse(path, method);
    }

    private Response getResponse(String path, String method) {
        if (path.equals(SIMPLE_GET_URI) && method.equals("GET")) {
            getResponseForSimpleGet();
        } else if(path.equals(PIGGLY_URI) && method.equals("GET")) {
            getResponseForPigglyGet();
        } else if(path.equals(METHODS_ONE_URI)) {
            if(isStandardOptionsRequest(method)) {
                getResponseForOptions();
            }
        } else if(path.equals(METHODS_TWO_URI)) {
            if(isStandardOptionsRequest(method) || method.equals("PUT") || method.equals("POST")) {
                getResponseForOptionsTwo();
            }
        } else {
            getResponseForNotFound();
        }
        return new Response(status, body, contentLength, headers);
    }

    private void getResponseForSimpleGet() {
        status = OK_STATUS;
        body = EMPTY_BODY;
    }

    private void getResponseForNotFound() {
        status = NOT_FOUND_STATUS;
        body = EMPTY_BODY;
    }

    private void getResponseForPigglyGet() {
        status = OK_STATUS;
        body = PIGGLY_BODY;
    }

    private void getResponseForOptions() {
        status = OK_STATUS;
        body = EMPTY_BODY;
        headers = OPTIONS_HEADER;
    }

    private void getResponseForOptionsTwo() {
        status = OK_STATUS;
        body = EMPTY_BODY;
        headers = OPTIONS_TWO_HEADER;
    }

    private boolean isStandardOptionsRequest(String method) {
        return method.equals("OPTIONS") || method.equals("GET") || method.equals("HEAD");
    }
}
