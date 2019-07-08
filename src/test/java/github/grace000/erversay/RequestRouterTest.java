package github.grace000.erversay;

import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class RequestRouterTest {

    @Test
    public void itReturnsResponseForSimpleGet() {
        String request = "GET /simple_get HTTP/1.1\r\nContent-Type:text/plain\r\nContent-Length: 0\r\n\r\n";
        Request parsedRequest = new RequestParser().parse(request);

        Response response = new RequestRouter().route(parsedRequest);

        assertEquals("200 OK", response.status);
    }

    @Test
    public void itReturnsResponseForNotFoundRequest() {
        String request = "GET /not_found_resource HTTP/1.1\r\nContent-Type:text/plain\r\nContent-Length: 0\r\n\r\n";
        Request parsedRequest = new RequestParser().parse(request);

        Response response = new RequestRouter().route(parsedRequest);

        assertEquals("404 Not Found", response.status);
    }

    @Test
    public void itExcludesBodyForHeadRequest() {
        String request = "HEAD /simple_get HTTP/1.1";
        Request parsedRequest = new RequestParser().parse(request);

        Response response = new RequestRouter().route(parsedRequest);

        assertEquals("", response.body);
    }

    @Test
    public void itIncludesBodyForPiggyRequest() {
        String request = "GET /piggly HTTP/1.1\r\nContent-Type:text/plain\r\nContent-Length: 0\r\n\r\n";
        Request parsedRequest = new RequestParser().parse(request);

        Response response = new RequestRouter().route(parsedRequest);

        assertEquals("piggly wiggly", response.body);
    }

    @Test
    public void itIncludesGetAsAllowedHeadersForOptionsRequest() {
        String request = "OPTIONS /methods_options HTTP/1.1\r\nContent-Type:text/plain\r\nContent-Length: 0\r\n\r\n";
        Request parsedRequest = new RequestParser().parse(request);

        Response response = new RequestRouter().route(parsedRequest);

        assertEquals("Allow: OPTIONS, GET, HEAD", response.headers);
    }
}
