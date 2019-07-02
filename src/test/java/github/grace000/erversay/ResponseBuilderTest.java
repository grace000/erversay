package github.grace000.erversay;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class ResponseBuilderTest {

    @Test
    public void itHasADefaultCodeOf200() {
        ResponseBuilder responseBuilder = new ResponseBuilder().withCode("200").withStatus("OK");
        String formattedResponse = responseBuilder.build();
        assertEquals(formattedResponse, "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n ");
    }

    @Test
    public void itReturnsAFormattedHttpResponse() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String formattedResponse = responseBuilder.build();
        assertEquals(formattedResponse, "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n ");
    }

    @Test
    public void itReturnsEmptyBody() {
        ResponseBuilder response = new ResponseBuilder().withCode("200").withStatus("OK").withBody(" ");
        String formattedResponse = response.build();
        assertEquals(formattedResponse, "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n ");
    }

    @Test
    public void itReturnsHeadersForOptionsResponse() {
        ResponseBuilder response = new ResponseBuilder().withHeaders("\r\nAllow: OPTIONS, GET, HEAD");
        String formattedResponse = response.build();
        assertEquals(formattedResponse, "HTTP/1.1 200 OK\r\nAllow: OPTIONS, GET, HEAD\r\nContent-Length: 0\r\n\r\n ");
    }
}
