package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request;
import github.grace000.erversay.ResponseBuilder;

import java.util.Arrays;

import static github.grace000.erversay.Constants.StatusCodes.NOT_FOUND_STATUS;

public class SimpleGet implements RouteHandler{
    public enum AcceptedMethods {
        GET, HEAD, OPTIONS
    }

    public boolean isMethodAllowed(String method) {
        return Arrays.stream(AcceptedMethods.values())
                .findFirst().filter(accepted -> (accepted.name().equals(method))).isPresent();
    }

    public String handle(Request request) {
        if (isMethodAllowed(request.method)) {
                return new ResponseBuilder().build();
        } else return new ResponseBuilder()
                .withStatus(NOT_FOUND_STATUS)
                .build();
    }
}
