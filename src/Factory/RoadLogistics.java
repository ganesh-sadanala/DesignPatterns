package Factory;
import Factory.Logistics;
import Factory.Transport;

public class RoadLogistics extends Logistics{

    @Override
    public Transport createTransport(){
        return new Truck();
    }
}