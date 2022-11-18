package ui.buttons;

import model.Square;
import ui.BoardPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
Represents an interactive button on a board that is a display of an square object
 */
public class SquareButton extends JButton {
    private final BoardPanel boardPanel;
    private Square square;

    public SquareButton(Square square, BoardPanel boardPanel) {
        super();
        this.square = square;
        showButtonIfNotHidden();

        MouseListener listener = new SquareMouseListener();
        this.addMouseListener(listener);

        this.boardPanel = boardPanel;
    }

    //MODIFIES: this
    //EFFECTS: sets the text depending on whether it should show its identity or not
    public void showButtonIfNotHidden() {
        if (square.isIdentityHidden()) {
            setText("?");
        } else {
            setText("" + square.getIntegerIdentity());
        }
    }

    //PRIVATE METHODS ===========

    //EFFECTS: react to mouse click
    private void handleMouseClick() {
        boardPanel.updateBoardWithClick(square.getPosition());
    }

    /*
    Provides methods for interacting and responding to mouse actions
     */
    private class SquareMouseListener extends MouseAdapter {

        @Override
        // EFFECTS: Handles mouse click and alerts button
        public void mouseClicked(MouseEvent e) {
            handleMouseClick();
        }

    }

}
