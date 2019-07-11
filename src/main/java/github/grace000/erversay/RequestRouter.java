package github.grace000.erversay;

import github.grace000.erversay.RouteHandlers.RouteHandler;

import java.util.HashMap;

public class RequestRouter {
    public String route(Request request, HashMap routes) {
        return handleRequest(request, routes);
    }

    private String handleRequest(Request request, HashMap routes) {
        String path = request.path;
        if (routes.containsKey(path)) {
            RouteHandler routeHandler = (RouteHandler) routes.get(path);
            return routeHandler.handle(request);
        }
        return path;
    }
}
