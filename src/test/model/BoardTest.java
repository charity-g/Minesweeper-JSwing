package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

//TODO 3 - ask lab TA about test redundancy?? my two cases are very small vs very big
public class BoardTest {
    Board testBoardTiny;
    Board testBoardStandard;
    Random random;
    long seed = 24;

    @BeforeEach
    public void setup() {
        testBoardTiny = new Board(3, 1, 0, 0);
        testBoardStandard = new Board(8, 10, 6, 3);
        random = new Random();
        random.setSeed(seed);
    }

    //CONSTRUCTOR TESTS
    @Test
    public void BoardConstructorTest() {
        assertEquals(testBoardTiny.getBoardLength(), 3);
        assertEquals(testBoardStandard.getBoardLength(), 8);

        assertEquals(testBoardTiny.getGameStatus(), GameStatus.IN_PROGRESS);
        assertEquals(testBoardStandard.getGameStatus(), GameStatus.IN_PROGRESS);
    }

    @Test
    public void doesNotRepeatTestConsecutive() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        assertTrue(testBoardStandard.listDoesNotRepeat(list));
        list.add(1);
        list.add(2);
        assertTrue(testBoardStandard.listDoesNotRepeat(list));
        list.add(2);
        assertFalse(testBoardStandard.listDoesNotRepeat(list));
    }

    @Test
    public void doesNotRepeatTestNonConsecutive() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(3);
        list.add(9);
        assertTrue(testBoardStandard.listDoesNotRepeat(list));
        list.add(2);
        assertFalse(testBoardStandard.listDoesNotRepeat(list));
    }

    @Test
    public void setBombsTestTiny() {
        Square bomb = testBoardTiny.getBombs().get(0);
        assertEquals(testBoardTiny.getBombs().size(), 1);
        assertFalse(bomb.getRowPosition() == 0);
        assertFalse(bomb.getColumnPosition() == 0);

        assertSquaresAreWithinBoard(testBoardTiny.getBombs(), 3);
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
        assertTrue(testBoardStandard.listDoesNotRepeat(positions));
        assertSquaresAreWithinBoard(bombs, 8);
    }

    @Test
    public void setBombsTestSeed(){
        //TODO
    }

    @Test
    public void checkSquarePositionsInOrderTestTiny() {
        ArrayList<Square> testSquareList = setupTestSquareList();
        assertSquarePositionsInOrder(testSquareList, 3);
    }

    @Test
    public void initializeAllSquaresOnBoardTestTiny() {
        ArrayList<Square> allSquaresOnBoard = testBoardTiny.getAllSquares();
        //test to see correct number of squares have been made
        assertEquals(allSquaresOnBoard.size(), 9);
        //test to see the positions of squares are correct
        assertSquarePositionsInOrder(allSquaresOnBoard, 3);
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
        assertSquarePositionsInOrder(allSquaresOnBoard, 8);
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
        int pos = normalSquare.getPosition();
        assertTrue(normalSquare != null);
        assertTrue(normalSquare.isIdentityHidden());

        assertTrue(testBoardTiny.unearthSquare(pos));
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
    static void assertSquarePositionsInOrder(ArrayList<Square> allSquaresOnBoard, int boardLength) {
        for(int i = 0; i < allSquaresOnBoard.size(); i++){
            Square square = allSquaresOnBoard.get(i);
            assertTrue(i == square.getPosition());
            assertTrue(i % boardLength == square.getRowPosition());
            assertTrue(i / boardLength == square.getColumnPosition());
        }
    }

    //EFFECTS: depending on identity of the square, checks the positions in the neighbors around it to see if the
    // number of bombs around this square is correct
    static boolean checkBombsAroundSquare(ArrayList<Square> allSquaresOnBoard, int chosenRow, int chosenColumn) {
        return false;
    }

    //EFFECTS: returns true if each square in list has position on board and not off board, else false
    static void assertSquaresAreWithinBoard(ArrayList<Square> squares, int boardLength) {
        for(Square square : squares){
            assertSquareIsWithinBoard(square, boardLength);
        }
    }

    //EFFECTS: return true if given square's rowPosition and columnPosition is on board, and position is on board
    static void assertSquareIsWithinBoard(Square sq, int boardLength) {
        assertTrue(sq.getPosition() < (boardLength * boardLength));
        assertTrue(sq.getRowPosition() < boardLength);
        assertTrue(sq.getColumnPosition() < boardLength);
    }

    //EFFECTS: return first square on the board that has the identity, else null if no square exists with that identity
    static Square findSquare(Board board, Identity id) {
        ArrayList<Square> squares = board.getAllSquares();
        for(int i = 0; i < squares.size(); i++){
            Square sq = squares.get(i);
            if (sq.getIdentity() == id) {
                return sq;
            }
        }
        return null;
    }
}
