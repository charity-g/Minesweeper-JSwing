package ui.buttons;

import ui.SystemPanel;

/*
Represents a button that will alert SystemPanel that it has been clicked so board can be loaded
 */
public class LoadButton extends SystemButton {

    public LoadButton(String text, SystemPanel clickListener) {
        super(text, clickListener);
        setActionCommand("load");
    }

}
