package saveload;

import UI.TextEditor;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWorker {

    private TextEditor textEditor;
    private Path currentFilePath;
    private boolean firstSave = true;

    public FileWorker(TextEditor textEditor){
        this.textEditor = textEditor;
    }

    public void saveFile(){
        if (firstSave){
            saveFileAS();
            if (currentFilePath != null){
                firstSave = false;
            }
            return;
        }

        try {
            Files.writeString(currentFilePath, textEditor.getTextArea().getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFileAS(){
        JFileChooser fileChooser = textEditor.getFileChooser();
        fileChooser.setDialogTitle("Choose path to save:");

        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            currentFilePath = Paths.get(fileChooser.getSelectedFile().getAbsolutePath());
        }
        System.out.println("qwer");
        System.out.println(currentFilePath.toAbsolutePath());
        try {
            Files.writeString(currentFilePath, textEditor.getTextArea().getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFile(){
        Path file = null;
        JFileChooser fileChooser = textEditor.getFileChooser();
        fileChooser.setDialogTitle("Choose file to open:");

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = Paths.get(fileChooser.getSelectedFile().getAbsolutePath());
        }
        currentFilePath = file;

        String text;
        try {
            text = new String(Files.readAllBytes(file));
        } catch (IOException e) {
            e.printStackTrace();
            text = "";
        }
        textEditor.getTextArea().setText(text);
    }
}
