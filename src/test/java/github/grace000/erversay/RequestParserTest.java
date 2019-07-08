package github.grace000.erversay;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class RequestParserTest {
    private String requestWithBody = "GET /piggly HTTP/1.1\r\nContent-Type:text/plain\r\nContent-Length: 6\r\n\r\npiggly";
    private RequestParser parser;

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

    @Test
    public void itGetsMethod() {
        parser = new RequestParser();

        String requestLine = parser.getRequestLine(requestWithBody);
        String method = parser.getMethod(requestLine);

        assertEquals(method, "GET");
    }

    @Test
    public void itGetsPath() {
        parser = new RequestParser();

        String requestLine = parser.getRequestLine(requestWithBody);
        String path = parser.getPath(requestLine);

        assertEquals(path, "/piggly");
    }

    @Test
    public void itCreatesRequestObject() {
        parser = new RequestParser();

        Request request = parser.parse(requestWithBody);

        assertEquals(request.method, "GET");
        assertEquals(request.path, "/piggly");
        assertEquals(request.body, "piggly");
    }
}

