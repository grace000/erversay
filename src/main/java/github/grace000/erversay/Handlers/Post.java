package github.grace000.erversay.Handlers;

import github.grace000.erversay.Request;
import github.grace000.erversay.ResponseBuilder;

import static github.grace000.erversay.Constants.StatusCodes.NOT_FOUND_STATUS;

public class Post implements Handler {
    public boolean isMethodAllowed(String method) {
        return method.equals("POST");
    }

    public String handle(Request request) {
        if (isMethodAllowed(request.method)) {
            return new ResponseBuilder()
                    .withBody(request.body)
                    .withContentLength(request.body.length(), request.body)
                    .build();
        } else return new ResponseBuilder()
                .withStatus(NOT_FOUND_STATUS)
                .build();
    }
}
