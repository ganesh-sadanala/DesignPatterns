package Structural.Adapter;

// extends/implements Target/Client: RoundPeg
public class SquarePegAdapter extends RoundPeg{

    // Adaptee
    private SquarePeg squarePeg;

    public SquarePegAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    // translates the calls from Target to make use of Adaptee and delivers what client/target expects
    @Override
    public Long getRadius() {
        return (long) (squarePeg.getWidth() * Math.sqrt(2) / 2);
    }
}
