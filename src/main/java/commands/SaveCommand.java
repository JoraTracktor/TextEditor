package commands;

import UI.TextEditor;
import saveload.FileWorker;
import search.SearchEngine;

public class SaveCommand extends Command{

    private FileWorker fileWorker;

    public SaveCommand(FileWorker fileWorker) {
        this.fileWorker = fileWorker;
    }

    @Override
    public boolean execute() {
        fileWorker.saveFile();
        return true;
    }
}
