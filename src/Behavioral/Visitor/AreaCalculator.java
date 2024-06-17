package Behavioral.Visitor;

public class AreaCalculator implements AreaVisitor {

    @Override
    public double calculateArea(Circle circle) {
        double radius = circle.getRadius();
        double area = Math.PI * radius * radius;
        return area;
    }

    @Override
    public double calculateArea(Square square) {
        double side = square.getSide();
        double area = side * side;
        return area;
    }

    @Override
    public double calculateArea(Triangle triangle) {
        double base = triangle.getBase();
        double height = triangle.getHeight();
        double area = 0.5 * base * height;
        return area;
    }

    @Override
    public double calculateArea(Rectangle rectangle) {
        double length = rectangle.getLength();
        double width = rectangle.getWidth();
        double area = length * width;
        return area;
    }
}
