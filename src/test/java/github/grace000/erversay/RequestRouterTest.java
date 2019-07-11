package github.grace000.erversay;

import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class RequestRouterTest {
        private RequestRouter router = new RequestRouter();
        private MockRouteHandler mockRouteHandler = new MockRouteHandler();

        @Test
        public void itRoutesMockRequestToMockHandler() {
                HashMap routes = new Routes().routes;
                routes.put("/mock_handle", mockRouteHandler);

                Request request = new Request("GET", "/mock_handle","");

                router.route(request, routes);
                assertTrue(mockRouteHandler.handleRequestWasCalled);
        }

        @Test
        public void itDoesNotRouteInvalidRequestToMockHandler() {
                HashMap routes = new Routes().routes;
                routes.put("/mock_handle", mockRouteHandler);

                Request request = new Request("GET", "/mock","");

                String routedRequest = router.route(request, routes);
                assertFalse(mockRouteHandler.handleRequestWasCalled);
        }
}
