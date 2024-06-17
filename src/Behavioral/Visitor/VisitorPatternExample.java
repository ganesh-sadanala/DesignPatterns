package Behavioral.Visitor;

public class VisitorPatternExample {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape square = new Square(4);
        Shape triangle = new Triangle(3, 4);
        Shape rectangle = new Rectangle(5, 6);

        PerimeterCalculator perimeterCalculator = new PerimeterCalculator();
        double circlePerimeter = perimeterCalculator.calculatePerimeter((Circle) circle);
        double squarePerimeter = perimeterCalculator.calculatePerimeter((Square) square);
        double trianglePerimeter = perimeterCalculator.calculatePerimeter((Triangle) triangle);
        double rectanglePerimeter = perimeterCalculator.calculatePerimeter((Rectangle) rectangle);

        System.out.println("Circle perimeter: " + circlePerimeter);
        System.out.println("Square perimeter: " + squarePerimeter);
        System.out.println("Triangle perimeter: " + trianglePerimeter);
        System.out.println("Rectangle perimeter: " + rectanglePerimeter);
    }
}
