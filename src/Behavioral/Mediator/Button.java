package Behavioral.Mediator;

public class Button extends UIControl{
    DialogMediator mediator;

    Button(DialogMediator mediator) {
        super(mediator);
    }

    public void click(){
        mediator.notify(this, "ButtonClicked");
    }
    @Override
    void handleEvent(String event) {

    }
}
