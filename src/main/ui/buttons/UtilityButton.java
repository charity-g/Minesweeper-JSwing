package ui.buttons;

import ui.Game;
import ui.GameFrame;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class UtilityButton extends JButton {
    protected GameFrame gameFramework;
    protected JTextComponent errorText;

    public UtilityButton(String text, GameFrame gameFramework) {
        super(text);
        this.gameFramework = gameFramework;

        addMouseListener(new MouseClickListener());
    }


    //EFFECTS: handles mouse click for this button
    public abstract void handleMouseClicked(MouseEvent e);

    /*
    Provides methods for interacting and responding to mouse actions
     */
    private class MouseClickListener extends MouseAdapter {

        @Override
        // EFFECTS: TODO !!
        public void mouseClicked(MouseEvent e) {
            handleMouseClicked(e);
        }

    }

}
