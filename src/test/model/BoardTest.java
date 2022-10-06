package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

//TODO 1 - create tests for all
//TODO 3 - ask lab TA about test redundancy
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
    public void doesNotRepeatTestConsecutive() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        assertTrue(testBoardStandard.doesNotRepeat(list));
        list.add(1);
        list.add(2);
        assertTrue(testBoardStandard.doesNotRepeat(list));
        list.add(2);
        assertFalse(testBoardStandard.doesNotRepeat(list));
    }

    @Test
    public void doesNotRepeatTestNonConsecutive() {
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
    public void checkSquarePositionsInOrderTestTiny() {
        ArrayList<Square> testSquareList = setupTestSquareList();
        assertTrue(checkSquarePositionsInOrder(testSquareList, 3));
    }

    @Test
    public void initializeAllSquaresOnBoardTestTiny() {
        ArrayList<Square> allSquaresOnBoard = testBoardTiny.getAllSquares();
        //test to see correct number of squares have been made
        assertEquals(allSquaresOnBoard.size(), 9);
        //test to see the positions of squares are correct
        assertTrue(checkSquarePositionsInOrder(allSquaresOnBoard, 3));
        //test to see the positions of squares match up with supposed identity
        for(Square square: allSquaresOnBoard){
            assertTrue(checkBombsAroundSquare(allSquaresOnBoard, square.getRowPosition(), square.getColumnPosition()));
        }
    }

    @Test
    public void initializeAllSquaresOnBoardTestStandard() {
        ArrayList<Square> allSquaresOnBoard = testBoardStandard.getAllSquares();
        assertEquals(allSquaresOnBoard.size(), 64);
        //test to see the positions of squares are correct
        assertTrue(checkSquarePositionsInOrder(allSquaresOnBoard, 8));
        //test to see the positions of squares match up with supposed identity
        for(Square square: allSquaresOnBoard){
            assertTrue(checkBombsAroundSquare(allSquaresOnBoard, square.getRowPosition(), square.getColumnPosition()));
        }
    }

    @Test
    public void getSquareTest() {
        Identity testSquareID = testBoardStandard.getAllSquares().get(0).getIdentity();

    }

    @Test
    public void unearthSquareTest() {

    }

    @Test
    public void isGameWonYetTestNo(){
        assertFalse(testBoardTiny.isGameWonYet());
        assertFalse(testBoardStandard.isGameWonYet());
    }


    @Test
    public void isGameWonYetTestYes(){
    }

    //STATIC HELPERS

    static ArrayList<Square> setupTestSquareList() {
        ArrayList<Square> list = new ArrayList<Square>();
        list.add(new Square(Identity.BLANK, 0, 0, 3));
        list.add(new Square(Identity.BLANK, 1, 0, 3));
        list.add(new Square(Identity.BLANK, 2, 0, 3));

        list.add(new Square(Identity.BLANK, 0, 1, 3));
        list.add(new Square(Identity.BLANK, 1, 1, 3));
        list.add(new Square(Identity.BLANK, 2, 1, 3));

        list.add(new Square(Identity.BLANK, 0, 2, 3));
        list.add(new Square(Identity.BLANK, 1, 2, 3));
        list.add(new Square(Identity.BLANK, 2, 2, 3));

        return list;
    }

    //EFFECTS: returns true if the positions of the squares in order of the arrayList correspond well to the
    //         boardLength and have the correct rowPosition and columnPosition
    static boolean checkSquarePositionsInOrder(ArrayList<Square> allSquaresOnBoard, int boardLength) {
        return false;
    }

    //EFFECTS: depending on identity of the square, checks the positions in the matrix around it to see if the number
    //         of bombs around this square is correct
    static boolean checkBombsAroundSquare(ArrayList<Square> allSquaresOnBoard, int chosenRow, int chosenColumn){
        return false;
    }
}
