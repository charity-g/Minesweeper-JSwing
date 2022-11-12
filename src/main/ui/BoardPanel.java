package ui;

import model.Board;
import model.Identity;
import model.Square;
import ui.buttons.SquareButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static model.BoardStatus.LOST;
import static ui.GameFrame.*;

public class BoardPanel extends JPanel implements ActionListener {
    private Board boardInProgress;
    private GameFrame gameFramework;
    private ArrayList<SquareButton> allSquareButtons;

    public BoardPanel(Board boardInProgress, GameFrame gameFramework) {
        this.boardInProgress = boardInProgress;
        this.gameFramework = gameFramework;
        this.allSquareButtons = new ArrayList<>();
        gameFramework.allowSaving();

        setPreferredSize(new Dimension(INTERFACE_WIDTH - MARGIN, INTERFACE_HEIGHT - MARGIN));

        this.setLayout(new GridLayout(boardInProgress.getBoardHeight(), boardInProgress.getBoardWidth()));
        addSquareButtons();
    }

    private void addSquareButtons() {
        for (Square square : this.boardInProgress.getAllSquares()) {
            SquareButton button = new SquareButton(square, this);
            this.add(button);
            allSquareButtons.add(button);
            //TODO
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    } //TODO remove??

    @Override
    public void actionPerformed(ActionEvent e) {
        for (SquareButton squareButton : allSquareButtons) {
            Square squareObj = squareButton.getSquare();
            if (!squareObj.isIdentityHidden()) {
                squareButton.setText("" + squareObj.getIntegerIdentity());

            }
        }

        if (boardInProgress.getBoardStatus() == LOST) {
            gameFramework.loseGameImage();
        } else if (isGameWon()) {
            boardInProgress.setGameWon();
            gameFramework.winGameImage();
        }

        this.revalidate();
        gameFramework.revalidate();

    }

    //EFFECTS: produces true if all squares are shown on board except bomb
    private boolean isGameWon() {
        for (Square square : this.boardInProgress.getAllSquares()) {
            if (square.getIdentity() != Identity.BOMB && square.isIdentityHidden()) {
                return false;
            }
        }
        return true;
    }

    public Board getBoardInProgress() {
        return this.boardInProgress;
    }

    public void setBoardInProgress(Board newBoard) {
        this.boardInProgress = newBoard;
    }


    //EFFECTS:
    public void flipSquare(int position) {
        gameFramework.game.unearthPlayerChosenSquare(position);
    }
}
