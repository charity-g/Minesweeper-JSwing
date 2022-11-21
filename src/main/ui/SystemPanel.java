package ui;

import org.json.JSONException;
import ui.buttons.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

/*
Represents an Action Listener that can take in user interaction with a buttons panel and calls appropriate GameFrame
method. These buttons control aspects of the board onscreen, such as saving, loading and creating a new board
 */
public class SystemPanel extends JPanel implements ActionListener {
    private LayoutManager systemPanelLayout;
    GridBagConstraints constraint;

    private GameFrame gameFrame;

    private JButton saveButton;
    private JButton loadButton;
    private JButton newButton;
    private JTextComponent savingMessage;
    private JTextComponent errorText;

    private JButton beginnerButton;
    private JButton intermediateButton;
    private JButton advancedButton;

    public SystemPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

        this.setBackground(Color.lightGray);
        this.setBounds(0, 0, gameFrame.INTERFACE_WIDTH, GameFrame.SYSTEM_PANEL_HEIGHT);

        systemPanelLayout = new GridBagLayout();
        setLayout(systemPanelLayout);
        constraint = new GridBagConstraints();

        addSystemButtonsToPanel();
        addTextToPanel();

        setVisible(true);
    }

    //MODIFIES: this, gameFrame
    //EFFECTS: handles the action event command and determines next course of action for GameFrame to manage
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ("load".equals(cmd)) {
            loadButtonAction();
        } else if ("save".equals(cmd)) {
            saveButtonAction();
        } else if ("new game".equals(cmd)) {
            addNewGameOptions();
        } else {
            if ("beginner".equals(cmd)) {
                gameFrame.makeNewBeginnerBoard();
            } else if ("intermediate".equals(cmd)) {
                gameFrame.makeNewIntermediateBoard();
            } else if ("advanced".equals(cmd)) {
                gameFrame.makeNewAdvancedBoard();
            }
            removeNewGameOptions();
            saveButton.setEnabled(true);
        }
    }

    //MODIFIES: this, errorText
    //EFFECTS: shows the error message
    public void showErrorMessage(String text) {
        errorText.setText(text);
        errorText.setVisible(true);
        revalidate();
    }

    //PRIVATE METHODS =========================

    //MODIFIES: the data file this.gameFrame.game is programmed to save into
    private void saveButtonAction() {
        try {
            gameFrame.saveBoardIntoJson();
            Calendar cal = Calendar.getInstance();
            savingMessage.setText("GAME SAVED AT " + cal.getTime());
            savingMessage.setVisible(true);
            this.revalidate();
        } catch (FileNotFoundException ex) {
            showErrorMessage("OOP, UNABLE TO SAVE GAME INTO FILE.");
        }

    }

    //MODIFIES: this.gameFrame.game.boardInProgress
    //EFFECTS: loads the board into the game, but catches if unable to load to display error
    private void loadButtonAction() {
        try {
            gameFrame.loadBoardIntoGame();
            saveButton.setEnabled(true);
        } catch (IOException ex) {
            showErrorMessage("OOP, UNABLE TO LOAD GAME FROM A FILE THAT DOESN'T EXIST.");
        } catch (JSONException ex) {
            showErrorMessage("OOP, NO GAME SAVED.");
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes the save, load and new game buttons with the respective layout and functionality
    private void addSystemButtonsToPanel() {
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 1;
        ;
        constraint.insets = new Insets(0, 15, 0, 15);
        constraint.gridx = 0;
        constraint.gridy = 0;

        saveButton = new SaveButton("save game", this);
        this.add(saveButton, constraint);
        saveButton.setEnabled(false);

        constraint.gridx = 1;
        loadButton = new LoadButton("load game", this);
        this.add(loadButton, constraint);

        constraint.gridx = 2;
        newButton = new NewGameButton("new game", this);
        this.add(newButton, constraint);
    }

    //MODIFIES: this
    //EFFECTS: initializes the error text and the layout
    private void addTextToPanel() {
        savingMessage = new JTextArea("SAVED AT <TIME> GOES HERE");
        savingMessage.setEditable(false);
        savingMessage.setOpaque(false);
        constraint.gridx = 0;
        constraint.gridy = 1;
        add(savingMessage, constraint);

        errorText = new JTextArea("ERROR MESSAGES GOES HERE");
        errorText.setEditable(false);
        errorText.setOpaque(false);

        constraint.gridx = 1;
        constraint.gridy = 1;
        add(errorText, constraint);
    }

    //EFFECTS: reveals the 3 button options: beginner, intermediate and advanced for users to interact with
    private void addNewGameOptions() {
        beginnerButton = new SystemButton("Beginner", this);
        beginnerButton.setActionCommand("beginner");
        intermediateButton = new SystemButton("Intermediate", this);
        intermediateButton.setActionCommand("intermediate");
        advancedButton = new SystemButton("Advanced", this);
        advancedButton.setActionCommand("advanced");

        constraint.insets = new Insets(0, 15, 0, 15);
        constraint.gridx = 2;
        constraint.gridy = 2;
        this.add(beginnerButton, constraint);

        constraint.gridx = 3;
        this.add(intermediateButton, constraint);

        constraint.gridx = 4;
        this.add(advancedButton, constraint);
        revalidate();
    }

    //EFFECTS: removes the new Game options from the screen
    private void removeNewGameOptions() {
        this.remove(beginnerButton);
        this.remove(intermediateButton);
        this.remove(advancedButton);
        this.revalidate();
        this.repaint();
    }

    public void disableSaveButton() {
        this.saveButton.setEnabled(false);
    }
}
