package ui.buttons;

import ui.GameFrame;
import ui.UtilityPanel;


public class LoadButton extends UtilityButton {

    public LoadButton(String text, GameFrame gameFramework, UtilityPanel listener) {
        super(text, gameFramework, listener);
        setActionCommand("load");
    }

}
