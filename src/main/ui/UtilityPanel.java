package ui;


import ui.buttons.LoadButton;
import ui.buttons.NewGameButton;
import ui.buttons.SaveButton;

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

    GameFrame gameFramework;
    JButton saveButton;
    JButton loadButton;
    JButton newButton;
    JTextComponent errorText;

    //EFFECTS: Initializes the 3 utility buttons that the user can interact with, setting the saveButton to unenabled
    // at the moment as there is no Board in progress
    public UtilityPanel(GameFrame gameFramework) {
        this.gameFramework = gameFramework;
        BorderLayout utilLayout = new BorderLayout();

        setPreferredSize(new Dimension(INTERFACE_WIDTH, UTILITY_HEIGHT));
        setBackground(Color.lightGray);

        addUtilityButtons(utilLayout);
        errorText = new JTextField();
        errorText.setVisible(false);
        add(errorText, utilLayout.SOUTH);
        errorText.setLocation(INTERFACE_WIDTH / 2, UTILITY_HEIGHT / 2);
    }

    //EFFECTS: initializes buttons in utility menu
    private void addUtilityButtons(BorderLayout utilLayout) {
        saveButton = new SaveButton("save game", gameFramework, this);
        this.add(saveButton, utilLayout.NORTH);
        saveButton.setEnabled(false);

        loadButton = new LoadButton("load game", gameFramework, this);
        this.add(loadButton, utilLayout.NORTH);

        newButton = new NewGameButton("new game", gameFramework, this);
        this.add(newButton, utilLayout.NORTH);
    }


    public void enableSaveButton() {
        saveButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("load".equals(e.getActionCommand())) {
            try {
                gameFramework.loadBoardIntoGame();
            } catch (IOException ex) {
                errorText.setText("OOP, UNABLE TO LOAD GAME.");
                errorText.setVisible(true);
                gameFramework.revalidate();
            }
        } else if ("save".equals(e.getActionCommand())) {
            try {
                gameFramework.saveBoardFromGame();
                errorText.setText("GAME SAVED AT " + Calendar.getInstance());
                errorText.setVisible(true);
                gameFramework.revalidate();

            } catch (FileNotFoundException ex) {
                errorText.setText("OOP, UNABLE TO SAVE GAME.");
                errorText.setVisible(true);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException exc) {
                    throw new RuntimeException(exc);
                }
                errorText.setVisible(false);
            }
        }
    }
}
