package ui.buttons;

import ui.Game;
import ui.GameFrame;
import ui.UtilityPanel;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UtilityButton extends JButton {
    protected GameFrame gameFramework;

    public UtilityButton(String text, GameFrame gameFramework, UtilityPanel listener) {
        super(text);
        this.gameFramework = gameFramework;

        addActionListener(listener);
    }


    //EFFECTS: handles mouse click for this button
    //public abstract void handleMouseClicked(MouseEvent e);

    /*
    Provides methods for interacting and responding to mouse actions
     */
    private class MouseClickListener extends MouseAdapter {

        @Override
        // EFFECTS: TODO tbd!!
        public void mouseClicked(MouseEvent e) {
            //handleMouseClicked(e);
        }

    }

}
