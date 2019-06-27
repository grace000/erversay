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

    @Test
    public void itExcludesBodyForHeadRequest() {
        String requestLine = "HEAD /simple_get HTTP/1.1";
        parsedRequestArray = requestLine.split("\\s");

        responseRoute = new RequestRouter().route(parsedRequestArray);

        assertEquals("", responseRoute.get("body"));
    }

    @Test
    public void itIncludesBodyForPiggyRequest() {
        String requestLine = "GET /piggly HTTP/1.1\r\nContent-Type:text/plain\r\nContent-Length: 6\r\n\r\npiggly";
        parsedRequestArray = requestLine.split("\\s");

        responseRoute = new RequestRouter().route(parsedRequestArray);

        assertEquals("piggly", responseRoute.get("body"));
    }
}
