package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.Response.ResponseBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static github.grace000.erversay.Constants.Headers.*;
import static github.grace000.erversay.Constants.StatusCodes.NOT_ALLOWED_STATUS;
import static github.grace000.erversay.Constants.StatusCodes.OK_STATUS;

public class KittyImage implements RouteHandler {
    private ResponseBuilder responseBuilder = new ResponseBuilder();

    public enum AcceptedMethods {
        GET
    }

    public boolean isMethodAllowed(String method) {
        for (AcceptedMethods acceptedMethods : AcceptedMethods.values()) {
            if (acceptedMethods.name().equals(method)) {
                return true;
            }
        }
        return false;
    }

    public Response handle(Request request) {
        ResponseBuilder fileResponse = responseBuilder;
        if (isMethodAllowed(request.method)) {
        try {
            File file = new File("public/tuxedo.jpg");
            Path path = file.toPath();
            byte[] data = Files.readAllBytes(path);

            fileResponse
                    .withStatus(OK_STATUS)
                    .withBody(data)
                    .withHeaders(JPEG_IMAGE_HEADER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        return fileResponse.build();
        }
        else return responseBuilder
                .withHeaders(OPTIONS_HEADER)
                .withStatus(NOT_ALLOWED_STATUS)
                .build();
    }
}
