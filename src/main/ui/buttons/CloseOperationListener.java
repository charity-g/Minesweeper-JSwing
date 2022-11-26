package ui.buttons;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

public class CloseOperationListener extends WindowAdapter {
    private JFrame frame;

    public CloseOperationListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        print(EventLog.getInstance().getEvents());
    }

    private void print(Collection<Event> events) {
        for (Event e : events) {
            System.out.println(e.getDate().toString() + ": " + e.getDescription());
        }
    }

}
