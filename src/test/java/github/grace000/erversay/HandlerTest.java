package github.grace000.erversay;

import github.grace000.erversay.RouteHandlers.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class HandlerTest {
    private RouteHandler simpleGetHandler = new SimpleGet();
    private RouteHandler optionsHandler = new Options();
    private RouteHandler optionsTwoHandler = new OptionsTwo();
    private RouteHandler postHandler = new Post();
    @Test
    public void simpleGetHandlerBuildsResponseForGetMethod() {
        Request request = new Request("GET", "/simple_get", "");
        String response = simpleGetHandler.handle(request);

        assertEquals(response, "HTTP/1.1 200 OK\r\ncontent-length: 0\r\n\r\n");
    }

    @Test
    public void simpleGetHandlerBuildsResponseForUnknownMethod() {
        Request request = new Request("POST", "/simple_get", "");
        String response = simpleGetHandler.handle(request);

        assertEquals(response, "HTTP/1.1 404 Not Found\r\ncontent-length: 0\r\n\r\n");
    }

    @Test
    public void optionsHandlerBuildsResponseForGetMethod() {
        Request request = new Request("GET", "/method_options", " ");
        String response = optionsHandler.handle(request);

        assertEquals(response, "HTTP/1.1 200 OK\r\nAllow: OPTIONS, GET, HEAD\r\ncontent-length: 0\r\n\r\n");
    }

    @Test
    public void optionsHandlerBuildsResponseForUnknownMethod() {
        Request request = new Request("PUT", "/method_options", " ");
        String response = optionsHandler.handle(request);

        assertEquals(response, "HTTP/1.1 404 Not Found\r\ncontent-length: 0\r\n\r\n");
    }

    @Test
    public void optionsTwoHandlerBuildsForOptionsMethod() {
        Request request = new Request("OPTIONS", "/method_options", " ");
        String response = optionsTwoHandler.handle(request);

        assertEquals(response, "HTTP/1.1 200 OK\r\nAllow: OPTIONS, GET, HEAD, PUT, POST\r\ncontent-length: 0\r\n\r\n");
    }

    @Test
    public void optionsTwoHandlerBuildsNotFoundForUnknownMethod() {
        Request request = new Request("DELETE", "/method_options", " ");
        String response = optionsTwoHandler.handle(request);

        assertEquals(response, "HTTP/1.1 404 Not Found\r\ncontent-length: 0\r\n\r\n");
    }

    @Test
    public void postHandlerEchosResponseBody() {
        Request request = new Request("POST", "/echo_body", "body");
        String response = postHandler.handle(request);

        assertEquals(response, "HTTP/1.1 200 OK\r\ncontent-length: 4\r\n\r\nbody");
    }

    @Test
    public void notAllowedHandlerBuilds405ResponseForGetMethod() {
        Request request = new Request("GET", "/get_with_body", " ");
        String response = postHandler.handle(request);

        assertEquals(response, "HTTP/1.1 405 Method Not Allowed\r\ncontent-length: 0\r\n\r\n");
    }
}
