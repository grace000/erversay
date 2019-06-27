package github.grace000.erversay;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ResponseBuilderTest {

        @Test
        public void itHasADefaultCodeOf200() {
            ResponseBuilder responseBuilder = new ResponseBuilder().withCode("200").withStatus("OK");
            String formattedResponse = responseBuilder.build();
            assertEquals(formattedResponse, "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n");
        }

        @Test
        public void itHasADefaultHttpProtocolVersionOfOnePointOne() {
            ResponseBuilder responseBuilder = new ResponseBuilder();
            assertEquals(responseBuilder.protocol, "HTTP/1.1");
        }

        @Test
        public void itReturnsAFormattedHttpResponse() {
            ResponseBuilder responseBuilder = new ResponseBuilder();
            String formattedResponse = responseBuilder.build();
            assertEquals(formattedResponse, "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n");
        }
}
