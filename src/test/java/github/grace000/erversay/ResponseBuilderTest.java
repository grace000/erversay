package github.grace000.erversay;

import github.grace000.erversay.Constants.Headers;
import github.grace000.erversay.Constants.StatusCodes;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class ResponseBuilderTest {

    @Test
    public void itHasADefaultCodeOf200() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String formattedResponse = responseBuilder.build();
        assertEquals(formattedResponse, "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n ");
    }

    @Test
    public void itReturnsHeadersForOptionsResponse() {
        ResponseBuilder response = new ResponseBuilder().withHeaders(Headers.OPTIONS_HEADER);
        String formattedResponse = response.build();
        assertEquals(formattedResponse, "HTTP/1.1 200 OK\r\nAllow: OPTIONS, GET, HEAD\r\nContent-Length: 0\r\n\r\n ");
    }
}
