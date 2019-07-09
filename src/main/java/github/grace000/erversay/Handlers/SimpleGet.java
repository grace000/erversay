package github.grace000.erversay.Handlers;

import github.grace000.erversay.Response;

public class SimpleGet implements Handler{
    public boolean isRouteAvailable() {
        return false;
    }

    public String handle() {
        return " ";
    }
}
