import Behavioral.Command.*;
import Behavioral.Iterator.*;
import Behavioral.Observer.Editor;
import Behavioral.Observer.EmailAlertsListener;
import Behavioral.Observer.EventListener;
import Behavioral.Observer.LoggingListener;
import Behavioral.StateDelegation.Document;
import Behavioral.StateDelegation.Moderation;
import Behavioral.StateDelegation.Published;
import Behavioral.Strategy.Navigator;
import Behavioral.Strategy.PublicTransportStrategy;
import Behavioral.Strategy.RoadStrategy;
import Behavioral.Strategy.WalkingStrategy;
import Behavioral.Visitor.JSONExportVisitor;
import Behavioral.Visitor.Visitor;
import Behavioral.Visitor.XMLExportVisitor;
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
import Structural.Composite.Product;
import Structural.Decorator.FacebookDecorator;
import Structural.Decorator.Notifier;
import Structural.Decorator.SMSDecorator;
import Structural.Decorator.SlackDecorator;
import Structural.Facade.VideoConverter;
import Structural.Composite.Component;
import Structural.Composite.Box;

public class Main {

    public static Logistics logistics;
    public static FurnitureFactory furnitureFactory;

    public static Database database;

    public static VideoConverter videoConverter;

    public static Notifier stack;

    public static Navigator navigator;

    public static Editor editor;

    public static SocialNetwork network;
    public static SocialSpammer spammer;

    public static Visitor visitor;

    public static Document document;

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
            case "Strategy" -> {
                navigator = new Navigator();
                switch(args[1]){
                    case "Road" -> navigator.setRouteStrategy(new RoadStrategy());
                    case "Walk" -> navigator.setRouteStrategy(new WalkingStrategy());
                    case "PT" -> navigator.setRouteStrategy(new PublicTransportStrategy());
                }
                navigator.buildRoute(new Point(2, 3), new Point(1, 2));
            }
            case "Observer" -> {
                editor = new Editor();
                editor.getEvents().subscribe("open", new LoggingListener());
                editor.getEvents().subscribe("save", new EmailAlertsListener());
            }
            case "Iterator" -> {
                if(args[1].equals("Facebook")) network=new Facebook();
                else network = new LinkedIn();
                spammer = new SocialSpammer();
                Profile profile = new Profile();
                ProfileIterator iterator = network.createFriendsIterator(profile.getId());
                spammer.send(iterator, "Very important message");

                iterator = network.createCoworkersIterator(profile.getId());
                spammer.send(iterator, "Very important message");
            }
            case "Visitor" -> {
                if(args[1].equals("JSON")) visitor = new JSONExportVisitor();
                else visitor = new XMLExportVisitor();
//                foreach (shape in allShapes) do
//                    shape.accept(exportVisitor)
            }
            case "State" -> {
                document=new Document();
                if(args[1].equals("Admin")) document.changeState(new Published(document);
                else document.changeState(new Moderation(document));
            }
            case "Command" -> {
                Behavioral.Command.Editor editor=new Behavioral.Command.Editor();
                Application app=new Application();

                // Create commands
                Command copyCommand=new CopyCommand(editor);
                Command cutCommand = new CutCommand(editor);
                Command pasteCommand = new PasteCommand(editor);


                // Perform operations
                app.setCommand(copyCommand);
                app.executeCommand();

                app.setCommand(cutCommand);
                app.executeCommand();

                app.setCommand(pasteCommand);
                app.executeCommand();

                // Undo the last command
                app.undoCommand();
            }
            case "Composite" -> {
                Component product1 = new Product("Product 1", 10.0);
                Component product2 = new Product("Product 2", 20.0);
                Component product3 = new Product("Product 3", 30.0);

                // Create boxes and add products
                Box box1 = new Box(5.0);
                box1.addComponent(product1);
                box1.addComponent(product2);

                Box box2 = new Box(10.0);
                box2.addComponent(product3);
                box2.addComponent(box1);

                // Create an order and add products and boxes
                Box order = new Box(0.0);
                order.addComponent(product1);
                order.addComponent(box2);

                // Calculate the total price of the order
                double totalPrice = order.calculatePrice();
                System.out.println("Total Price: $" + totalPrice);
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