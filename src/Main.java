import Creational.AbstractFactory.*;
import Creational.Builder.*;
import Creational.Factory.Logistics;
import Creational.Factory.RoadLogistics;
import Creational.Factory.SeaLogistics;
import Creational.Factory.Transport;
import Creational.Singleton.Database;
import Structural.Adapter.RoundHole;
import Structural.Adapter.RoundPeg;
import Structural.Adapter.SquarePeg;
import Structural.Adapter.SquarePegAdapter;
import Structural.Decorator.FacebookDecorator;
import Structural.Decorator.Notifier;
import Structural.Decorator.SMSDecorator;
import Structural.Decorator.SlackDecorator;
import Structural.Facade.VideoConverter;

public class Main {

    public static Logistics logistics;
    public static FurnitureFactory furnitureFactory;

    public static Database database;

    public static VideoConverter videoConverter;

    public static Notifier stack;
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
            case "Adapter" -> {
                RoundHole hole = new RoundHole(5l);
                RoundPeg rpeg = new RoundPeg(5l);
                hole.fits(rpeg); // true

                SquarePeg small_sqpeg = new SquarePeg(5l);
                SquarePeg large_sqpeg = new SquarePeg(10l);
//                hole.fits(small_sqpeg) // this won't compile (incompatible types)

                SquarePegAdapter small_sqpeg_adapter = new SquarePegAdapter(small_sqpeg);
                SquarePegAdapter large_sqpeg_adapter = new SquarePegAdapter(large_sqpeg);
                hole.fits(small_sqpeg_adapter); // true
                hole.fits(large_sqpeg_adapter); // false
            }
            case "Facade" -> videoConverter.convertVideo("abc", "def");
            case "Decorator" ->{
                stack = new Notifier();
                if(args[1] != null) stack = new FacebookDecorator(stack);
                if(args[2] != null) stack = new SlackDecorator(stack);
                if(args[3] != null) stack = new SMSDecorator(stack);

                stack.send("Hello!");
            }
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