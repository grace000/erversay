package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;

public class Put implements RouteHandler{
    public boolean isMethodAllowed(String method) {
        return false;
    }

    public Response handle(Request request) {
        return null;
    }
}
