package ui.buttons;

import ui.SystemPanel;


/*
Represents a button that will alert SystemPanel that it has been clicked so a new board can be created
 */
public class NewGameButton extends SystemButton {
    public NewGameButton(String text, SystemPanel clickListener) {
        super(text, clickListener);
    }
}
