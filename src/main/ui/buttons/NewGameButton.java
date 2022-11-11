package ui.buttons;

import ui.Game;
import ui.GameFrame;

import java.awt.event.MouseEvent;

public class NewGameButton extends UtilityButton {

    public NewGameButton(String text, GameFrame gameFramework) {
        super(text, gameFramework);
    }

    //BoardPanel boardPanel = new BoardPanel();
    //this.add(boardPanel);
    //setComponentZOrder(boardPanel, 2);

    @Override
    public void handleMouseClicked(MouseEvent e) {

    }
}