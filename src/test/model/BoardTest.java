package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Random;

//TODO 1 - create tests for all
//TODO 3 - ask lab TA about test redundancy?? my two cases are very small vs very big
public class BoardTest {
    Board testBoardTiny;
    Board testBoardStandard;
    Random r = new Random();

    setSeed(123);

    @BeforeEach
    public void setup() {
        testBoardTiny = new Board(3, 1, 0, 0);
        testBoardStandard = new Board(8, 10, 6, 3);
    }

    //CONSTRUCTOR TESTS
    @Test
    public void BoardConstructorTest() {
        assertEquals(testBoardTiny.getBoardLength(), 3);
        assertEquals(testBoardStandard.getBoardLength(), 8);

        assertEquals((testBoardTiny.getGameStatus(), GameStatus.IN_PROGRESS);
        assertEquals((testBoardStandard.getGameStatus(), GameStatus.IN_PROGRESS);
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

    //TODO setBombs random thing
    //https://www.geeksforgeeks.org/random-setseed-method-in-java-with-examples/

    @Test
    public void setBombsTestTiny() {
        Square bomb = testBoardTiny.getBombs().get(0);
        assertEquals(testBoardTiny.getBombs().size(), 1);
        assertFalse(bomb.getRowPosition() == 0);
        assertFalse(bomb.getColumnPosition() == 0);

        assertTrue(squaresPosWithinBoard(testBoardTiny.getBombs(), 8));
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
        assertTrue(squaresPosWithinBoard(bombs, 8));
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
        for (Square square : allSquaresOnBoard) {
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
        for (Square square : allSquaresOnBoard) {
            assertTrue(checkBombsAroundSquare(allSquaresOnBoard, square.getRowPosition(), square.getColumnPosition()));
        }
    }

    //NON-CONSTRUCTOR METHODS TEST
    @Test
    public void getSquareTestFirst() {
        Identity testSquareID = testBoardStandard.getAllSquares().get(0).getIdentity();
        Square gotSquare = testBoardStandard.getSquare(0, 0);
        assertEquals(testSquareID, gotSquare.getIdentity());
        assertEquals(0, gotSquare.getPosition());
    }

    @Test
    public void getSquareTest() {
        Identity testSquareID = testBoardStandard.getAllSquares().get(9).getIdentity();
        Square gotSquare = testBoardStandard.getSquare(9 % 8, 9 / 8);
        assertEquals(testSquareID, gotSquare.getIdentity());
        assertEquals(0, gotSquare.getPosition());
    }

    @Test
    public void getSquareTestLast() {
        Identity testSquareID = testBoardStandard.getAllSquares().get(63).getIdentity();
        Square gotSquare = testBoardStandard.getSquare(7, 7);
        assertEquals(testSquareID, gotSquare.getIdentity());
        assertEquals(63, gotSquare.getPosition());
    }

    @Test
    public void unearthSquareTestNumber() {
        Square normalSquare = findSquare(testBoardTiny, Identity.ONE);
        int pos = normalSquare.getPosition());
        assertTrue(normalSquare != null);
        assertTrue(normalSquare.isIdentityHidden());

        assertTrue(testBoardTiny.unearthSquare(pos);
        assertFalse(normalSquare.isIdentityHidden());

        assertFalse(testBoardTiny.unearthSquare(pos));
        assertFalse(normalSquare.isIdentityHidden());
    }

    @Test
    public void unearthSquareTestBlank() {
        Square blankSquare = findSquare(testBoardStandard, Identity.BLANK);
        assertTrue(blankSquare != null);
        //TODO  LONG LOL
    }

    @Test
    public void TestGameLost() {
        Square bombSquare = findSquare(testBoardTiny, Identity.BOMB);
        int position = bombSquare.getPosition();
        assertTrue(bombSquare != null);
        assertTrue(testBoardTiny.unearthSquare(position));
        assertEquals(testBoardTiny.getGameStatus(), GameStatus.LOST);
    }

    @Test
    public void isGameWonTestYes() {
        for (Square square : testBoardTiny.getAllSquares()) {
            if (square.getIdentity() != Identity.BOMB) {
                testBoardTiny.unearthSquare(square.getPosition());
            }
        }
        assertEquals(testBoardTiny.getGameStatus(), GameStatus.WON);
    }

    @Test
    public void changeSquareFlagTestUnflagged() {
        Square square = testBoardTiny.getAllSquares().get(0);
        assertFalse(square.isFlagged());
        testBoardTiny.changeSquareFlag(0);
        assertTrue(square.isFlagged());
    }

    @Test
    public void changeSquareFlagTestAlreadyFlagged() {
        Square square = testBoardTiny.getAllSquares().get(2);
        testBoardTiny.changeSquareFlag(2);
        assertTrue(square.isFlagged());
        testBoardTiny.changeSquareFlag(2);
        assertFalse(square.isFlagged());
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

    //EFFECTS: depending on identity of the square, checks the positions in the neighbors around it to see if the
    // number of bombs around this square is correct
    static boolean checkBombsAroundSquare(ArrayList<Square> allSquaresOnBoard, int chosenRow, int chosenColumn) {
        return false;
    }

    //EFFECTS: returns true if each square in list has position on board and not off board, else false
    static boolean squaresPosWithinBoard(ArrayList<Square> squares, int boardLength) {
        return false;
        //use squarePosWithinBoard to iterate over
    }

    //EFFECTS: return true if given square's rowPosition and columnPosition si on board, and position is on board
    static boolean squarePosWithinBoard(Square sq, int boardLength) {
        return false;
    }

    //EFFECTS: return first square on the board that has the identity, else null if no square exists with that identity
    static Square findSquare(Board board, Identity id) {
        return null;
    }
}
