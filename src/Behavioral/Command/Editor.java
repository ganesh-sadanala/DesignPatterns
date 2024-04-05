package Behavioral.Command;

// The editor class has actual text editing operations.
// Plays the role of a receiver: all commands end up delegating
// execution of editor methods
public class Editor {
    String text;

    String getSelection(){
        return text;
    }

    void deleteSelection(){
        text="";
    }

    void replaceSelection(String text){
        // Replace the clipboards contents at the current position
        this.text=text;
    }

    public void restoreSelection(String backupText) {
        // Restore the backup text
        text = backupText;
    }
}
