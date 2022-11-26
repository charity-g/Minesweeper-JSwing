package ui;

import ui.buttons.CloseOperationListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
Represents a frame that holds all the panels on screen and communicates between the Game object and what the user sees
 */
public class GameFrame extends JFrame {
    public static final int INTERFACE_WIDTH = 1200;
    public static final int INTERFACE_HEIGHT = 800;
    public static final int MARGIN = 30;
    public static final int SYSTEM_PANEL_HEIGHT = 100;

    private Game game;

    JPanel bkgPanel;
    JLayeredPane layeredPane;
    BoardPanel boardPanel;
    SystemPanel systemPanel;
    private JLabel endGameImage;

    //EFFECTS: creates new JFrame object with necessary panels in order
    public GameFrame() {
        game = new Game();
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(INTERFACE_WIDTH, INTERFACE_HEIGHT));


        bkgPanel = new JPanel();
        bkgPanel.setBackground(Color.lightGray);
        bkgPanel.setBounds(0, 0, INTERFACE_WIDTH, INTERFACE_HEIGHT);
        layeredPane.add(bkgPanel, 1);

        systemPanel = new SystemPanel(this);
        layeredPane.add(systemPanel, 0);

        boardPanel = new BoardPanel(this);
        layeredPane.add(boardPanel, 0);

        addWindowListener(new CloseOperationListener(this));

        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(layeredPane);
        setVisible(true);
        pack();
        setLocationRelativeTo(null); //puts window in center of screen
    }

    //EFFECTS: saves the current board on screen into the json file
    public void saveBoardIntoJson() throws FileNotFoundException {
        game.saveBoard();
    }

    //MODIFIES: this, game, boardPanel
    //EFFECTS: loads the board and displays on screen
    public void loadBoardIntoGame() throws IOException {
        game.loadBoard();
        exchangeBoard();

    }

    //MODIFIES: this, game, boardPanel
    //EFFECTS: creates new beginner board and displays it on screen
    public void makeNewBeginnerBoard() {
        game.setupNewBeginnerBoard();
        exchangeBoard();
    }

    //MODIFIES: this, game, boardPanel
    //EFFECTS: creates new intermediate board and displays it on screen
    public void makeNewIntermediateBoard() {
        game.setupNewIntermediateBoard();
        exchangeBoard();
    }

    //MODIFIES: this, game, boardPanel
    //EFFECTS: creates new advanced board and displays it on screen
    public void makeNewAdvancedBoard() {
        game.setupNewAdvancedBoard();
        exchangeBoard();
    }

    //MODIFIES: this, game, systemPanel(possibly)
    //EFFECTS: coordinates game object to unearth the squares needed, and catches error to show onscreen
    public void unearthSquare(int position) {
        try {
            game.unearthPlayerChosenSquare(position);
        } catch (CannotFlipFlaggedSquareException e) {
            systemPanel.showErrorMessage("OOP! Square flagged, cannot flip.");
        }
    }

    //MODIFIES: this, boardPanel
    //EFFECTS: exchanges the board on boardPanel and removes last game's win/lose image
    private void exchangeBoard() {
        boardPanel.setBoard(game.getBoardInProgress());
        if (endGameImage != null) {
            layeredPane.remove(endGameImage);
            endGameImage = null;
        }
        layeredPane.setLayer(bkgPanel,0);
        repaint();
        revalidate();
    }


    //MODIFIES: this, boardPanel
    //EFFECTS: loads losing image
    public void showLoseGameImage() {
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File("./data/images/minesweeper_lose.png"));
            endGameImage = new JLabel(new ImageIcon(myPicture));
        } catch (IOException e) {
            endGameImage = new JLabel("YOU LOST");
        }

        showEndGameImage();
    }

    //MODIFIES: this, boardPanel
    //EFFECTS: loads winning image
    public void showWinGameImage() {
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File("./data/images/minesweeper_win.png"));
            endGameImage = new JLabel(new ImageIcon(myPicture));
        } catch (IOException e) {
            endGameImage = new JLabel("YOU WON");
        }

        showEndGameImage();
    }

    //MODIFIES: this, boardPanel
    //EFFECTS: reveals endGameImage image
    private void showEndGameImage() {
        systemPanel.disableSaveButton();
        endGameImage.setBounds(GameFrame.MARGIN, GameFrame.SYSTEM_PANEL_HEIGHT,
                GameFrame.INTERFACE_WIDTH - (2 * GameFrame.MARGIN),
                GameFrame.INTERFACE_HEIGHT - GameFrame.SYSTEM_PANEL_HEIGHT);
        layeredPane.add(endGameImage,1);
        layeredPane.setLayer(boardPanel,0);
        this.repaint();
        this.revalidate();
    }

}
