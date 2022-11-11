package ui.buttons;

import ui.BoardPanel;
import ui.Game;
import ui.GameFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LoadButton extends UtilityButton {


    public LoadButton(String text, GameFrame gameFramework) {
        super(text, gameFramework);
        errorText = new JTextField("The file to load cannot be loaded.");
        add(errorText);
        errorText.setVisible(false);
        setActionCommand("load");
    }

    @Override
    public void handleMouseClicked(MouseEvent e) {
        try {
            gameFramework.getGame().loadBoard();
            gameFramework.setActiveBoardPanel(new BoardPanel(gameFramework.getGame().getBoardInProgress()));
            gameFramework.revalidate();
            gameFramework.getActiveBoardPanel().repaint();
        } catch (IOException ex) {
            errorText.setVisible(true);
        }
    }
}
