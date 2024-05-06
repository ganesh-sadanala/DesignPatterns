package Structural.Decorator;

public abstract class BaseDecorator extends Notifier{
    Notifier notifier;
    BaseDecorator(Notifier notifier){
        this.notifier=notifier;
    }

    @Override
    public void send(String message) {
        notifier.send(message);
    }
}
