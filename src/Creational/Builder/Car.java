package Creational.Builder;

public class Car {

    private Long seats;
    private String engine;
    private boolean tripComputer;
    private boolean gps;

    private Car(){

    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Car car;

        public Builder(){
            car=new Car();
        }

        public Builder setSeats(Long number) {
            car.seats = number;
            return this;
        }

        public Builder setEngine(String engine) {
            car.engine = engine;
            return this;
        }

        public Builder setTripComputer() {
            car.tripComputer = true;
            return this;
        }

        public Builder setGPS() {
            car.gps = true;
            return this;
        }

        public Car build() {
            return car;
        }
    }

    public Long getSeats() {
        return seats;
    }

    public String getEngine() {
        return engine;
    }

    public boolean hasTripComputer() {
        return tripComputer;
    }

    public boolean hasGPS() {
        return gps;
    }
}
