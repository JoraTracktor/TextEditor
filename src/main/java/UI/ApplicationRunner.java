package UI;

import translate.TranslateEngine;

import java.awt.*;
import java.io.IOException;

public class ApplicationRunner {
    public static void main(String[] args) {
        TranslateEngine engine = new TranslateEngine();
        try {
            engine.translate1();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> new TextEditor().setVisible(true));
    }
}
