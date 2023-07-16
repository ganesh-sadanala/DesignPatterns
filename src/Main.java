import AbstractFactory.*;
import Builder.*;
import Factory.Logistics;
import Factory.RoadLogistics;
import Factory.SeaLogistics;
import Factory.Transport;

public class Main {

    public static Logistics logistics;
    public static FurnitureFactory furnitureFactory;

    public static void configure(String []args){

        switch(args[0]) {
            case "Factory":
                if (args[1].equals("Road")) {
                    logistics = new RoadLogistics();
                } else {
                    logistics = new SeaLogistics();
                }
                break;
            case "AbstractFactory":
                if (args[1].equals("Modern")) {
                    furnitureFactory = new ModernFurnitureFactory();
                } else furnitureFactory = new VictorianFurnitureFactory();
                break;
            case "Builder":
                Director director = new Director();
                CarBuilder carBuilder = new CarBuilder();
                director.makeSportsCar(carBuilder);
                Car car = carBuilder.getResult();

                CarManualBuilder carManualBuilder = new CarManualBuilder();
                director.makeSportsCar(carManualBuilder);

                // The final product is often retrieved from a builder
                // object since the director isn't aware of and not
                // dependent on concrete builders and products.
                Manual manual = carManualBuilder.getResult();
                break;
        }
    }

    public static void runBusinessLogic(){
        // Factory
        Transport transport = logistics.createTransport();
        transport.deliver();

        // AbstractFactory
        Chair chair = furnitureFactory.createChair();
        Sofa sofa = furnitureFactory.createSofa();
        CoffeeTable coffeeTable = furnitureFactory.createCoffeeTable();
    }

    public static void main(String[] args) {
        configure(args);
        runBusinessLogic();

    }
}