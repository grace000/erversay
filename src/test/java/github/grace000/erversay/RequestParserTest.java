package github.grace000.erversay;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class RequestParserTest {
    private String requestWithBody = "GET /piggly HTTP/1.1\r\nContent-Type:text/plain\r\ncontent-length: 6\r\n\r\npiggly";
    private RequestParser parser;
    private BufferedReader bufferedReader;

    private ByteArrayInputStream getStream(String requestString) {
        return new ByteArrayInputStream(requestString.getBytes());
    }

    private BufferedReader reader(ByteArrayInputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    @Test
    public void itGetsRequestMethod() throws IOException {
        parser = new RequestParser();

        bufferedReader = reader(getStream(requestWithBody));
        String parsedMethod = parser.parse(bufferedReader).method;

        assertEquals(parsedMethod, "GET");
    }

    @Test
    public void itGetsRequestBody() throws IOException {
        parser = new RequestParser();

        bufferedReader = reader(getStream(requestWithBody));
        String parsedBody = parser.parse(bufferedReader).body;

        assertEquals(parsedBody, "piggly");
    }


    @Test
    public void itGetsRequestPath() throws IOException {
        parser = new RequestParser();

        bufferedReader = reader(getStream(requestWithBody));
        String parsedPath = parser.parse(bufferedReader).path;

        assertEquals(parsedPath, "/piggly");
    }

    @Test
    public void itCreatesRequestObjectForPostRequest() throws IOException {
        parser = new RequestParser();
        String postRequest = "POST /echo_body HTTP/1.1\r\nContent-Type:text/plain\r\ncontent-length: 9\r\n\r\nsome data";
        bufferedReader = reader(getStream(postRequest));
        Request request = parser.parse(bufferedReader);

        assertEquals(request.method, "POST");
        assertEquals(request.path, "/echo_body");
        assertEquals(request.body, "some data");
    }
}

