package Structural.Composite;

public class Product implements Component{

    private String name;
    private double price;

    public Product(String s, double v) {
        this.name=s;
        this.price=v;
    }


    @Override
    public double calculatePrice() {
        return price;
    }
}
