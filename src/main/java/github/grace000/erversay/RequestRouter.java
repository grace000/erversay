package github.grace000.erversay;

import java.util.HashMap;

import static github.grace000.erversay.Constants.*;

public class RequestRouter {
    private HashMap<String, String> routeTable = new HashMap<>();

    public HashMap route(String[] requestLineArray) {
        int uriIndex = 1;
        if (requestLineArray[uriIndex].equals(SIMPLE_GET_URI)) {
            routeTable.put(CODE, DEFAULT_CODE);
            routeTable.put(STATUS, DEFAULT_STATUS);
            routeTable.put("body", "");
            return routeTable;
        } else {
            routeTable.put(CODE, NOT_FOUND_CODE);
            routeTable.put(STATUS, NOT_FOUND_STATUS);
            return routeTable;
        }
    }
}
