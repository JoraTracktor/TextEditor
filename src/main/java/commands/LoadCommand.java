package commands;

import saveload.FileWorker;

public class LoadCommand extends Command{

    private FileWorker fileWorker;

    public LoadCommand(FileWorker fileWorker) {
        this.fileWorker = fileWorker;
    }

    @Override
    public boolean execute() {
        fileWorker.loadFile();
        return true;
    }
}
