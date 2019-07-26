package github.grace000.erversay;

import github.grace000.erversay.Response.Response;
import github.grace000.erversay.Response.ResponseBuilder;
import org.junit.Test;

import java.util.Objects;

import static github.grace000.erversay.Constants.HTTPLines.EMPTY_BODY;
import static github.grace000.erversay.Constants.Headers.OPTIONS_HEADER;
import static github.grace000.erversay.Constants.StatusCodes.OK_STATUS;
import static junit.framework.TestCase.assertEquals;

public class ResponseBuilderTest {
    private ResponseBuilder responseBuilder = new ResponseBuilder();

    @Test
    public void itReturnsADefaultResponseObjectWithOkStatus() {
        Response response = responseBuilder.build();

        int emptyContent = 0;
        String emptyHeaders = "";

        assertEquals(OK_STATUS, response.status);
        assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
        assertEquals(emptyContent, response.contentLength);
        assertEquals(emptyHeaders, response.headers);
    }

    @Test
    public void itReturnsHeadersForOptionsResponse() {
        Response response = responseBuilder.withHeaders(OPTIONS_HEADER).build();

        int emptyContent = 0;

        assertEquals(OK_STATUS, response.status);
        assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
        assertEquals(emptyContent, response.contentLength);
        assertEquals(OPTIONS_HEADER, response.headers);
    }
}
