package github.grace000.erversay;

import github.grace000.erversay.RouteHandlers.*;

import java.util.HashMap;

public class Routes {
    public HashMap<String, RouteHandler> routes = new HashMap<>();
    {
        routes.put("/simple_get", new SimpleGet());
        routes.put("/method_options", new Options());
        routes.put("/method_options2", new OptionsTwo());
        routes.put("/echo_body", new Post());
        routes.put("/get_with_body", new NotAllowed());
    }
}
