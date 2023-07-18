package Creational.Factory;

// Client code
public class Logistics{
    public void planDelivery(){}
    // Doesn't see a difference since all are Transport and have deliver() method
    public Transport createTransport(){
        return null;
    }
}