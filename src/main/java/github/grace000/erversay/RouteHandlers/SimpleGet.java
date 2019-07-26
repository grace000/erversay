package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.Response.ResponseBuilder;

import static github.grace000.erversay.Constants.Headers.CONTENT_LENGTH;
import static github.grace000.erversay.Constants.Headers.OPTIONS_HEADER;
import static github.grace000.erversay.Constants.StatusCodes.NOT_ALLOWED_STATUS;

public class SimpleGet implements RouteHandler {
    private ResponseBuilder responseBuilder = new ResponseBuilder();

    public enum AcceptedMethods {
        GET, HEAD, OPTIONS
    }

    public boolean isMethodAllowed(String method) {
        for (AcceptedMethods acceptedMethods : AcceptedMethods.values()) {
            if (acceptedMethods.name().equals(method)) {
                return true;
            }
        }
        return false;
    }

    public Response handle(Request request) {
        if (isMethodAllowed(request.method)) {
                return responseBuilder
                        .withHeaders(CONTENT_LENGTH + ": 0")
                        .build();
        }
        else return responseBuilder
                .withHeaders(OPTIONS_HEADER)
                .withStatus(NOT_ALLOWED_STATUS.code)
                .build();
    }
}
