package Behavioral.Strategy;

import java.util.List;
import java.awt.*;

public interface RouteStrategy {

    List<Point> buildRoute(Point a, Point b);
}
