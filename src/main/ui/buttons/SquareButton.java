package ui.buttons;

import model.Square;
import ui.BoardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
The gui representation of a square object, with JButton capabilities to respond to user interaction
 */
public class SquareButton extends JButton {
    private static final int SQUARE_WIDTH = 40;

    Square square;
    BoardPanel boardListener;

    public SquareButton(Square square, BoardPanel boardListener) {
        super("" + square.getPosition());
        this.square = square;
        setPreferredSize(new Dimension(SQUARE_WIDTH, SQUARE_WIDTH));
        setSquareIdentityShown();

        MouseListener ml = new SquareMouseListener();
        this.addMouseListener(ml);

        addActionListener(boardListener);
        this.boardListener = boardListener;
    }

    private void setSquareIdentityShown() {
        if (square.isIdentityHidden()) {
            setText("?");
        } else {
            setText("" + square.getIntegerIdentity());
        }
    }

    public Square getSquare() {
        return square;
    }

    //EFFECTS: react to mouse click
    private void handleMouseClick() {
        boardListener.flipSquare(square.getPosition());
    }

    /*
    Provides methods for interacting and responding to mouse actions
     */
    private class SquareMouseListener extends MouseAdapter {

        @Override
        // EFFECTS: TODO !!
        public void mouseClicked(MouseEvent e) {
            handleMouseClick();
        }

    }
}
