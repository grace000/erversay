package github.grace000.erversay;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.RouteHandlers.*;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Objects;

import static github.grace000.erversay.Constants.HTTPLines.EMPTY_BODY;
import static github.grace000.erversay.Constants.Headers.*;
import static github.grace000.erversay.Constants.StatusCodes.*;
import static junit.framework.TestCase.*;

public class HandlerTest {
    private RouteHandler simpleGetHandler = new SimpleGet();
    private RouteHandler optionsHandler = new Options();
    private RouteHandler optionsTwoHandler = new OptionsTwo();
    private RouteHandler postHandler = new Post();
    private RouteHandler notAllowedHandler = new NotAllowed();
    private RouteHandler redirectHandler = new Redirect();
    private RouteHandler kittyImageHandler = new KittyImage();
    private RouteHandler putHandler = new Put();

    private String fileData = "Bacon";
    private String sampleTextPath = "public/sample.txt";

    private void writeToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(sampleTextPath));
        writer.write(fileData);
        writer.close();
    }

    @Test
    public void simpleGetHandlerBuildsResponseForGetMethod() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("GET", "/simple_get", headers, EMPTY_BODY);
        Response response = simpleGetHandler.handle(request);

        int emptyContent = 0;
        String emptyHeaders = "Content-Length: 0";

        assertEquals(OK_STATUS.code, response.status);
        assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
        assertEquals(emptyContent, response.contentLength);
        assertEquals(emptyHeaders, response.headers);
    }

    @Test
    public void simpleGetHandlerBuildsResponseForUnknownMethod() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("POST", "/simple_get", headers, EMPTY_BODY);
        Response response = simpleGetHandler.handle(request);

        int emptyContent = 0;

        assertEquals(NOT_ALLOWED_STATUS.code, response.status);
        assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
        assertEquals(emptyContent, response.contentLength);
        assertEquals("Allow: GET, HEAD, OPTIONS", response.headers);
    }

    @Test
    public void optionsHandlerBuildsResponseForGetMethod() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("GET", "/method_options", headers,EMPTY_BODY);
        Response response = optionsHandler.handle(request);

        int emptyContent = 0;

        assertEquals(OK_STATUS.code, response.status);
        assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
        assertEquals(emptyContent, response.contentLength);
        assertEquals("Allow: GET, HEAD, OPTIONS", response.headers);
    }

    @Test
    public void optionsHandlerBuildsResponseForUnknownMethod() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("PUT", "/method_options", headers,EMPTY_BODY);
        Response response = optionsHandler.handle(request);

        int emptyContent = 0;

        assertEquals(NOT_ALLOWED_STATUS.code, response.status);
        assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
        assertEquals(emptyContent, response.contentLength);
        assertEquals("Allow: GET, HEAD, OPTIONS", response.headers);
    }

    @Test
    public void optionsTwoHandlerBuildsForOptionsMethod() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("OPTIONS", "/method_options2", headers,EMPTY_BODY);
        Response response = optionsTwoHandler.handle(request);

        int emptyContent = 0;

        assertEquals(OK_STATUS.code, response.status);
        assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
        assertEquals(emptyContent, response.contentLength);
        assertEquals("Allow: GET, HEAD, OPTIONS, PUT, POST", response.headers);
    }

    @Test
    public void optionsTwoHandlerDoesNotBuildNotAllowedForOptionsMethod() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("OPTIONS", "/method_options2", headers, EMPTY_BODY);
        Response response = optionsTwoHandler.handle(request);

        String emptyHeaders = "";

        assertNotSame(NOT_ALLOWED_STATUS.code, response.status);
        assertNotSame(emptyHeaders, response.headers);
    }

    @Test
    public void optionsTwoHandlerBuildsNotAllowedForUnknownMethod() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("DELETE", "/method_options2", headers, EMPTY_BODY);
        Response response = optionsTwoHandler.handle(request);

        int emptyContent = 0;

        assertEquals(NOT_ALLOWED_STATUS.code, response.status);
        assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
        assertEquals(emptyContent, response.contentLength);
        assertEquals("Allow: GET, HEAD, OPTIONS, PUT, POST", response.headers);
    }

    @Test
    public void postHandlerEchosResponseBody() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("POST", "/echo_body", headers,"body");
        Response response = postHandler.handle(request);

        int contentLength = 4;
        String expectedBody = "body";
        String expectedHeaders = "Content-Length: 4";

        assertEquals(CREATED.code, response.status);
        assert(Objects.deepEquals(expectedBody.getBytes(), response.body));
        assertEquals(contentLength, response.contentLength);
        assertEquals(expectedHeaders, response.headers);
    }

    @Test
    public void notAllowedHandlerBuilds405ResponseForGetMethod() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("GET", "/get_with_body", headers, EMPTY_BODY);
        Response response = notAllowedHandler.handle(request);

        int emptyContent = 0;

        assertEquals(NOT_ALLOWED_STATUS.code, response.status);
        assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
        assertEquals(emptyContent, response.contentLength);
        assertEquals(NOT_ALLOWED_HEADER, response.headers);
    }

    @Test
    public void notAllowedHandlerBuildsOKResponseForHeadMethod() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("HEAD", "/get_with_body", headers, EMPTY_BODY);
        Response response = notAllowedHandler.handle(request);

        int emptyContent = 0;
        String emptyHeaders = "";

        assertEquals(OK_STATUS.code, response.status);
        assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
        assertEquals(emptyContent, response.contentLength);
        assertEquals(emptyHeaders, response.headers);
    }

    @Test
    public void simpleGetHandlerBuildsResponseForRedirect() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("GET", "/redirect", headers, EMPTY_BODY);
        Response response = redirectHandler.handle(request);

        int emptyContent = 0;

        assertEquals(REDIRECT_STATUS.code, response.status);
        assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
        assertEquals(emptyContent, response.contentLength);
        assertEquals(REDIRECT_HEADER, response.headers);
    }

    @Test
    public void kittyImageHandlerBuildsResponseForImageRequest() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("GET", "/kitty_image", headers, EMPTY_BODY);
        Response response = kittyImageHandler.handle(request);

        assertEquals(OK_STATUS.code, response.status);
        assertEquals(JPEG_IMAGE_HEADER, response.headers);
    }

    @Test
    public void putRequestHandlerBuildsResponseForPutRequest() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Request request = new Request("PUT", "/update_body", headers, "update");
        Response response = putHandler.handle(request);

        String expectedBody = "update";
        int expectedContent = 6;
        String expectedHeaders = "Content-Length: 6";

        assertEquals(OK_STATUS, response.status);
        assert(Objects.deepEquals(expectedBody.getBytes(), response.body));
        assertEquals(expectedContent, response.contentLength);
        assertEquals(expectedHeaders, response.headers);
    }

    @Test
    public void putRequestHandlerSendsFileContentsForGetRequest() throws IOException {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        writeToFile();

        Request request = new Request("GET", "/update_body", headers, "");
        Response response = putHandler.handle(request);

        int expectedContent = 5;
        String expectedHeaders = "Content-Length: 5";

        assertEquals(OK_STATUS, response.status);
        assertEquals(expectedContent, response.contentLength);
        assertEquals(expectedHeaders, response.headers);
    }

    @Test
    public void putRequestHandlerUpdatesFileContentsForPutRequest() throws IOException {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "plain/text");

        Request request = new Request("PUT", "/update_body", headers, "Piggly wiggly");
        Response response = putHandler.handle(request);

        String expectedBody = "Piggly wiggly";
        int expectedContent = 13;
        String expectedHeaders = "Content-Length: 13";

        byte[] fileBytes = Files.readAllBytes(new File(sampleTextPath).toPath());

        assertEquals(OK_STATUS, response.status);
        assert(Objects.deepEquals(fileBytes, response.body));
        assert(Objects.deepEquals(expectedBody.getBytes(), response.body));
        assertEquals(expectedContent, response.contentLength);
        assertEquals(expectedHeaders, response.headers);
    }
}
