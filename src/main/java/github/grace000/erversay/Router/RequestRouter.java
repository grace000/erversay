package github.grace000.erversay.Router;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.Response.ResponseBuilder;
import github.grace000.erversay.RouteHandlers.RouteHandler;

import static github.grace000.erversay.Constants.StatusCodes.NOT_FOUND_STATUS;

public class RequestRouter {
    private ResponseBuilder responseBuilder = new ResponseBuilder();
    private Routes routes = new Routes();

    public Response route(Request request) {
        return handleRequest(request, routes);
    }

    public Response route(Request request, Routes routes) {
        return handleRequest(request, routes);
    }

    private Response handleRequest(Request request, Routes routes) {
        String path = request.path;

        if (!routes.routeMap.containsKey(path)) {
            return handleUnknownPath();
        }

        RouteHandler routeHandler = (RouteHandler) routes.routeMap.get(path);
        return routeHandler.handle(request);
    }

    private Response handleUnknownPath() {
        return responseBuilder.withStatus(NOT_FOUND_STATUS.code).build();
    }
}
