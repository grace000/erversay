package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.Response.ResponseBuilder;

import static github.grace000.erversay.Constants.Headers.CONTENT_LENGTH;
import static github.grace000.erversay.Constants.Headers.PUT_HEADER;
import static github.grace000.erversay.Constants.StatusCodes.NOT_ALLOWED_STATUS;

public class Put implements RouteHandler{
    private ResponseBuilder responseBuilder = new ResponseBuilder();

    public enum AcceptedMethods {
       GET, PUT
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
                    .withHeaders(CONTENT_LENGTH + ": " + request.body.getBytes().length)
                    .withBody(request.body)
                    .withContentLength(request.body.length(), request.body.getBytes())
                    .build();
        }
        else return responseBuilder
                .withHeaders(PUT_HEADER)
                .withStatus(NOT_ALLOWED_STATUS)
                .build();
    }
}
