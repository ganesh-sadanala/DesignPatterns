package Builder;

public class CarBuilder implements Builder{
    private Car car;

    @Override
    public void reset() {

    }

    @Override
    public void setSeats(Long number) {

    }

    @Override
    public void setEngine(String engine) {

    }

    @Override
    public void setTripComputer() {

    }

    @Override
    public void setGPS() {

    }

    public Car getResult(){
        return new Car();
    }
}
