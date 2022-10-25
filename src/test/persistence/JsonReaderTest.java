package persistence;

import model.Board;
import model.GameStatus;
import model.Identity;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    public void readFileNotFound() {
        JsonReader reader = new JsonReader("./data/fileDoesNotExist.json");
        try {
            reader.read();
            fail("Should have thrown exception and thus not gotten here.");
        } catch (IOException e) {
            //pass
        } catch (org.json.JSONException e) {
            fail("Wrong exception thrown.");
        }
    }

    @Test
    public void readNotBoard() {
        JsonReader reader = new JsonReader("./src/main/model/Board.java");
        try {
            reader.read();
            fail("Should have thrown exception and thus not gotten here.");
        } catch (IOException e) {
            fail("Wrong exception thrown??");
        } catch (org.json.JSONException e) {
            //pass
        }
    }

    @Test
    //Read a file with Square json data not Board json data
    public void readWrongFile() {
        JsonReader reader = new JsonReader("./data/testJsonReaderSquare.json");
        try {
            reader.read();
            fail("Should have thrown exception and thus not gotten here.");
        } catch (IOException e) {
            fail("Wrong exception thrown??");
        } catch (org.json.JSONException e) {
            //pass
        }
    }

    @Test
    public void readSmallBoardInProgressTest() {
        JsonReader reader = new JsonReader("./data/testJsonReaderSmallBoardInProgress.json");
        try {
            Board board = reader.read();

            assertEquals(board.getGameStatus(), GameStatus.IN_PROGRESS);
            assertEquals(board.getBoardWidth(), 3);
            assertEquals(board.getBoardHeight(), 3);
            assertEquals(board.getListOfBombPos().size(), 2);
            assertTrue(board.getListOfBombPos().contains(3));
            assertTrue(board.getListOfBombPos().contains(7));
            assertEquals(board.getSquare(0).getIdentity(), Identity.ONE);

        } catch (Exception e) {
            fail("Should not have thrown exception.");
        }
    }
}
