package Behavioral.Visitor;

public interface AreaVisitor {
    double calculateArea(Circle circle);
    double calculateArea(Rectangle rectangle);
    double calculateArea(Triangle triangle);
    double calculateArea(Square square);
}
