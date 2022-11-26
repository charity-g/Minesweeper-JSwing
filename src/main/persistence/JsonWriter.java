package persistence;

import model.Board;
import model.Event;
import model.EventLog;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static java.awt.Event.TAB;

//writes Boards into json data at a specific file
public class JsonWriter {
    String filePath;
    PrintWriter printWriter;

    //EFFECTS: sets the path for the file to be written in
    public JsonWriter(String filePath) {
        this.filePath = filePath;
    }

    //EFFECTS: writes the parameter board into the filePath of this writer object
    public void write(Board board) throws FileNotFoundException {

        printWriter = new PrintWriter(new File(this.filePath));

        JSONObject jsonBoard = board.toJson();
        saveToFile(jsonBoard.toString(TAB));

        printWriter.close();

        EventLog.getInstance().logEvent(new Event("Board saved."));
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        printWriter.print(json);
    }
}
