package github.grace000.erversay.Handlers;

import github.grace000.erversay.Response;

public interface Handler {
    public boolean isRouteAvailable();
//    public Response handle();
    public String handle();
}
