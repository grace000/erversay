package github.grace000.erversay;

import github.grace000.erversay.Constants.Headers;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.Response.ResponseBuilder;
import org.junit.Test;

import static github.grace000.erversay.Constants.Body.EMPTY_BODY;
import static github.grace000.erversay.Constants.Headers.OPTIONS_HEADER;
import static github.grace000.erversay.Constants.StatusCodes.OK_STATUS;
import static junit.framework.TestCase.assertEquals;

public class ResponseBuilderTest {
    ResponseBuilder responseBuilder = new ResponseBuilder();

    @Test
    public void itReturnsADefaultResponseObjectWithOkStatus() {
        Response response = responseBuilder.build();

        int emptyContent = 0;
        String emptyHeaders = "";

        assertEquals(OK_STATUS, response.status);
        assertEquals(EMPTY_BODY, response.body);
        assertEquals(emptyContent, response.contentLength);
        assertEquals(emptyHeaders, response.headers);
    }

    @Test
    public void itReturnsHeadersForOptionsResponse() {
        Response response = responseBuilder.withHeaders(OPTIONS_HEADER).build();

        int emptyContent = 0;

        assertEquals(OK_STATUS, response.status);
        assertEquals(EMPTY_BODY, response.body);
        assertEquals(emptyContent, response.contentLength);
        assertEquals(OPTIONS_HEADER, response.headers);
    }
}
