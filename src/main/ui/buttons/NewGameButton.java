package ui.buttons;

import ui.Game;
import ui.GameFrame;
import ui.UtilityPanel;

import java.awt.event.MouseEvent;

public class NewGameButton extends UtilityButton {

    public NewGameButton(String text, GameFrame gameFramework, UtilityPanel listener) {
        super(text, gameFramework, listener);
    }

    //BoardPanel boardPanel = new BoardPanel();
    //this.add(boardPanel);
    //setComponentZOrder(boardPanel, 2);

    public void handleMouseClicked(MouseEvent e) {

    }
}