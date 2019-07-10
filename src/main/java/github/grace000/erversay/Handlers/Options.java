package github.grace000.erversay.Handlers;

import github.grace000.erversay.Request;
import github.grace000.erversay.ResponseBuilder;

import static github.grace000.erversay.Constants.Headers.OPTIONS_HEADER;
import static github.grace000.erversay.Constants.StatusCodes.NOT_FOUND_STATUS;

public class Options implements Handler{
    public enum AcceptedMethods {
        GET, HEAD, OPTIONS
    }

    public boolean isMethodAllowed(String method) {
        for (AcceptedMethods accepted: AcceptedMethods.values()) {
            return (accepted.name().equals(method));
        }
        return false;
    }

    public String handle(Request request) {
        if (isMethodAllowed(request.method)) {
            return new ResponseBuilder().withHeaders(OPTIONS_HEADER).build();
        } else return new ResponseBuilder().withStatus(NOT_FOUND_STATUS).build();
    }
}
