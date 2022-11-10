package ui.buttons;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class UtilityButton extends JButton {

    public UtilityButton(String text) {
        super(text);
    }


    //EFFECTS: handles mouse click for this button
    abstract void handleMouseClicked(MouseEvent e);

    /*
    Provides methods for interacting and responding to mouse actions
     */
    private class MouseListener extends MouseAdapter {

        // EFFECTS:Forward mouse clicked event to the active tool
        public void mouseClicked(MouseEvent e) {
            handleMouseClicked(e);
        }
    }
}
