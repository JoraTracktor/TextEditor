package commands;

import saveload.FileWorker;
import search.SearchEngine;

public class PreviousCommand extends Command{

    private SearchEngine searchEngine;
    public PreviousCommand(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public boolean execute() {
        searchEngine.showPrevious();
        return true;
    }
}
