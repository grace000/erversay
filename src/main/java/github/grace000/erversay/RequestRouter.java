package github.grace000.erversay;

import github.grace000.erversay.RouteHandlers.RouteHandler;

import java.util.HashMap;

import static github.grace000.erversay.Constants.StatusCodes.NOT_FOUND_STATUS;

public class RequestRouter {
    private ResponseBuilder responseBuilder = new ResponseBuilder();


    public String route(Request request, HashMap routes) {
        return handleRequest(request, routes);
    }

    private String handleRequest(Request request, HashMap routes) {
        String path = request.path;

        if (!routes.containsKey(path)) {
            return handleUnknownPath();
        }

        RouteHandler routeHandler = (RouteHandler) routes.get(path);
        return routeHandler.handle(request);
    }

    private String handleUnknownPath() {
        return responseBuilder.withStatus(NOT_FOUND_STATUS).build();
    }
}
