package ui;


import ui.buttons.LoadButton;
import ui.buttons.NewGameButton;
import ui.buttons.SaveButton;
import ui.buttons.UtilityButton;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import static ui.GameFrame.INTERFACE_WIDTH;

/*
Represents the outer border of the board, as well as in charge of keeping track of the board in progress
 and creating a new board
 */
public class UtilityPanel extends JPanel implements ActionListener {
    private static final int UTILITY_HEIGHT = 100;
    private static final int BUTTON_MARGIN = 10;

    GameFrame gameFramework;
    JButton saveButton;
    JButton loadButton;
    JButton newButton;
    JTextComponent errorText;

    UtilityButton beginnerButton;
    UtilityButton intermediateButton;
    UtilityButton advancedButton;


    //EFFECTS: Initializes the 3 utility buttons that the user can interact with, setting the saveButton to unenabled
    // at the moment as there is no Board in progress
    public UtilityPanel(GameFrame gameFramework) {
        this.gameFramework = gameFramework;
        GridLayout utilLayout = new GridLayout(1, 3, BUTTON_MARGIN, BUTTON_MARGIN);
        this.setLayout(utilLayout);

        setPreferredSize(new Dimension(INTERFACE_WIDTH, UTILITY_HEIGHT));
        setBackground(Color.lightGray);

        addUtilityButtons(utilLayout);
        errorText = new JTextField();
        errorText.setVisible(false);
        add(errorText, utilLayout);
        errorText.setLocation(INTERFACE_WIDTH / 2, UTILITY_HEIGHT / 2);
    }

    //EFFECTS: initializes buttons in utility menu
    private void addUtilityButtons(GridLayout utilLayout) {
        saveButton = new SaveButton("save game", gameFramework, this);
        this.add(saveButton, utilLayout);
        saveButton.setEnabled(false);

        loadButton = new LoadButton("load game", gameFramework, this);
        this.add(loadButton, utilLayout);

        newButton = new NewGameButton("new game", gameFramework, this);
        this.add(newButton, utilLayout);
    }

    public void enableSaveButton() {
        saveButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ("load".equals(cmd)) {
            loadButtonAction();
        } else if ("save".equals(cmd)) {
            saveButtonAction();
        } else if ("new game".equals(cmd)) {
            addNewGameOptions();
        } else if ("make beginner board".equals(cmd)) {
            gameFramework.setBeginnerBoard();
            removeNewGameOptions();
        } else if ("make intermediate board".equals(cmd)) {
            gameFramework.setIntermediateBoard();
            removeNewGameOptions();
        } else if ("make advanced board".equals(cmd)) {
            gameFramework.setAdvancedBoard();
            removeNewGameOptions();
        }
    }

    //REQUIRES: the new game option buttons != null
    private void removeNewGameOptions() {
        this.remove(beginnerButton);
        this.remove(intermediateButton);
        this.remove(advancedButton);
        revalidate();
    }

    //EFFECTS: saves the board into the game
    private void saveButtonAction() {
        try {
            gameFramework.saveBoardFromGame();
            Calendar cal = Calendar.getInstance();
            errorText.setText("GAME SAVED AT " + cal.getTime());
            errorText.setVisible(true);
            gameFramework.revalidate();

        } catch (FileNotFoundException ex) {
            errorText.setText("OOP, UNABLE TO SAVE GAME.");
            errorText.setVisible(true);
            gameFramework.revalidate();
        }
    }

    //EFFECTS: loads the json file into the game
    private void loadButtonAction() {
        try {
            gameFramework.loadBoardIntoGame();
        } catch (IOException ex) {
            errorText.setText("OOP, UNABLE TO LOAD GAME.");
            errorText.setVisible(true);
            gameFramework.revalidate();
        }
    }

    //EFFECTS: creates buttons that if clicked, produce beginner, intermediate, and advanced boards
    private void addNewGameOptions() {
        beginnerButton = new UtilityButton("Beginner Board", gameFramework, this);
        beginnerButton.setActionCommand("make beginner board");
        intermediateButton = new UtilityButton("Intermediate Board", gameFramework, this);
        intermediateButton.setActionCommand("make intermediate board");
        advancedButton = new UtilityButton("Advanced Board", gameFramework, this);
        advancedButton.setActionCommand("make advanced board");

        this.add(beginnerButton);
        this.add(intermediateButton);
        this.add(advancedButton);
        revalidate();
    }

    public void disableSaveButton() {
        saveButton.setEnabled(false);
    }
}
