package persistence;

import model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


//When testing, please ensure you are going into the files to delete the former data
public class JsonWriterTest {
    long seed = 24;
    JsonWriter writer;
    JsonReader reader;
    Board testBoardWrite;
    Board readBoard;

    @BeforeEach
    public void setup(){
        testBoardWrite = new Board(8, 8, 10, seed);
    }

    @Test
    public void writeTestFileDoesNotExist() {
        Board beginnerBoard = new Board(8, 8, 10, seed);
        writer = new JsonWriter("./\0testJsonWriterBeginnerBoardNoMoves.json");
        try {
            writer.write(beginnerBoard);
            fail("Should have thrown exception");
        } catch (FileNotFoundException e) {
            //pass!
        }
    }

    @Test
    public void writeNoMovesBoard() {
        writer = new JsonWriter("./data/testJsonWriterBoardNoMoves.json");
        try {
            writer.write(testBoardWrite);
        } catch (FileNotFoundException e) {
            fail("Should not have thrown exception");
        }

        reader = new JsonReader("./data/testJsonWriterBoardNoMoves.json");
        try {
            readBoard = reader.read();
        } catch (Exception e){
            fail("Should not have thrown exception");
        }

        assertTrue(readBoard.getListOfBombPos().equals(testBoardWrite.getListOfBombPos()));
        assertTrue(readBoard.getBoardSize() == testBoardWrite.getBoardSize());
    }

    @Test
    public void writeMidGameBoard(){
        //setup
        testBoardWrite.unearthSquare(0);

        //call method
        writer = new JsonWriter("./data/testJsonWriterBoardMidGame.json");
        try {
            writer.write(testBoardWrite);
        } catch (FileNotFoundException e) {
            fail("Should not have thrown exception");
        }

        //check results
        reader = new JsonReader("./data/testJsonWriterBoardMidGame.json");
        try {
            readBoard = reader.read();
        } catch (Exception e){
            fail("Should not have thrown exception");
        }

        assertTrue(readBoard.getListOfBombPos().equals(testBoardWrite.getListOfBombPos()));
        assertTrue(readBoard.getBoardSize() == testBoardWrite.getBoardSize());
        int[] positionsUnearthed = {0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 16, 17, 18, 19, 20};

        for (int position : positionsUnearthed) {
            assertFalse(readBoard.getSquare(position).isIdentityHidden());
            assertFalse(testBoardWrite.getSquare(position).isIdentityHidden());
        }
    }
}
