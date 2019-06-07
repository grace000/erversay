package github.grace000.erversay;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ResponseTest {

        @Test
        public void itHasADefaultCodeOf200() {
            Response response = new Response().withCode(200).withStatus("OK");
            String formattedResponse = response.build();
            assertEquals(formattedResponse, "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n");
        }

        @Test
        public void itHasADefaultHttpProtocolVersionOfOnePointOne() {
            Response response = new Response();
            assertEquals(response.protocol, "HTTP/1.1");
        }

        @Test
        public void itReturnsAFormattedHttpResponse() {
            Response response = new Response();
            String formattedResponse = response.build();
            assertEquals(formattedResponse, "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n");
        }
}
