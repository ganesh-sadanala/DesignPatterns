package Structural.Adapter;

public class RoundHole {
    private Long radius;

    public RoundHole(Long radius){
        this.radius=radius;
    }

    public Long getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg roundPeg){
        return getRadius() >= roundPeg.getRadius();
    }

}
