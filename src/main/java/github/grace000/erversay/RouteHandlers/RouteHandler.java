package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request.Request;

public interface RouteHandler {
    public boolean isMethodAllowed(String method);
    public String handle(Request request);
}
