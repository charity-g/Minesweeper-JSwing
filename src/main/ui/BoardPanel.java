package ui;

import model.Board;

import javax.swing.*;

public class BoardPanel extends JPanel {
    Board boardInProgress;

    public BoardPanel(Board boardInProgress) {
        this.boardInProgress = boardInProgress;
    }

}
