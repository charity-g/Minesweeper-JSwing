package ui.buttons;

import ui.SystemPanel;

import javax.swing.*;

public class SaveButton extends SystemButton {
    public SaveButton(String text, SystemPanel clickListener) {
        super(text, clickListener);
        setActionCommand("save");
    }
}
