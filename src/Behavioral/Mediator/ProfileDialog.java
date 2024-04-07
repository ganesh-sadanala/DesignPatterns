package Behavioral.Mediator;

public class ProfileDialog implements DialogMediator{
    Checkbox dogCheckbox;
    TextBox dogNameTextBox;
    Button submitButton;

    public ProfileDialog() {
        dogCheckbox = new Checkbox(this);
        dogNameTextBox = new TextBox(this);
        submitButton = new Button(this);
    }


    @Override
    public void notify(UIControl uiControl, String event) {
        if(uiControl==dogCheckbox && event.equals("CheckboxSelected")){
            dogNameTextBox.handleEvent("CheckboxSelected");
        }else if(uiControl == submitButton && event.equals("ButtonClicked")){
            if(validateForm()){
                saveData();
            }
        }
    }

    private boolean validateForm() {
        // Validate form fields
        // ...
        return true;
    }

    private void saveData() {
        // Save form data
        // ...
    }
}
