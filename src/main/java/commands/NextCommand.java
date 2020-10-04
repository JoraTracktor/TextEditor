package commands;

import search.SearchEngine;

public class NextCommand extends Command{

    private SearchEngine searchEngine;
    public NextCommand(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public boolean execute() {
        searchEngine.showNext();
        return true;
    }
}
