package github.grace000.erversay;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.RouteHandlers.RouteHandler;

public class MockRouteHandler implements RouteHandler {
    public boolean handleRequestWasCalled = false;

    @Override
    public boolean isMethodAllowed(String method) {
        return false;
    }

    @Override
    public Response handle(Request request) {
        handleRequestWasCalled = true;
        return null;
    }
}
