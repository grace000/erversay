package github.grace000.erversay;

import github.grace000.erversay.Request.Request;
import github.grace000.erversay.Response.Response;
import github.grace000.erversay.RouteHandlers.RouteHandler;
import github.grace000.erversay.Router.RequestRouter;
import github.grace000.erversay.Router.Routes;
import org.junit.Test;


import java.util.HashMap;
import java.util.Objects;

import static github.grace000.erversay.Constants.Body.EMPTY_BODY;
import static github.grace000.erversay.Constants.Headers.*;
import static github.grace000.erversay.Constants.StatusCodes.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class RequestRouterTest {
        private RequestRouter router = new RequestRouter();
        private MockRouteHandler mockRouteHandler = new MockRouteHandler();
        private Routes routes = new Routes();

        private HashMap<String, String> setHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
        }

        @Test
        public void itRoutesMockRequestToMockHandler() {
                routes.routeMap.put("/mock_handle", mockRouteHandler);

                Request request = new Request("GET", "/mock_handle", setHeaders(), EMPTY_BODY);

                router.route(request, routes);
                assertTrue(mockRouteHandler.handleRequestWasCalled);
        }

        @Test
        public void itDoesNotRouteInvalidRequestToMockHandler() {
                Request request = new Request("GET", "/mock", setHeaders(), EMPTY_BODY);

                router.route(request);
                assertFalse(mockRouteHandler.handleRequestWasCalled);
        }

        @Test
        public void itRoutesSimpleGetRequest() {
                Request request = new Request("GET", "/simple_get", setHeaders(), EMPTY_BODY);

                Response response = router.route(request);

                int emptyContent = 0;
                String emptyHeaders = "Content-Length: 0";

                assertEquals(OK_STATUS, response.status);
                assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
                assertEquals(emptyContent, response.contentLength);
                assertEquals(emptyHeaders, response.headers);
        }

        @Test
        public void itRoutesOptionsRequest() {
                Request request = new Request("OPTIONS", "/method_options", setHeaders(), EMPTY_BODY);

                Response response = router.route(request);

                int emptyContent = 0;

                assertEquals(OK_STATUS, response.status);
                assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
                assertEquals(emptyContent, response.contentLength);
                assertEquals(OPTIONS_HEADER, response.headers);
        }

        @Test
        public void itRoutesOptionsTwoRequest() {
                Request request = new Request("OPTIONS", "/method_options2", setHeaders(), EMPTY_BODY);

                Response response = router.route(request);

                int emptyContent = 0;

                assertEquals(OK_STATUS, response.status);
                assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
                assertEquals(emptyContent, response.contentLength);
                assertEquals(OPTIONS_TWO_HEADER, response.headers);
        }

        @Test
        public void itRoutesPostRequest() {
                Request request = new Request("POST", "/echo_body", setHeaders(),"some body");

                Response response = router.route(request);

                int contentLength = 9;
                String expectedBody = "some body";
                String expectedHeaders = "Content-Length: 9";

                assertEquals(OK_STATUS, response.status);
                assert(Objects.deepEquals(expectedBody.getBytes(), response.body));
                assertEquals(contentLength, response.contentLength);
                assertEquals(expectedHeaders, response.headers);
        }

        @Test
        public void itRoutesNotAllowedRequest() {
                Request request = new Request("GET", "/get_with_body", setHeaders(), "some body");

                Response response = router.route(request);

                int emptyContent = 0;

                assertEquals(NOT_ALLOWED_STATUS, response.status);
                assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
                assertEquals(emptyContent, response.contentLength);
                assertEquals(NOT_ALLOWED_HEADER, response.headers);
        }

        @Test
        public void itRoutesNotFoundRequest() {
                Request request = new Request("GET", "/not_found_resource", setHeaders(), "some body");

                Response response = router.route(request);

                int emptyContent = 0;

                assertEquals(NOT_FOUND_STATUS, response.status);
                assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
                assertEquals(emptyContent, response.contentLength);
        }

        @Test
        public void itRoutesMovedRequest() {
                Request request = new Request("GET", "/redirect", setHeaders(), EMPTY_BODY);

                Response response = router.route(request);

                int emptyContent = 0;

                assertEquals(REDIRECT_STATUS, response.status);
                assert(Objects.deepEquals(EMPTY_BODY.getBytes(), response.body));
                assertEquals(emptyContent, response.contentLength);
                assertEquals(REDIRECT_HEADER, response.headers);
        }
}
