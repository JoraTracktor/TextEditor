package commands;

import saveload.FileWorker;

public class SaveAsCommand extends Command {

    private FileWorker fileWorker;

    public SaveAsCommand(FileWorker fileWorker) {
        this.fileWorker = fileWorker;
    }

    @Override
    public boolean execute() {
        fileWorker.saveFile();
        return true;
    }
}
