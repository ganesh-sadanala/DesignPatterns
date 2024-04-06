package Structural.Composite;

import java.util.ArrayList;
import java.util.List;

public class Box implements Component{
    List<Component> components;
    double packagingCost;

    public Box(double packagingCost) {
        this.components = new ArrayList<>();
        this.packagingCost = packagingCost;
    }

    public void addComponent(Component component){
        components.add(component);
    }

    public void removeComponent(Component component){
        components.remove(component);
    }


    @Override
    public double calculatePrice() {
        double totalPrice = 0;
        for (Component component : components) {
            totalPrice += component.calculatePrice();
        }
        return totalPrice + packagingCost;
    }
}
