package ui.buttons;

import model.Board;
import model.Square;
import ui.BoardPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static model.Identity.BOMB;

/*
The gui representation of a square object, with JButton capabilities to respond to user interaction
 */
public class SquareButton extends JButton {
    Square square;
    BoardPanel boardListener;

    public SquareButton(Square square, BoardPanel boardListener) {
        super("" + square.getPosition());
        setText("?");
        this.square = square;

        MouseListener ml = new SquareMouseListener();
        this.addMouseListener(ml);

        addActionListener(boardListener);
        this.boardListener = boardListener;
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
