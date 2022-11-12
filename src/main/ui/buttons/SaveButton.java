package ui.buttons;

import ui.GameFrame;
import ui.UtilityPanel;


public class SaveButton extends UtilityButton {

    public SaveButton(String text, GameFrame gameFramework, UtilityPanel listener) {
        super(text, gameFramework, listener);
        setActionCommand("save");
    }

}
