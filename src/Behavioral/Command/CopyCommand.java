package Behavioral.Command;

public class CopyCommand implements Command{

    private Editor editor;
    private String backup;

    public CopyCommand(Editor editor) {
        this.editor = editor;
    }

    @Override
    void execute() {
        backup=editor.getSelection();

    }

    @Override
    public void undo() {
        // not applicable
    }
}
