package ui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
Represents the Frame the gui is to be presented in, and initializes the beginning of the program to create new game or
to load an old one
 */
public class GameFrame extends JFrame {
    UtilityPanel handleGameButtons;

    //EFFECTS: sets up the frame settings and initializes the utilities and beginning choices
    GameFrame() {

        handleGameButtons = new UtilityPanel();
        this.add(handleGameButtons);

//        setComponentZOrder(panelBorder, 1);

        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }



    //BoardPanel boardPanel = new BoardPanel();
    //this.add(boardPanel);
    //setComponentZOrder(boardPanel, 2);

}
