package github.grace000.erversay;

import github.grace000.erversay.Handlers.Handler;
import github.grace000.erversay.Handlers.OptionsTwo;
import github.grace000.erversay.Handlers.SimpleGet;

import java.util.HashMap;

public class Routes {
    public HashMap<String, Handler> routes = new HashMap<>();
    {
        routes.put("/simple_get", new SimpleGet());
        routes.put("/method_options2", new OptionsTwo());
    }
}
