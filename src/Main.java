import Creational.AbstractFactory.*;
import Creational.Builder.*;
import Creational.Factory.Logistics;
import Creational.Factory.RoadLogistics;
import Creational.Factory.SeaLogistics;
import Creational.Factory.Transport;
import Creational.Singleton.Database;

public class Main {

    public static Logistics logistics;
    public static FurnitureFactory furnitureFactory;

    public static Database database;

    public static void configure(String []args){

        switch (args[0]) {
            case "Factory" -> {
                if (args[1].equals("Road")) {
                    logistics = new RoadLogistics();
                } else {
                    logistics = new SeaLogistics();
                }
            }
            case "AbstractFactory" -> {
                if (args[1].equals("Modern")) {
                    furnitureFactory = new ModernFurnitureFactory();
                } else furnitureFactory = new VictorianFurnitureFactory();
            }
            case "Builder" -> {
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
            }
            case "Singleton" -> database = Database.getDatabase();
        }
    }

    public static void runBusinessLogic(){
        // Creational.Factory
        Transport transport = logistics.createTransport();
        transport.deliver();

        // Creational.AbstractFactory
        Chair chair = furnitureFactory.createChair();
        Sofa sofa = furnitureFactory.createSofa();
        CoffeeTable coffeeTable = furnitureFactory.createCoffeeTable();
    }

    public static void main(String[] args) {
        configure(args);
        runBusinessLogic();

    }
}