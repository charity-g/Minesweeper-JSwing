package ui;

import model.Board;
import model.Square;
import ui.buttons.SquareButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel {
    Board boardInProgress;

    public BoardPanel(Board boardInProgress) {
        this.boardInProgress = boardInProgress;

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

}
