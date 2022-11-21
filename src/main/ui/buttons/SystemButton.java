package ui.buttons;

import ui.SystemPanel;

import javax.swing.*;


/*
Represents a button that will alert SystemPanel that it has been clicked so appropriate method can be called
 */
public class SystemButton extends JButton {

    //EFFECTS: calls the JButton initializer
    public SystemButton(String text, SystemPanel clickListener) {
        super(text);

        addActionListener(clickListener);
    }

}
