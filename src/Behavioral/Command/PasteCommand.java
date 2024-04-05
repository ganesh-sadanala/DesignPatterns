package Behavioral.Command;

public class PasteCommand implements Command{

    private Editor editor;
    private String backup;

    PasteCommand(Editor editor){
        this.editor=editor;
    }
    @Override
    void execute() {
        backup=editor.getSelection();
        editor.replaceSelection("Pasted text");
    }

    @Override
    public void undo() {
        editor.restoreSelection(backup);
    }
}
