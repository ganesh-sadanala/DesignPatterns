package Behavioral.Strategy;

import java.util.List;
import java.awt.*;

public class Navigator {
    RouteStrategy routeStrategy;

    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    public List<Point> buildRoute(Point A, Point B){
        return routeStrategy.buildRoute(A, B);
    }
}
