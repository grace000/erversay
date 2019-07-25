package github.grace000.erversay.RouteHandlers;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.Response.ResponseBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static github.grace000.erversay.Constants.Headers.CONTENT_LENGTH;
import static github.grace000.erversay.Constants.Headers.PUT_HEADER;
import static github.grace000.erversay.Constants.StatusCodes.NOT_ALLOWED_STATUS;

public class Put implements RouteHandler{
    private ResponseBuilder responseBuilder = new ResponseBuilder();
    private String absolutePath = "public/sample.txt";
    private List<String> resource = new ArrayList<>();
    private Response response;

    public enum AcceptedMethods {
       GET, PUT
    }

    public boolean isMethodAllowed(String method) {
        for (AcceptedMethods accepted: AcceptedMethods.values()) {
            if (accepted.name().equals(method)){
                return true;
            }
        }
        return false;
    }

    public Response handle(Request request) {
        if (!isMethodAllowed(request.method)) {
            return methodNotAllowedResponse();
        }

        try {
            response = buildResponse(request, readFile(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private Response buildResponse(Request request, List<String> fileContents) throws IOException {
        String fileData = String.join(",", fileContents);

        if(request.method.equals(AcceptedMethods.PUT.toString())) {
            updateFile(request);
            return sendFileContents(request.body);
        }
        else {
            return sendFileContents(fileData);
        }
    }

    private Response methodNotAllowedResponse() {
        return responseBuilder
                .withHeaders(PUT_HEADER)
                .withStatus(NOT_ALLOWED_STATUS)
                .build();
    }

    private void updateFile(Request request) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(absolutePath));
        writer.write(request.body);
        writer.close();
    }

    private Response sendFileContents(String content) {
        return responseBuilder
                .withHeaders(CONTENT_LENGTH + ": " + content.getBytes().length)
                .withBody(content)
                .withContentLength(content.length(), content.getBytes())
                .build();
    }

    private List<String> readFile(List<String> records) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(absolutePath));
        String fileLine;
        while ((fileLine = reader.readLine()) != null) {
            records.add(fileLine);
        }
        reader.close();
        return records;
    }
}
