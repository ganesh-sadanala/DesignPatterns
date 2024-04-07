package Behavioral.Mediator;

public class Checkbox extends UIControl{

    boolean isChecked;
    Checkbox(DialogMediator mediator) {
        super(mediator);
    }

    boolean isChecked(){
        return isChecked;
    }

    void setChecked(boolean checked){
        isChecked=checked;
        mediator.notify(this, "CheckboxSelected");
    }

    @Override
    void handleEvent(String event) {

    }
}
