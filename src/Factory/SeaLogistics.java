package Factory;
import Factory.Logistics;
import Factory.Transport;

public class SeaLogistics extends Logistics {
    @Override
    public Transport createTransport(){
        return new Ship();
    }
}