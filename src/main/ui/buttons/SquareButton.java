package ui.buttons;

import model.Square;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
The gui representation of a square object, with JButton capabilities to respond to user interaction
 */
public class SquareButton extends JButton {
    Square square;

    public SquareButton(Square square) {
        super("" + square.getPosition());

        this.square = square;

        MouseListener ml = new SquareMouseListener();
        this.addMouseListener(ml);
    }

    //EFFECTS: react to mouse click
    private void handleMouseClick() {
        square.showSquare();
        //TODO
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
