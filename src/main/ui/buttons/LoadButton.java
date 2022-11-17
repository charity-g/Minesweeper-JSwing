package ui.buttons;

import ui.SystemPanel;

import javax.swing.*;

public class LoadButton extends SystemButton {

    public LoadButton(String text, SystemPanel clickListener) {
        super(text, clickListener);
        setActionCommand("load");
    }

}
