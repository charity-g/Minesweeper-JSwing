package ui;


import ui.buttons.LoadButton;
import ui.buttons.NewGameButton;
import ui.buttons.SaveButton;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

        setPreferredSize(new Dimension(INTERFACE_WIDTH, UTILITY_HEIGHT));
        setBackground(Color.lightGray);

        addUtilityButtons();
        errorText = new JTextField();
        errorText.setVisible(false);
        add(errorText);
        errorText.setLocation(INTERFACE_WIDTH / 2, UTILITY_HEIGHT / 2);
    }

    //EFFECTS: initializes buttons in utility menu
    private void addUtilityButtons() {
        saveButton = new SaveButton("save game", gameFramework, this);
        this.add(saveButton);
        saveButton.setEnabled(false);

        loadButton = new LoadButton("load game", gameFramework, this);
        this.add(loadButton);

        newButton = new NewGameButton("new game", gameFramework, this);
        this.add(newButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("load".equals(e.getActionCommand())) {
            try {
                gameFramework.loadBoardIntoGame();
                BoardPanel newPanel = new BoardPanel(gameFramework.getBoardInProgress(), gameFramework);
                gameFramework.removeActiveBoard();

                gameFramework.addActiveBoard(newPanel);
                gameFramework.revalidate();
            } catch (IOException ex) {
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
