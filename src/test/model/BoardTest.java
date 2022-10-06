package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

//TODO 1 - create tests for all
public class BoardTest {
    Board testBoardTiny;
    Board testBoardStandard;

    @BeforeEach
    public void setup() {
        testBoardTiny = new Board(3, 1, 0, 0);
        testBoardStandard = new Board(8, 10, 6, 3);
    }

    @Test
    public void BoardConstructorTest() {
        assertEquals(testBoardTiny.getBoardLength(), 3);
        assertEquals(testBoardStandard.getBoardLength(), 8);

        assertFalse(testBoardTiny.isGameWonYet());
        assertFalse(testBoardStandard.isGameWonYet());
    }

    @Test
    public void doesNotRepeatTestConsecutive(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        assertTrue(testBoardStandard.doesNotRepeat(list));
        list.add(1);
        list.add(2);
        assertTrue(testBoardStandard.doesNotRepeat(list));
        list.add(2);
        assertFalse(testBoardStandard.doesNotRepeat(list));
    }

    @Test
    public void doesNotRepeatTestNonConsecutive(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(3);
        list.add(9);
        assertTrue(testBoardStandard.doesNotRepeat(list));
        list.add(2);
        assertFalse(testBoardStandard.doesNotRepeat(list));
    }

    @Test
    public void setBombsTestTiny() {
        Square bomb = testBoardTiny.getBombs().get(0);
        assertEquals(testBoardTiny.getBombs().size(), 1);
        assertFalse(bomb.getRowPosition() == 0);
        assertFalse(bomb.getColumnPosition() == 0);

    }

    @Test
    public void setBombsTestStandard() {
        ArrayList<Square> bombs = testBoardStandard.getBombs();
        assertEquals(bombs.size(), 10);
        ArrayList<Integer> positions = new ArrayList<Integer>();
        positions.add((3 * 8) + 6);
        for (Square bomb : bombs) {
            positions.add(bomb.getPosition());
        }
        assertTrue(testBoardStandard.doesNotRepeat(positions));
    }

    @Test
    public void initializeAllSquaresOnBoardTest() {
        //test to see correct number of squares have been made
        //test to see the positions of squares are correct
        //test to see the positions of squares match up with supposed identity
    }

    @Test
    public void getSquareTest() {

    }

    @Test
    public void unearthSquareTest() {

    }

}
