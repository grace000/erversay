package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request;

import java.util.Arrays;

public class NotFound implements RouteHandler {
    private enum AllowedMethods {
        HEAD, OPTIONS
    }

    public boolean isMethodAllowed(String method) {
        return Arrays.stream(AllowedMethods.values())
                .findFirst()
                .filter(allowed -> (allowed.name().equals(method))).isPresent();
    }

    public String handle(Request request) {
        return null;
    }
}
