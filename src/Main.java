import Factory.Logistics;
import Factory.RoadLogistics;
import Factory.SeaLogistics;
import Factory.Transport;

public class Main {

    public static Logistics logistics;

    public static void configure(String arg){
        if(arg.equals("Road")){
            logistics = new RoadLogistics();
        }else{
            logistics = new SeaLogistics();
        }
    }

    public static void runBusinessLogic(){
        Transport transport = logistics.createTransport();
        transport.deliver();
    }

    public static void main(String[] args) {

        configure(args[0]);
        runBusinessLogic();

    }
}