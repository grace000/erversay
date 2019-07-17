package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request;
import github.grace000.erversay.ResponseBuilder;

import static github.grace000.erversay.Constants.Headers.REDIRECT_HEADER;
import static github.grace000.erversay.Constants.StatusCodes.NOT_FOUND_STATUS;
import static github.grace000.erversay.Constants.StatusCodes.REDIRECT_STATUS;

public class Redirect implements RouteHandler {
    private ResponseBuilder responseBuilder = new ResponseBuilder();

    public enum AcceptedMethods {
        GET
    }

    public boolean isMethodAllowed(String method) {
        for (AcceptedMethods acceptedMethods : AcceptedMethods.values()) {
            if (acceptedMethods.name().equals(method)) {
                return true;
            }
        }
        return false;
    }

    public String handle(Request request) {
        if (isMethodAllowed(request.method)) {
            return responseBuilder
                    .withHeaders(REDIRECT_HEADER)
                    .withStatus(REDIRECT_STATUS)
                    .build();
        } else return responseBuilder
                .withStatus(NOT_FOUND_STATUS)
                .build();
    }
}
