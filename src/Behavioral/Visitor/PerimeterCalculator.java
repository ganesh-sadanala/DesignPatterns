package Behavioral.Visitor;

public class PerimeterCalculator implements PerimeterVisitor {

    @Override
    public double calculatePerimeter(Circle circle) {
        double radius = circle.getRadius();
        return 2 * Math.PI * radius;
    }

    @Override
    public double calculatePerimeter(Square square) {
        double side = square.getSide();
        return 4 * side;
    }

    @Override
    public double calculatePerimeter(Triangle triangle) {
        double base = triangle.getBase();
        double height = triangle.getHeight();
        double side1 = Math.sqrt(base * base + height * height);
        return base + height + side1;
    }

    @Override
    public double calculatePerimeter(Rectangle rectangle) {
        double length = rectangle.getLength();
        double width = rectangle.getWidth();
        return 2 * (length + width);
    }
}
