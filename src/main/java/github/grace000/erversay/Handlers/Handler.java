package github.grace000.erversay.Handlers;

import github.grace000.erversay.Request;

public interface Handler {
    public boolean isMethodAllowed(String method);
    public String handle(Request request);
}
