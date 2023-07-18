package Creational.Builder;

public interface Builder {
    public void reset();
    public void setSeats(Long number);
    public void setEngine(String engine);
    public void setTripComputer();
    public void setGPS();
}
