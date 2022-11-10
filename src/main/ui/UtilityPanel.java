package ui;

import ui.buttons.*;

import javax.swing.*;
import java.awt.*;

/*
Represents the outer border of the board, as well as in charge of keeping track of the board in progress
 and creating a new board
 */
public class UtilityPanel extends JPanel {
    static final int INTERFACE_HEIGHT = 600;
    static final int INTERFACE_WIDTH = 1000;

    public UtilityPanel() {
        setPreferredSize(new Dimension(INTERFACE_WIDTH, INTERFACE_HEIGHT));
        setBackground(Color.lightGray);

        //TODO: implement button functionality
        JButton saveButton = new SaveButton("save game");
        add(saveButton);
        saveButton.setEnabled(true);

        JButton loadButton = new LoadButton("load game");
        add(loadButton);
        loadButton.setEnabled(true);

        JButton newButton = new NewGameButton("new game");
        add(newButton);
        newButton.setEnabled(true);
    }
}
