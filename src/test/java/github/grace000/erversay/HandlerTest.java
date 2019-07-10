package github.grace000.erversay;

import github.grace000.erversay.Handlers.Options;
import github.grace000.erversay.Handlers.OptionsTwo;
import github.grace000.erversay.Handlers.Post;
import github.grace000.erversay.Handlers.SimpleGet;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class HandlerTest {

    @Test
    public void simpleGetHandlerBuildsResponseForGetMethod() {
        String request = "GET /simple_get HTTP/1.1\r\nContent-Type:text/plain\r\nContent-Length: 0\r\n\r\n";
        Request parsedRequest = new RequestParser().parse(request);
        String builtResponse = new SimpleGet().handle(parsedRequest);

        assertEquals(builtResponse, "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void optionsHandlerBuildsResponseForGetMethod() {
        String request = "GET /method_options HTTP/1.1\r\nContent-Type:text/plain\r\nContent-Length: 0\r\n\r\n";
        Request parsedRequest = new RequestParser().parse(request);
        String builtResponse = new Options().handle(parsedRequest);

        assertEquals(builtResponse, "HTTP/1.1 200 OK\r\nAllow: OPTIONS, GET, HEAD\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void optionsTwoHandlerBuildsNotFoundForUnknownMethod() {
        String request = "DELETE /method_options HTTP/1.1\r\nContent-Type:text/plain\r\nContent-Length: 0\r\n\r\n";
        Request parsedRequest = new RequestParser().parse(request);
        String builtResponse = new OptionsTwo().handle(parsedRequest);

        assertEquals(builtResponse, "HTTP/1.1 404 Not Found\r\nContent-Length: 0\r\n\r\n");
    }

    @Test
    public void postHandlerEchosResponseBody() {
        String request = "POST /echo_body HTTP/1.1\r\nContent-Type:text/plain\r\nContent-Length: 4\r\n\r\nbody";
        Request parsedRequest = new RequestParser().parse(request);
        String builtResponse = new Post().handle(parsedRequest);

        assertEquals(builtResponse, "HTTP/1.1 200 OK\r\nContent-Length: 4\r\n\r\nbody");
    }
}
