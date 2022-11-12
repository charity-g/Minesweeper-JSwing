package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
    JLabel endGameImage;

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
    private void removeActiveBoard() {
        if (activeBoardPanel != null) {
            remove(activeBoardPanel);
            this.activeBoardPanel = null;
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the Board on screen, as well as setting it as the current active board
    private void addActiveBoard(BoardPanel newPanel) {
        add(newPanel, layout.CENTER, 1);
        this.activeBoardPanel = newPanel;
        activeBoardPanel.setVisible(true);
    }

    //EFFECTS:
    public void loadBoardIntoGame() throws IOException {
        this.game.loadBoard();
        BoardPanel newPanel = new BoardPanel(game.getBoardInProgress(), this);

        exchangeBoard(newPanel);
    }

    private void exchangeBoard(BoardPanel newPanel) {
        if (this.endGameImage != null) {
            remove(endGameImage);
        }
        removeActiveBoard();
        addActiveBoard(newPanel);
        pack();
        revalidate();
    }

    public void saveBoardFromGame() throws FileNotFoundException {
        this.game.saveBoard();
    }

    public void allowSaving() {
        bkgLayer.enableSaveButton();
    }

    public void setBeginnerBoard() {
        BoardPanel newPanel = new BoardPanel(game.setupNewBeginnerBoard(), this);
        exchangeBoard(newPanel);
    }

    public void setIntermediateBoard() {

        BoardPanel newPanel = new BoardPanel(game.setupNewIntermediateBoard(), this);
        exchangeBoard(newPanel);
    }

    public void setAdvancedBoard() {
        BoardPanel newPanel = new BoardPanel(game.setupNewAdvancedBoard(), this);
        exchangeBoard(newPanel);
    }

    //EFFECTS: loads winning image
    public void winGameImage() {
        bkgLayer.disableSaveButton();
        this.activeBoardPanel.setVisible(false);
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File("./data/images/minesweeper_win.png"));
            endGameImage = new JLabel(new ImageIcon(myPicture));
        } catch (IOException e) {
            endGameImage = new JLabel("YOU WON");
        }
        add(endGameImage, layout.SOUTH);
        revalidate();
    }

    //EFFECTS: loads losing image
    public void loseGameImage() {
        bkgLayer.disableSaveButton();
        this.activeBoardPanel.setVisible(false);
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File("./data/images/minesweeper_lose.png"));
            endGameImage = new JLabel(new ImageIcon(myPicture));
        } catch (IOException e) {
            endGameImage = new JLabel("YOU LOST");
        }
        add(endGameImage, layout.SOUTH);
        revalidate();
    }
}
