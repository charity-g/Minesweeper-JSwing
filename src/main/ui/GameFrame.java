package ui;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
Represents the Frame the gui(board and utility buttons) are to be presented in, and initializes the beginning of the
 program to create new game or to load an old one
 */
public class GameFrame extends JFrame {
    static final int INTERFACE_HEIGHT = 600;
    static final int INTERFACE_WIDTH = 1000;

    JPanel bkgLayer;
    BoardPanel activeBoardPanel;
    private JLayeredPane layers = new JLayeredPane();
    Game game;

    //EFFECTS: sets up the frame settings and initializes the utilities and beginning choices
    GameFrame() {
        game = new Game();
        BorderLayout layout = new BorderLayout();
        add(layers);
        layers.setLayout(layout);

        bkgLayer = new UtilityPanel(this);
        layers.add(bkgLayer, layout.NORTH);
        bkgLayer.setOpaque(true);

        long seed = 24;
        activeBoardPanel = new BoardPanel(new Board(3,3, 2, seed));
        layers.add(activeBoardPanel, layout.CENTER);
        activeBoardPanel.setOpaque(true);

        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public Game getGame() {
        return this.game;
    }

    public BoardPanel getActiveBoardPanel() {
        return this.activeBoardPanel;
    }

    public void setActiveBoardPanel(BoardPanel boardPanel) {
        this.activeBoardPanel = boardPanel;
        activeBoardPanel.setVisible(true);
//        this.setComponentZOrder(activeBoardPanel, 1);
    }

}
