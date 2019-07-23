package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.Response.ResponseBuilder;

import static github.grace000.erversay.Constants.Headers.CONTENT_LENGTH;
import static github.grace000.erversay.Constants.Headers.NOT_ALLOWED_HEADER;
import static github.grace000.erversay.Constants.StatusCodes.NOT_ALLOWED_STATUS;

public class Post implements RouteHandler {
    private ResponseBuilder responseBuilder = new ResponseBuilder();

    public boolean isMethodAllowed(String method) {
        return method.equals("POST");
    }

    public Response handle(Request request) {
        System.out.println(request.body);
        if (isMethodAllowed(request.method)) {
            return responseBuilder
                    .withHeaders(CONTENT_LENGTH + ": " + request.body.getBytes().length)
                    .withBody(request.body)
                    .withContentLength(request.body.length(), request.body.getBytes())
                    .build();
        }
        else return responseBuilder
                .withHeaders(NOT_ALLOWED_HEADER)
                .withStatus(NOT_ALLOWED_STATUS)
                .build();
    }
}
