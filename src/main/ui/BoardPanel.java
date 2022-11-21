package ui;

import model.Board;
import model.GameStatus;
import model.Square;
import ui.buttons.SquareButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static model.GameStatus.LOST;
import static model.GameStatus.WON;

/*
Represents the panel that presents the boardInProgress as interactable buttons
 */

public class BoardPanel extends JPanel {
    private Board boardRepresentation;
    private final GameFrame gameFrame;
    private List<SquareButton> squareButtons;
    private LayoutManager gridLayout;

    //EFFECTS: creates a board panel with no board
    public BoardPanel(GameFrame gameFrame) {
        squareButtons = new ArrayList<>();
        this.gameFrame = gameFrame;

        this.setBounds(GameFrame.MARGIN, GameFrame.SYSTEM_PANEL_HEIGHT,
                GameFrame.INTERFACE_WIDTH - (2 * GameFrame.MARGIN),
                GameFrame.INTERFACE_HEIGHT - GameFrame.SYSTEM_PANEL_HEIGHT);
        this.gridLayout = new GridLayout(1, 1);
        this.setBackground(Color.white);
        this.setOpaque(true);
        this.setLayout(gridLayout);
    }

    //MODIFIES: this (the panel)
    //EFFECTS: represents the given board onscreen as user-interactable buttons, and removes any existing boards
    public void setBoard(Board board) {
        removeAllSquareButtons();

        this.boardRepresentation = board;
        this.gridLayout = new GridLayout(boardRepresentation.getBoardHeight(), boardRepresentation.getBoardWidth());
        this.setLayout(gridLayout);

        for (Square square : this.boardRepresentation.getAllSquares()) {
            SquareButton button = new SquareButton(square, this);
            this.squareButtons.add(button);
            this.add(button, gridLayout);
        }
        this.repaint();
        this.revalidate();
    }

    //MODIFIES: this
    //EFFECTS: removes all the square buttons both from the arraylist AND from the panel
    private void removeAllSquareButtons() {
        for (SquareButton squareButton : squareButtons) {
            this.remove(squareButton);
        }

        this.squareButtons.clear();
    }

    //MODIFIES: boardRepresentation, this, Square sq : squareButtons
    //EFFECTS: updates the board object and board panel with the respective click
    public void updateBoardWithClick(int position) {
        gameFrame.unearthSquare(position);

        for (SquareButton squareButton : this.squareButtons) {
            squareButton.showButtonIfNotHidden();
        }

        boardRepresentation.checkAndSetIfGameWon();
        GameStatus gameStatus = boardRepresentation.getGameStatus();

        if (gameStatus == LOST) {
            gameFrame.showLoseGameImage();
        } else if (gameStatus == WON) {
            gameFrame.showWinGameImage();
        }

    }

}
