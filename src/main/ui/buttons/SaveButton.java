package ui.buttons;

import ui.SystemPanel;


/*
Represents a button that will alert SystemPanel that it has been clicked so board can be saved
 */
public class SaveButton extends SystemButton {
    public SaveButton(String text, SystemPanel clickListener) {
        super(text, clickListener);
        setActionCommand("save");
    }
}
