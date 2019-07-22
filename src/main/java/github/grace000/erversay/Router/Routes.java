package github.grace000.erversay.Router;

import github.grace000.erversay.RouteHandlers.*;

import java.util.HashMap;

public class Routes {
    public HashMap<String, RouteHandler> routeMap = new HashMap<>();
    {
        routeMap.put("/simple_get", new SimpleGet());
        routeMap.put("/method_options", new Options());
        routeMap.put("/method_options2", new OptionsTwo());
        routeMap.put("/echo_body", new Post());
        routeMap.put("/get_with_body", new NotAllowed());
        routeMap.put("/redirect", new Redirect());
        routeMap.put("/kitty_image", new KittyImage());
    }
}
