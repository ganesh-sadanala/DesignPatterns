package Behavioral.Visitor;

public class CompoundShape implements Shape{
    @Override
    public void move(int x, int y) {

    }

    @Override
    public void draw() {

    }

    @Override
    public void accept(Visitor v) {
        v.visitCompoundShape(this);
    }
}
