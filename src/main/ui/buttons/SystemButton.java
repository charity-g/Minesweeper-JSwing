package ui.buttons;

import ui.SystemPanel;

import javax.swing.*;

public class SystemButton extends JButton {

    //EFFECTS: calls the JButton initializer
    public SystemButton(String text, SystemPanel clickListener) {
        super(text);

        addActionListener(clickListener);
    }

}
