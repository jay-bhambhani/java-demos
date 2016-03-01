package com.cinchfinancial.routes.routehelpers;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by jbhambhani on 2/26/16.
 */
public interface RouteHelper {

    /**
     * every route helper must set a route string
     * @return string route string
     */
    String setRouteString();
    //HashMap<String, Object> setConstants(String fieldName, String fieldValue) throws Exception;
}
