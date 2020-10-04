package commands;

import UI.TextEditor;

import java.awt.*;

public class IncreaseFontCommand extends Command{

    private TextEditor textEditor;

    public IncreaseFontCommand(TextEditor textEditor){
        this.textEditor = textEditor;
    }

    @Override
    public boolean execute() {
        textEditor.increaseFontSize();
        return true;
    }
}
