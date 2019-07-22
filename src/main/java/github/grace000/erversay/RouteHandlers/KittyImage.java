package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request.Request;

import java.io.File;

public class KittyImage implements RouteHandler {
    public boolean isMethodAllowed(String method) {
        return true;
    }

    public String handle(Request request) {
        File file = new File("tuxedo.png");
        System.out.println(file.getAbsolutePath());
        System.out.println("length" + file.length());

        09-?""
        return null;
    }
}
