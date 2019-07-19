package github.grace000.erversay;

import github.grace000.erversay.RouteHandlers.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;

public class HandlerTest {
    private RouteHandler simpleGetHandler = new SimpleGet();
    private RouteHandler optionsHandler = new Options();
    private RouteHandler optionsTwoHandler = new OptionsTwo();
    private RouteHandler postHandler = new Post();
    private RouteHandler notAllowedHandler = new NotAllowed();
    private RouteHandler redirectHandler = new Redirect();

    @Test
    public void simpleGetHandlerBuildsResponseForGetMethod() {
        Request request = new Request("GET", "/simple_get", "");
        String response = simpleGetHandler.handle(request);

        assertEquals(response, "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void simpleGetHandlerBuildsResponseForUnknownMethod() {
        Request request = new Request("POST", "/simple_get", "");
        String response = simpleGetHandler.handle(request);

        assertEquals(response, "HTTP/1.1 404 Not Found\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void optionsHandlerBuildsResponseForGetMethod() {
        Request request = new Request("GET", "/method_options", " ");
        String response = optionsHandler.handle(request);

        assertEquals(response, "HTTP/1.1 200 OK\r\nAllow: OPTIONS, GET, HEAD\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void optionsHandlerBuildsResponseForUnknownMethod() {
        Request request = new Request("PUT", "/method_options", " ");
        String response = optionsHandler.handle(request);

        assertEquals(response, "HTTP/1.1 404 Not Found\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void optionsTwoHandlerBuildsForOptionsMethod() {
        Request request = new Request("OPTIONS", "/method_options2", " ");
        String response = optionsTwoHandler.handle(request);

        assertEquals(response, "HTTP/1.1 200 OK\r\nAllow: OPTIONS, GET, HEAD, PUT, POST\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void optionsTwoHandlerDoesNotBuildNotFoundForOptionsMethod() {
        Request request = new Request("OPTIONS", "/method_options2", " ");
        String response = optionsTwoHandler.handle(request);

        assertNotSame(response, "HTTP/1.1 404 Not Found\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void optionsTwoHandlerBuildsNotFoundForUnknownMethod() {
        Request request = new Request("DELETE", "/method_options2", " ");
        String response = optionsTwoHandler.handle(request);

        assertEquals(response, "HTTP/1.1 404 Not Found\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void postHandlerEchosResponseBody() {
        Request request = new Request("POST", "/echo_body", "body");
        String response = postHandler.handle(request);

        assertEquals(response, "HTTP/1.1 200 OK\r\nContent-Length: 4\r\n\r\nbody");
    }

    @Test
    public void notAllowedHandlerBuilds405ResponseForGetMethod() {
        Request request = new Request("GET", "/get_with_body", " ");
        String response = notAllowedHandler.handle(request);

        assertEquals(response, "HTTP/1.1 405 Method Not Allowed\r\nAllow: HEAD, OPTIONS\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void notAllowedHandlerBuildsOKResponseForHeadMethod() {
        Request request = new Request("HEAD", "/get_with_body", " ");
        String response = notAllowedHandler.handle(request);

        assertEquals(response, "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void simpleGetHandlerBuildsResponseForRedirect() {
        Request request = new Request("GET", "/redirect", "");
        String response = redirectHandler.handle(request);

        assertEquals(response, "HTTP/1.1 301 Moved Permanently\r\nLocation: https://erversay.herokuapp.com/simple_get\r\nContent-Length: 0\r\n\r\n");
    }
}
