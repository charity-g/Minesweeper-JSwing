package ui;

import model.Board;
import model.Identity;
import model.Square;
import ui.buttons.SquareButton;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.BoardStatus.LOST;
import static ui.GameFrame.*;

public class BoardPanel extends JPanel implements ActionListener {
    private Board boardInProgress;
    private GameFrame gameFramework;

    public BoardPanel(Board boardInProgress, GameFrame gameFramework) {
        this.boardInProgress = boardInProgress;
        this.gameFramework = gameFramework;
        setPreferredSize(new Dimension(INTERFACE_WIDTH - MARGIN, INTERFACE_HEIGHT - MARGIN));

        this.setLayout(new GridLayout(boardInProgress.getBoardHeight(), boardInProgress.getBoardWidth()));
        addSquareButtons();
    }

    private void addSquareButtons() {
        for (Square square : boardInProgress.getAllSquares()) {
            JButton button = new SquareButton(square);
            this.add(button);
            //TODO
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (boardInProgress.getBoardStatus() == LOST) {
            endGame();
        } else if (isGameWon(boardInProgress)) {
            boardInProgress.setGameWon();
            endGame();
        } //TODO
    }

    //EFFECTS: produces true if all squares are shown on board except bomb
    private boolean isGameWon(Board boardInProgress) {
        for (Square square : this.boardInProgress.getAllSquares()) {
            if (square.getIdentity() != Identity.BOMB && square.isIdentityHidden()) {
                return false;
            }
        }
        return true;
    }

    //EFFECTS: displays end screen
    private void endGame() {
        JTextComponent text = new JTextField();
        text.setText("GAME ENDED: " + boardInProgress.getBoardStatus());
        this.add(text);
        gameFramework.revalidate();
    }

    public Board getBoardInProgress() {
        return this.boardInProgress;
    }

    public void setBoardInProgress(Board newBoard) {
        this.boardInProgress = newBoard;
    }
}
