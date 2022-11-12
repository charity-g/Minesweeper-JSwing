package ui.buttons;


import ui.GameFrame;
import ui.UtilityPanel;


public class NewGameButton extends UtilityButton {

    public NewGameButton(String text, GameFrame gameFramework, UtilityPanel listener) {
        super(text, gameFramework, listener);
        setActionCommand("new game");
    }
}