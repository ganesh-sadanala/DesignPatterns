package Behavioral.Mediator;

public abstract class UIControl {
    DialogMediator mediator;

    UIControl(DialogMediator mediator){
        this.mediator=mediator;
    }


    abstract void handleEvent(String event);

}
