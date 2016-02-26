package com.cinchfinancial.routes.consumers;

import com.cinchfinancial.routes.routehelpers.RouteHelper;
import com.cinchfinancial.routes.routers.CinchRouter;

/**
 * Created by jbhambhani on 2/23/16.
 */

public class CinchKafkaConsumer extends CinchRouter {
    private RouteHelper routeHelper;
    private String toUri;


    public CinchKafkaConsumer(RouteHelper routeHelper, String toUri) {
        this.routeHelper = routeHelper;
        this.toUri = toUri;
    }

    @Override
    public void configure() {
        from(router(routeHelper))
                .unmarshal(getFormat())
                .to(this.toUri)
                .to("log:sent?showAll=true");
    }
}
