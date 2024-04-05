package Behavioral.Command;

public class CutCommand implements Command{

    private Editor editor;
    private String backup;

    public CutCommand(Editor editor) {
        this.editor = editor;
    }
    @Override
    void execute() {
        backup=editor.getSelection();
        editor.deleteSelection();
    }

    @Override
    public void undo() {
        editor.restoreSelection(backup);
    }
}
