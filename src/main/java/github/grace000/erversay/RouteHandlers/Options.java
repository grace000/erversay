package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.Response.ResponseBuilder;

import java.util.LinkedList;
import java.util.List;

import static github.grace000.erversay.Constants.Headers.OPTIONS_HEADER;
import static github.grace000.erversay.Constants.StatusCodes.NOT_ALLOWED_STATUS;
import static github.grace000.erversay.Constants.StatusCodes.OK_STATUS;

public class Options implements RouteHandler{
    private ResponseBuilder responseBuilder = new ResponseBuilder();

    public enum AcceptedMethods {
        GET, HEAD, OPTIONS
    }

    public boolean isMethodAllowed(String method) {
        for (AcceptedMethods accepted: AcceptedMethods.values()) {
            if (accepted.name().equals(method)){
                return true;
            }
        }
        return false;
    }

    public Response handle(Request request) {
        if (isMethodAllowed(request.method)) {
            return responseBuilder
                    .withHeaders("Allow: " + getMethods())
                    .withStatus(OK_STATUS.code)
                    .build();
        }
        else {
            return responseBuilder
                    .withHeaders("Allow: " + getMethods())
                    .withStatus(NOT_ALLOWED_STATUS.code)
                    .build();
        }
    }

    private String getMethods() {
        List<String> methods = new LinkedList<>();

        for(AcceptedMethods acceptedMethod : AcceptedMethods.values()) {
            methods.add(acceptedMethod.name());
        }
        return String.join(", ", methods);
    }
}
