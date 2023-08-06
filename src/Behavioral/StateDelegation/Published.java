package Behavioral.StateDelegation;

public class Published implements State{
    Document document;
    public Published(Document document){
        this.document = document;
    }
    @Override
    public void render() {
        System.out.println("Published");
    }

    @Override
    public void publish() {
        System.out.println("Already published");
    }
}
