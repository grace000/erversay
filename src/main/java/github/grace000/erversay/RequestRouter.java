package github.grace000.erversay;

import github.grace000.erversay.Constants.StatusCodes;

import static github.grace000.erversay.Constants.HTTPLines.EMPTY_BODY;
import static github.grace000.erversay.Constants.Headers.OPTIONS_HEADER;
import static github.grace000.erversay.Constants.Headers.PIGGLY_BODY;
import static github.grace000.erversay.Constants.Paths.*;
import static github.grace000.erversay.Constants.StatusCodes.DEFAULT_STATUS;
import static github.grace000.erversay.Constants.StatusCodes.NOT_FOUND_STATUS;

public class RequestRouter {
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
            case METHODS_ONE_URI:
                getResponseForOptions();
                break;
            default:
                getResponseForNotFound();
        }
        return new Response(status, body, contentLength, headers);
    }

    private void getResponseForSimpleGet() {
        status = DEFAULT_STATUS;
        body = EMPTY_BODY;
    }

    private void getResponseForNotFound() {
        status = NOT_FOUND_STATUS;
        body = EMPTY_BODY;
    }

    private void getResponseForPigglyGet() {
        status = DEFAULT_STATUS;
        body = PIGGLY_BODY;
    }

    private void getResponseForOptions() {
        status = DEFAULT_STATUS;
        body = EMPTY_BODY;
        headers = OPTIONS_HEADER;
    }
}
