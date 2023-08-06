package Behavioral.StateDelegation;

public class Moderation implements State{
    Document document;
    public Moderation(Document document){
        this.document = document;
    }

    @Override
    public void render(){
        System.out.println("Moderation");
    }

    @Override
    public void publish() {
        document.changeState(new Published(document));
    }
}
