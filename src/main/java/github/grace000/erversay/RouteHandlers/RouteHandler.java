package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;

public interface RouteHandler {
    public boolean isMethodAllowed(String method);
    public Response handle(Request request);
}
