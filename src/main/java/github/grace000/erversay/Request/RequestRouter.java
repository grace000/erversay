package github.grace000.erversay.Request;

import github.grace000.erversay.Response.ResponseBuilder;
import github.grace000.erversay.RouteHandlers.RouteHandler;
import github.grace000.erversay.Routes;

import static github.grace000.erversay.Constants.StatusCodes.NOT_FOUND_STATUS;

public class RequestRouter {
    private ResponseBuilder responseBuilder = new ResponseBuilder();
    private Routes routes = new Routes();

    public String route(Request request) {
        return handleRequest(request, routes);
    }

    private String handleRequest(Request request, Routes routes) {
        String path = request.path;

        if (!routes.routeMap.containsKey(path)) {
            return handleUnknownPath();
        }

        RouteHandler routeHandler = (RouteHandler) routes.routeMap.get(path);
        return routeHandler.handle(request);
    }

    private String handleUnknownPath() {
        return responseBuilder.withStatus(NOT_FOUND_STATUS).build();
    }
}
