package commands;

import saveload.FileWorker;
import search.SearchEngine;

public class SearchCommand extends Command{

    private SearchEngine searchEngine;
    public SearchCommand(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public boolean execute() {
        searchEngine.search();
        return true;
    }
}
