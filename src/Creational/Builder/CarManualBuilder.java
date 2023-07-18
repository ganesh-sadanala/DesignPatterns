package Creational.Builder;

public class CarManualBuilder implements Builder{
    private Manual manual;

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

    public Manual getResult(){
        return new Manual();
    }
}
