package github.grace000.erversay;

import github.grace000.erversay.Constants.Headers;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ResponseBuilderTest {

    @Test
    public void itReturnsAFormattedDefaultResponse() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String formattedResponse = responseBuilder.build();
        assertEquals(formattedResponse, "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void itReturnsHeadersForOptionsResponse() {
        ResponseBuilder response = new ResponseBuilder().withHeaders(Headers.OPTIONS_HEADER);
        String formattedResponse = response.build();
        assertEquals(formattedResponse, "HTTP/1.1 200 OK\r\nAllow: OPTIONS, GET, HEAD\r\nContent-Length: 0\r\n\r\n ");
    }
}
