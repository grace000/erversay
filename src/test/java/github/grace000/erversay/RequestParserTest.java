package github.grace000.erversay;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RequestParserTest {
    private String request = "GET /simple_get HTTP/1.1";
    private RequestParser parser;

    @Test
    public void itReturnsMethodPathVersionRequestArray() {
        parser = new RequestParser();

        String[] parsedRequest = parser.parse(request);

        assertEquals(parsedRequest[0], "GET");
        assertEquals(parsedRequest[1], "/simple_get");
        assertEquals(parsedRequest[2], "HTTP/1.1");
    }
}

