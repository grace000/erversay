package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.Response.ResponseBuilder;

import static github.grace000.erversay.Constants.Headers.REDIRECT_HEADER;
import static github.grace000.erversay.Constants.StatusCodes.*;

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

    public Response handle(Request request) {
        if (isMethodAllowed(request.method)) {
            return responseBuilder
                    .withHeaders(REDIRECT_HEADER)
                    .withStatus(REDIRECT_STATUS)
                    .build();
        }
        else return responseBuilder
                .withStatus(NOT_FOUND_STATUS)
                .build();
    }
}
