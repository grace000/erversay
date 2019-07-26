package github.grace000.erversay.Router;

import github.grace000.erversay.RouteHandlers.*;

import java.util.HashMap;

import static github.grace000.erversay.Constants.Paths.*;

public class Routes {
    public HashMap<String, RouteHandler> routeMap = new HashMap<>();
    {
        routeMap.put(SIMPLE_GET.path, new SimpleGet());
        routeMap.put(METHODS_ONE.path, new Options());
        routeMap.put(METHODS_TWO.path, new OptionsTwo());
        routeMap.put(ECHO_BODY.path, new Post());
        routeMap.put(GET_WITH_BODY.path, new NotAllowed());
        routeMap.put(REDIRECT.path, new Redirect());
        routeMap.put(KITTY_IMAGE.path, new KittyImage());
        routeMap.put(UPDATE_BODY.path, new Put());
    }
}
