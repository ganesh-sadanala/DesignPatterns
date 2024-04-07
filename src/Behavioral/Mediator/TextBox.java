package Behavioral.Mediator;

public class TextBox extends UIControl{
    TextBox(DialogMediator mediator) {
        super(mediator);
    }

    @Override
    void handleEvent(String event) {
        if(event.equals("CheckboxSelected")){
            setVisible(mediator.isCheckboxSelected());
        }
    }

    public void setVisible(boolean visible) {
        // Code to set visibility of the text box
    }
}
