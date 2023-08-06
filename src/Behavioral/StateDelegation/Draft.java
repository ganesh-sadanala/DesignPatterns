package Behavioral.StateDelegation;

public class Draft implements State{
    Document document;


    @Override
    public void render(){
        System.out.println("Draft");
    }

    @Override
    public void publish() {
        document.changeState(new Moderation(document));
    }
}
