package Behavioral.Visitor;

public interface PerimeterVisitor {
    double calculatePerimeter(Circle circle);
    double calculatePerimeter(Rectangle rectangle);
    double calculatePerimeter(Triangle triangle);
    double calculatePerimeter(Square square);
}
