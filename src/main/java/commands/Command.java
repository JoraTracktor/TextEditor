package commands;

import saveload.FileWorker;
import search.SearchEngine;

public abstract class Command {

    private String backup;

    void backup() {

    }
    public abstract boolean execute();
}
