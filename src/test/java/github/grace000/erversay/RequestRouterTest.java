package github.grace000.erversay;

import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class RequestRouterTest {
    private String[]  parsedRequestArray;
    private HashMap responseRoute;

    @Test
    public void itReturnsResponseRouteForSimpleGet() {
        String requestLine = "GET /simple_get HTTP/1.1";
        parsedRequestArray = requestLine.split("\\s");

        responseRoute = new RequestRouter().route(parsedRequestArray);

        assertEquals("200", responseRoute.get("code"));
        assertEquals("OK", responseRoute.get("status"));
    }

    @Test
    public void itReturnsResponseRouteForNotFoundResource() {
        String requestLine = "GET /not_found_resource HTTP/1.1";
        parsedRequestArray = requestLine.split("\\s");

        responseRoute = new RequestRouter().route(parsedRequestArray);

        assertEquals("404", responseRoute.get("code"));
        assertEquals("Not Found", responseRoute.get("status"));
    }
}
