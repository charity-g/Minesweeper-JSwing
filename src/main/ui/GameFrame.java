package ui;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
Represents the Frame the gui(board and utility buttons) are to be presented in, and initializes the beginning of the
 program to create new game or to load an old one
 */
public class GameFrame extends JFrame {
    static final int INTERFACE_HEIGHT = 600;
    static final int INTERFACE_WIDTH = 1000;
    static final int MARGIN = 100;
    private static final int ORIGIN_X = INTERFACE_WIDTH / 2;
    private static final int ORIGIN_Y = INTERFACE_HEIGHT / 2;

    Game game;
    UtilityPanel bkgLayer;
    BoardPanel activeBoardPanel;
    BorderLayout layout;

    //EFFECTS: sets up the frame settings and initializes the utilities and beginning choices
    GameFrame() {
        game = new Game();

        layout = new BorderLayout();
        setLayout(layout);

        bkgLayer = new UtilityPanel(this);
        add(bkgLayer, layout.NORTH);

        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    //MODIFIES: this
    //EFFECTS: removes the BoardPanel on screen if it exists, as well as removing it from the current active board
    public void removeActiveBoard() {
        if (activeBoardPanel != null) {
            remove(activeBoardPanel);
            this.activeBoardPanel = null;
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the Board on screen, as well as setting it as the current active board
    public void addActiveBoard(BoardPanel newPanel) {
        add(newPanel, layout.CENTER, 1);
        this.activeBoardPanel = newPanel;
        activeBoardPanel.setVisible(true);
    }

    //EFFECTS:
    public void loadBoardIntoGame() throws IOException {
        this.game.loadBoard();

        BoardPanel newPanel = new BoardPanel(game.getBoardInProgress(), this);


        removeActiveBoard();
        addActiveBoard(newPanel);
        pack();
        revalidate();
    }


    public void saveBoardFromGame() throws FileNotFoundException {
        this.game.saveBoard();
    }

    public void enableSave() {
        bkgLayer.enableSaveButton();
    }

}
