package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request;
import github.grace000.erversay.ResponseBuilder;

import java.util.Arrays;

import static github.grace000.erversay.Constants.Headers.NOT_ALLOWED_HEADER;
import static github.grace000.erversay.Constants.StatusCodes.*;

public class NotAllowed implements RouteHandler {
    private ResponseBuilder responseBuilder = new ResponseBuilder();

    private enum AllowedMethods {
        HEAD, OPTIONS
    }

    public boolean isMethodAllowed(String method) {
        for (AllowedMethods allowedMethods : AllowedMethods.values()) {
            if (allowedMethods.name().equals(method)) {
                return true;
            }
        }
        return false;
    }

    public String handle(Request request) {
        if (isMethodAllowed(request.method)) {
            return responseBuilder
                    .build();
        } else return responseBuilder
                .withHeaders(NOT_ALLOWED_HEADER)
                .withStatus(NOT_ALLOWED_STATUS)
                .build();
    }
}
