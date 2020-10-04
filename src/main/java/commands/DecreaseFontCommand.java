package commands;

import UI.TextEditor;

public class DecreaseFontCommand extends Command{
    private TextEditor textEditor;

    public DecreaseFontCommand(TextEditor textEditor){
        this.textEditor = textEditor;
    }

    @Override
    public boolean execute() {
        textEditor.decreaseFontSize();
        return true;
    }
}
