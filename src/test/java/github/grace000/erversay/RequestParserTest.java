package github.grace000.erversay;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class RequestParserTest {
    private String request = "GET /simple_get HTTP/1.1";
    private String requestWithBody = "GET /piggly HTTP/1.1\r\nContent-Type:text/plain\r\nContent-Length: 6\r\n\r\npiggly";
    private RequestParser parser;

    @Test
    public void itReturnsMethodPathVersionRequestArray() {
        parser = new RequestParser();

        String[] parsedRequest = parser.parseRequestLine(request);

        assertEquals(parsedRequest[0], "GET");
        assertEquals(parsedRequest[1], "/simple_get");
        assertEquals(parsedRequest[2], "HTTP/1.1");
    }

    @Test
    public void itParsesRequestIntoSeparateLines() {
        parser = new RequestParser();

        String[] parsedRequest = parser.parseRequest(requestWithBody);

        assertEquals(parsedRequest[0], "GET /piggly HTTP/1.1");
        assertEquals(parsedRequest[1], "Content-Type:text/plain");
        assertEquals(parsedRequest[2], "Content-Length: 6");
        assertEquals(parsedRequest[4], "piggly");
    }

    @Test
    public void itGetsRequestLine() {
        parser = new RequestParser();

        String requestLine = parser.getRequestLine(requestWithBody);

        assertEquals("GET /piggly HTTP/1.1", requestLine);
    }

    @Test
    public void itGetsHeaders() {
        parser = new RequestParser();

        String[] expectedHeaders = {"Content-Type:text/plain", "Content-Length: 6"};
        String[] headers = parser.getHeaders(requestWithBody);

        assertTrue(Arrays.equals(expectedHeaders, headers));
    }

    @Test
    public void itGetsBody() {
        parser = new RequestParser();

        String body = parser.getBody(requestWithBody);

        assertEquals(body, "piggly");
    }
}

