package commands;

public enum CommandName {
    LOAD,
    SAVE,
    SAVEAS,
    SEARCH,
    PREVIOUS,
    NEXT,
    INCREASE,
    DECREASE;

    public String toString() {
        return name().toLowerCase();
    }
}
