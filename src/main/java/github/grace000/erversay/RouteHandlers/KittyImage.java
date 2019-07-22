package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;

import java.io.File;

public class KittyImage implements RouteHandler {
    public boolean isMethodAllowed(String method) {
        return true;
    }

    public Response handle(Request request) {
        File file = new File("tuxedo.png");
        System.out.println(file.getAbsolutePath());
        System.out.println("length" + file.length());
        return new Response("hi", "", 0, "header");
    }
}
