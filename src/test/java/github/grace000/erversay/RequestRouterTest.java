package github.grace000.erversay;

import github.grace000.erversay.RouteHandlers.RouteHandler;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class RequestRouterTest {
        private RequestRouter router = new RequestRouter();
        private MockRouteHandler mockRouteHandler = new MockRouteHandler();
        private HashMap<String, RouteHandler> routes = new Routes().routes;

        @Test
        public void itRoutesMockRequestToMockHandler() {
                routes.put("/mock_handle", mockRouteHandler);

                Request request = new Request("GET", "/mock_handle","");

                router.route(request, routes);
                assertTrue(mockRouteHandler.handleRequestWasCalled);
        }

        @Test
        public void itDoesNotRouteInvalidRequestToMockHandler() {
                routes.put("/mock_handle", mockRouteHandler);

                Request request = new Request("GET", "/mock","");

                router.route(request, routes);
                assertFalse(mockRouteHandler.handleRequestWasCalled);
        }

        @Test
        public void itRoutesSimpleGetRequest() {
                Request request = new Request("GET", "/simple_get","");

                String response = router.route(request, routes);
                assertEquals(response, "HTTP/1.1 200 OK\r\ncontent-length: 0\r\n\r\n");
        }

        @Test
        public void itRoutesOptionsRequest() {
                Request request = new Request("OPTIONS", "/method_options","");

                String response = router.route(request, routes);
                assertEquals(response, "HTTP/1.1 200 OK\r\nAllow: OPTIONS, GET, HEAD\r\ncontent-length: 0\r\n\r\n");
        }

        @Test
        public void itRoutesOptionsTwoRequest() {
                Request request = new Request("OPTIONS", "/method_options2","");

                String response = router.route(request, routes);
                assertEquals(response, "HTTP/1.1 200 OK\r\nAllow: OPTIONS, GET, HEAD, PUT, POST\r\ncontent-length: 0\r\n\r\n");
        }

        @Test
        public void itRoutesPostRequest() {
                Request request = new Request("POST", "/echo_body","some body");

                String response = router.route(request, routes);
                assertEquals(response, "HTTP/1.1 200 OK\r\ncontent-length: 9\r\n\r\nsome body");
        }

        @Test
        public void itRoutesNotAllowedRequest() {
                Request request = new Request("GET", "/get_with_body","some body");

                String response = router.route(request, routes);
                assertEquals(response, "HTTP/1.1 405 Method Not Allowed\r\nAllow: HEAD, OPTIONS\r\ncontent-length: 0\r\n\r\n");
        }

        @Test
        public void itRoutesNotFoundRequest() {
                Request request = new Request("GET", "/not_found_resource","some body");

                String response = router.route(request, routes);
                assertEquals(response, "HTTP/1.1 404 Not Found\r\ncontent-length: 0\r\n\r\n");
        }

        @Test
        public void itRoutesMovedRequest() {
                Request request = new Request("GET", "/redirect","");

                String response = router.route(request, routes);
                assertEquals(response, "HTTP/1.1 301 Moved Permanently\r\nLocation: http://0.0.0.0:5000/simple_get\r\ncontent-length: 0\r\n\r\n");
        }
}
