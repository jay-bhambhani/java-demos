package com.cinchfinancial.routes.consumers;

import com.cinchfinancial.routes.routehelpers.RouteHelper;
import com.cinchfinancial.routes.routers.CinchRouter;

/**
 * Created by jbhambhani on 2/23/16.
 */

public class CinchConsumer extends CinchRouter {
    private RouteHelper routeHelper;
    private String toUri;


    public CinchConsumer(RouteHelper routeHelper, String toUri) {
        super(routeHelper);
        this.toUri = toUri;
    }

    /**
     * hitches route created by routehelper to "from" string
     */
    @Override
    public void configure() {
        from(router())
                .unmarshal(getJsonFormat())
                .to(this.toUri)
                .to("log:sent?showAll=true");
    }
}
