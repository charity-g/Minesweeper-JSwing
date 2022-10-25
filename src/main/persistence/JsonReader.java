package persistence;

import model.Board;
import model.Square;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.stream.Stream;

//reads a file with Board json data
public class JsonReader {
    final String filePath;

    //EFFECTS: sets the path for file to be read
    public JsonReader(String filePath) {
        this.filePath = filePath;
    }

    //REQUIRES: filePath to exist and lead to a file that can be read into a Board object
    //EFFECTS: reads the file into a board object
    //         if filePath does not exist, throws FileNotFoundException
    //         if filePath does not lead to a Board, throws FileContentWrongException
    public Board read() throws IOException, org.json.JSONException {
        String jsonData = readFile(this.filePath);
        JSONObject jsonBoardObject = new JSONObject(jsonData);
        return parseBoard(jsonBoardObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        } catch (InvalidPathException e) {
            throw new FileNotFoundException();
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Board parseBoard(JSONObject jsonBoardObject) throws org.json.JSONException {

        long seed = jsonBoardObject.getLong("seed");
        int boardWidth = jsonBoardObject.getInt("boardWidth");
        int boardHeight = jsonBoardObject.getInt("boardHeight");
        int bombNumber = jsonBoardObject.getInt("bombNumber");
        Board board = flagAndShow(new Board(boardWidth, boardHeight, bombNumber, seed),
                jsonBoardObject.getJSONArray("allSquares"));

        return board;
    }

    // EFFECTS: flags and shows the required squares on the board depending on the states of the JSONArray,
    private Board flagAndShow(Board board, JSONArray allSquares) {
        for (Object squareObject : allSquares) {
            JSONObject jsonSquare = (JSONObject) squareObject;

            if (!jsonSquare.getBoolean("isHidden")) {
                int position = jsonSquare.getInt("position");
                Square square = board.getSquare(position);
                square.showSquare();

            } else if (jsonSquare.getBoolean("isFlagged")) {
                int position = jsonSquare.getInt("position");
                Square square = board.getSquare(position);
                square.flag();
            }
        }
        return board;
    }
}
