package persistence;

import model.Board;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static java.awt.Event.TAB;

//TODO describe class
public class JsonWriter {
    String filePath;
    PrintWriter printWriter;

    //TODO to write
    public JsonWriter(String filePath) {
        this.filePath = filePath;
    }

    //EFFECTS: writes the parameter board into the filePath of this writer object
    public void write(Board board) throws FileNotFoundException {

        printWriter = new PrintWriter(new File(this.filePath));

        JSONObject jsonBoard = board.toJson();
        saveToFile(jsonBoard.toString(TAB));

        printWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        printWriter.print(json);
    }
}
