package UI;

import commands.*;
import saveload.FileWorker;
import search.SearchEngine;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import static commands.CommandName.*;

public class TextEditor extends JFrame {

    JPanel northPanel;
    JTextArea textArea;
    JTextField textField;
    JFileChooser fileChooser;
    JCheckBox regexCheckBox;


    ControllerCommands controllerCommands;
    private HashMap<CommandName, Command> commands = new HashMap<>();
    private SearchEngine searchEngine;
    private FileWorker fileWorker;

    private volatile int textAreaFontSize = 14;
    private final int fontSize = 14;
    Font timesRomanBold = new Font("TimesRoman", Font.BOLD, fontSize);
    //private synchronized

    public TextEditor() {
        northPanel = new JPanel();
        textArea = new JTextArea();
        textArea.setFont(new Font("TimesRoman", Font.BOLD, textAreaFontSize));
        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        controllerCommands = new ControllerCommands();
        initCommands();
        createMenuBar();
        setNorthPanel();
        setScrollPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Text Editor");
        Dimension dimension = new Dimension(750,600);
        setSize(dimension);
        setMinimumSize(dimension);
        dispose();
    }

    public synchronized void increaseFontSize(){
        textAreaFontSize++;
        textArea.setFont(new Font("TimesRoman", Font.BOLD, textAreaFontSize));
    }
    public synchronized void decreaseFontSize(){
        textAreaFontSize--;
        textArea.setFont(new Font("TimesRoman", Font.BOLD, textAreaFontSize));
    }


    private void initCommands(){
        searchEngine = new SearchEngine(this);
        fileWorker = new FileWorker(this);

        commands.put(LOAD, new LoadCommand(fileWorker));
        commands.put(SAVE, new SaveCommand(fileWorker));
        commands.put(SAVEAS, new SaveAsCommand(fileWorker));
        commands.put(SEARCH, new SearchCommand(searchEngine));
        commands.put(PREVIOUS, new PreviousCommand(searchEngine));
        commands.put(NEXT, new NextCommand(searchEngine));
        commands.put(INCREASE, new IncreaseFontCommand(this));
        commands.put(DECREASE, new DecreaseFontCommand(this));
    }

    private void setScrollPanel(){
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setName("ScrollPane");
        add(scrollableTextArea, BorderLayout.CENTER);
    }

    private void setNorthPanel(){
        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(northPanel, BorderLayout.NORTH);

        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        textField = new JTextField("", 15);
        textField.setFont(timesRomanBold);
        JButton searchButton = new JButton("Search");
        JButton previousButton = new JButton("Prev");
        JButton nextButton = new JButton("Next");
        regexCheckBox = new JCheckBox("Use Regex");
        JButton increaseFontButton = new JButton("+");
        JButton decreaseFontButton = new JButton("-");

        loadButton.addActionListener(actionEvent -> loadButtonCommand());
        saveButton.addActionListener(actionEvent -> saveButtonCommand());
        searchButton.addActionListener(actionEvent -> searchButtonCommand());
        previousButton.addActionListener(actionEvent -> previousButtonCommand());
        nextButton.addActionListener(actionEvent -> nextButtonCommand());

        increaseFontButton.addActionListener(actionEvent -> {
            controllerCommands.setCommand(commands.get(INCREASE));
            controllerCommands.executeCommand();
        });

        decreaseFontButton.addActionListener(actionEvent -> {
            controllerCommands.setCommand(commands.get(DECREASE));
            controllerCommands.executeCommand();
        });

        northPanel.add(loadButton);
        northPanel.add(saveButton);
        northPanel.add(textField);
        northPanel.add(searchButton);
        northPanel.add(previousButton);
        northPanel.add(nextButton);
        northPanel.add(regexCheckBox);
        northPanel.add(increaseFontButton);
        northPanel.add(decreaseFontButton);
    }

    private void createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.setName("MenuFile");
        menuBar.add(fileMenu);
        JMenu searchMenu = new JMenu("Search");
        searchMenu.setMnemonic(KeyEvent.VK_S);
        searchMenu.setName("MenuSearch");
        menuBar.add(searchMenu);

        fileMenu.setFont(timesRomanBold);
        searchMenu.setFont(timesRomanBold);

        JMenuItem loadMenuItem = new JMenuItem("Load");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem saveAsMenuItem = new JMenuItem("Save as");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        loadMenuItem.setName("MenuLoad");
        saveMenuItem.setName("MenuSave");
        saveAsMenuItem.setName("MenuSaveAs");
        exitMenuItem.setName("MenuExit");

        loadMenuItem.setFont(timesRomanBold);
        saveMenuItem.setFont(timesRomanBold);
        saveAsMenuItem.setFont(timesRomanBold);
        exitMenuItem.setFont(timesRomanBold);

        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        JMenuItem startSearchMenuItem = new JMenuItem("Start search");
        JMenuItem previousMatchMenuItem = new JMenuItem("Previous search");
        JMenuItem nextMatchMenuItem = new JMenuItem("Next match");
        JMenuItem useRegMenuItem = new JMenuItem("Use regular expressions");

        startSearchMenuItem.setFont(timesRomanBold);
        previousMatchMenuItem.setFont(timesRomanBold);
        nextMatchMenuItem.setFont(timesRomanBold);
        useRegMenuItem.setFont(timesRomanBold);

        searchMenu.add(startSearchMenuItem);
        searchMenu.add(previousMatchMenuItem);
        searchMenu.add(nextMatchMenuItem);
        searchMenu.add(useRegMenuItem);

        loadMenuItem.addActionListener(actionEvent -> loadButtonCommand());
        saveMenuItem.addActionListener(actionEvent -> saveButtonCommand());
        saveAsMenuItem.addActionListener(actionEvent -> saveAsButtonCommand());
        exitMenuItem.addActionListener(actionEvent -> this.dispose());

        startSearchMenuItem.addActionListener(actionEvent -> searchButtonCommand());
        previousMatchMenuItem.addActionListener(actionEvent -> previousButtonCommand());
        nextMatchMenuItem.addActionListener(actionEvent -> nextButtonCommand());
        useRegMenuItem.addActionListener(actionEvent -> this.regexCheckBox.setSelected(true));
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JCheckBox getRegexCheckBox() {
        return regexCheckBox;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    private void saveButtonCommand(){
        controllerCommands.setCommand(commands.get(SAVE));
        controllerCommands.executeCommand();
    }

    private void saveAsButtonCommand(){
        controllerCommands.setCommand(commands.get(SAVEAS));
        controllerCommands.executeCommand();
    }

    private void loadButtonCommand(){
        controllerCommands.setCommand(commands.get(LOAD));
        controllerCommands.executeCommand();
    }

    private void nextButtonCommand(){
        controllerCommands.setCommand(commands.get(NEXT));
        controllerCommands.executeCommand();
    }

    private void previousButtonCommand(){
        controllerCommands.setCommand(commands.get(PREVIOUS));
        controllerCommands.executeCommand();
    }

    private void searchButtonCommand(){
        controllerCommands.setCommand(commands.get(SEARCH));
        controllerCommands.executeCommand();
    }
}

