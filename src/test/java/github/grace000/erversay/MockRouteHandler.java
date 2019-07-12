package github.grace000.erversay;

import github.grace000.erversay.RouteHandlers.RouteHandler;

public class MockRouteHandler implements RouteHandler {
    public boolean handleRequestWasCalled = false;

    @Override
    public boolean isMethodAllowed(String method) {
        return false;
    }

    @Override
    public String handle(Request request) {
        handleRequestWasCalled = true;
        return null;
    }
}
