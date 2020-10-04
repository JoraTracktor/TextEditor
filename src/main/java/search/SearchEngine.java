package search;

import UI.TextEditor;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngine {

    private TextEditor textEditor;
    private List<Substring> substringsList =  Collections.synchronizedList(new ArrayList<>());

    private volatile int currentIndex;

    public SearchEngine(TextEditor textEditor){
        this.textEditor = textEditor;
    }

    public synchronized void search(){
        substringsList.clear();
        if (textEditor.getRegexCheckBox().isSelected()){
            searchWithRegex();
        } else {
            searchWithoutRegex();
        }
        currentIndex = 0;
        showFirst();
    }

    private void showFirst(){
        setTextArea();
    }

    public synchronized void showPrevious(){
        if (currentIndex >= 1){
           --currentIndex;
        } else {
            currentIndex = substringsList.size() - 1;
        }
        setTextArea();
    }

    public synchronized void showNext(){ ;
        if (currentIndex < substringsList.size() - 1){
            ++currentIndex;
        } else {
            currentIndex = 0;
        }
        setTextArea();
    }

    private void setTextArea(){
        Substring substring = substringsList.get(currentIndex);
        textEditor.getTextArea().setCaretPosition(substring.getEnd());
        textEditor.getTextArea().select(substring.getBegin(), substring.getEnd());
        textEditor.getTextArea().grabFocus();
    }

    private void searchWithRegex(){
        Pattern pattern = Pattern.compile(textEditor.getTextField().getText());
        Matcher matcher = pattern.matcher(textEditor.getTextArea().getText());

        while (matcher.find()){
            substringsList.add(new Substring(matcher.start(), matcher.end()));
        }
    }

    private void searchWithoutRegex(){
        String pattern = textEditor.getTextField().getText();
        String matcher = textEditor.getTextArea().getText();
        int lastIndex = 0;
        int firstIndex = 0;

        while (lastIndex != -1) {
            lastIndex = matcher.indexOf(pattern, lastIndex);
            firstIndex = lastIndex;
            if (lastIndex != -1) {
                lastIndex += pattern.length();
                substringsList.add(new Substring(firstIndex, lastIndex));
            }
        }
    }
}
