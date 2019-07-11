package github.grace000.erversay;

import github.grace000.erversay.Handlers.Handler;

public class MockRouteHandler implements Handler {
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
