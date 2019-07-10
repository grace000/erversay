package github.grace000.erversay;

import github.grace000.erversay.Handlers.Handler;

import java.util.HashMap;

public class RequestRouter {
    public String route(Request request, HashMap routes) {
        return handleRequest(request, routes);
    }

    private String handleRequest(Request request, HashMap routes) {
        String path = request.path;
        if (routes.containsKey(path)) {
            Handler pathHandler = (Handler) routes.get(path);
            return pathHandler.handle(request);
        }
        return path;
    }
}
