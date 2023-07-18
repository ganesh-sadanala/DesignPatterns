package Structural.Adapter;

public class SquarePegAdapter extends RoundPeg{
    private SquarePeg squarePeg;

    public SquarePegAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    @Override
    public Long getRadius() {
        return (long) (squarePeg.getWidth() * Math.sqrt(2) / 2);
    }
}
