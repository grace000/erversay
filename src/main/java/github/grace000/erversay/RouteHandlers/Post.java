package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request;
import github.grace000.erversay.ResponseBuilder;

import static github.grace000.erversay.Constants.StatusCodes.NOT_FOUND_STATUS;

public class Post implements RouteHandler {
    private ResponseBuilder responseBuilder = new ResponseBuilder();

    public boolean isMethodAllowed(String method) {
        return method.equals("POST");
    }

    public String handle(Request request) {
        if (isMethodAllowed(request.method)) {
            return responseBuilder
                    .withBody(request.body)
                    .withContentLength(request.body.length(), request.body)
                    .build();
        } else return responseBuilder
                .withStatus(NOT_FOUND_STATUS)
                .build();
    }
}
