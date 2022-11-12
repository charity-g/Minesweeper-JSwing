package ui.buttons;

import ui.BoardPanel;
import ui.Game;
import ui.GameFrame;
import ui.UtilityPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LoadButton extends UtilityButton {


    public LoadButton(String text, GameFrame gameFramework, UtilityPanel listener) {
        super(text, gameFramework, listener);
        errorText = new JTextField("The file to load cannot be loaded.");
        add(errorText);
        errorText.setVisible(false);
        setActionCommand("load");
    }

}
