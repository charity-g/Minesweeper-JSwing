package model;

import static model.Identity.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BoardTest {
    Board testBoardSeed;
    private final int BOARD_WIDTH = 3;
    private final int BOARD_HEIGHT = 4;
    private final int BOARD_SIZE = BOARD_HEIGHT * BOARD_WIDTH;
    Utils util = new Utils();
    protected long seed = 24;

    @BeforeEach
    public void setup() {
        testBoardSeed = new Board(BOARD_WIDTH, BOARD_HEIGHT, 1, seed);
    }

    //CONSTRUCTOR TESTS
    @Test
    //to be overridden
    public void constructorTest() {
        assertEquals(testBoardSeed.getBoardWidth(), this.BOARD_WIDTH);
        assertEquals(testBoardSeed.getBoardHeight(), this.BOARD_HEIGHT);
        assertEquals(testBoardSeed.getBoardSize(), this.BOARD_SIZE);
        assertEquals(testBoardSeed.getBombNumber(), 1);

        assertEquals(testBoardSeed.getGameStatus(), GameStatus.IN_PROGRESS);
    }

    @Test
    //to be overridden + override should add that the bombs do not repeat
    public void constructorSetBombsTest() {
        ArrayList<Integer> bombsPos = testBoardSeed.getListOfBombPos();
        assertEquals(bombsPos.size(), 1);
        ArrayList<Integer> filteredBombs = testBoardSeed.filterOutOfBounds(bombsPos);
        assertEquals(filteredBombs.size(), 1);
        assertEquals(bombsPos.get(0), filteredBombs.get(0));
    }

    @Test
    //to be overridden depending on seed
    public void setBombsTestSeed() {
        //TODO requires set seed to confirm positions
    }

    @Test
    public void constructorInitializeAllSquaresTest() {
        ArrayList<Square> allSquaresOnBoard = testBoardSeed.getAllSquares();
        //test to see correct number of squares have been made
        assertEquals(allSquaresOnBoard.size(), BOARD_SIZE);
        //test to see the positions of squares are correct
        util.assertSquarePositionsInOrder(allSquaresOnBoard);
        //test to see the positions of squares match up with supposed identity
        for (Square square : allSquaresOnBoard) {
            assertTrue(util.checkBombsAroundSquare(testBoardSeed,
                    square.getPosition()));
        }
    }

    //NON-CONSTRUCTOR BASED TESTS

    @Test
    public void unearthSquareTestNumber() {
        Square normalSquare = util.findSquare(testBoardSeed, ONE);
        if (normalSquare != null) {
            int pos = normalSquare.getPosition();
            assertTrue(normalSquare.isIdentityHidden());

            assertTrue(testBoardSeed.unearthSquare(pos));
            assertFalse(normalSquare.isIdentityHidden());

            assertFalse(testBoardSeed.unearthSquare(pos));
            assertFalse(normalSquare.isIdentityHidden());
        }
    }

    @Test
    //probably test multiple versions of this
    public void unearthSquareTestBlank() {
        Square blankSquare = util.findSquare(testBoardSeed, BLANK);
        assertTrue(blankSquare != null);
        //TODO  LONG LOL
        // req set seed
        assertTrue(false);
    }

    @Test
    public void unearthSquareTestBomb() {}

    @Test
    //filterOutOfBounds(ArrayList<Integer> allPositions)
    public void filterOutOfBoundsTestNone() {
        ArrayList<Integer> positions = new ArrayList<Integer>();
        //adding positions
        for (int i = 0; i < (BOARD_WIDTH * BOARD_HEIGHT); i++) {
            positions.add(i);
        }
        ArrayList<Integer> filteredPositions = testBoardSeed.filterOutOfBounds(positions);
        assertEquals(positions, filteredPositions);

    }

    @Test
    //filterOutOfBounds(ArrayList<Integer> allPositions)
    public void filterOutOfBoundsTestOne() {
        ArrayList<Integer> positions = new ArrayList<Integer>();
        //adding positions
        for (int i = 0; i <= (BOARD_WIDTH * BOARD_HEIGHT); i++) {
            positions.add(i);
        }
        ArrayList<Integer> filteredPositions = testBoardSeed.filterOutOfBounds(positions);
        assertFalse(positions == filteredPositions);
        assertEquals(filteredPositions.size(), (BOARD_SIZE) - 1);
        assertFalse(filteredPositions.contains(BOARD_SIZE));

    }

    @Test
    //filterOutOfBounds(ArrayList<Integer> allPositions)
    public void filterOutOfBoundsTestMultiple() {
        ArrayList<Integer> positions = new ArrayList<Integer>();
        //adding positions
        for (int i = -5; i <= (BOARD_WIDTH * BOARD_HEIGHT) + 1; i += 2) {
            positions.add(i);
        }
        ArrayList<Integer> filteredPositions = testBoardSeed.filterOutOfBounds(positions);
        assertFalse(positions == filteredPositions);
        assertFalse(filteredPositions.contains(-5));
        assertFalse(filteredPositions.contains(-3));
        assertFalse(filteredPositions.contains(-1));
        assertFalse(filteredPositions.contains(BOARD_WIDTH * BOARD_HEIGHT));
        assertFalse(filteredPositions.contains((BOARD_WIDTH * BOARD_HEIGHT) + 1));
    }

    @Test
    //Testing getNeighbors(int position) four corners
    public void getNeighborsTopLeftCornerTest() {
        ArrayList<Integer> neighborPos = testBoardSeed.getNeighborPositions(0);
        assertEquals(neighborPos.size(), 3);
        assertTrue(neighborPos.contains(1));
        assertTrue(neighborPos.contains(BOARD_WIDTH));
        assertTrue(neighborPos.contains(BOARD_WIDTH + 1));
        assertFalse(neighborPos.contains(BOARD_WIDTH - 1));
    }

    @Test
    //Testing getNeighbors(int position) four corners
    public void getNeighborsTopRightCornerTest() {
        ArrayList<Integer> neighborPos = testBoardSeed.getNeighborPositions(BOARD_WIDTH - 1);
        assertEquals(neighborPos.size(), 3);
        assertTrue(neighborPos.contains(BOARD_WIDTH - 2));
        assertFalse(neighborPos.contains(BOARD_WIDTH));
        assertTrue(neighborPos.contains((2 * BOARD_WIDTH) - 1));
    }

    @Test
    //Testing getNeighbors(int position) four corners
    public void getNeighborsBotLeftCornerTest() {
        ArrayList<Integer> neighborPos = testBoardSeed.getNeighborPositions(BOARD_WIDTH * (BOARD_HEIGHT - 1));
        assertEquals(neighborPos.size(), 3);
        assertTrue(neighborPos.contains(BOARD_WIDTH * (BOARD_HEIGHT - 2)));
        assertTrue(neighborPos.contains((BOARD_WIDTH * (BOARD_HEIGHT - 2)) + 1));
        assertTrue(neighborPos.contains((BOARD_WIDTH * (BOARD_HEIGHT - 1)) + 1));
    }

    @Test
    //Testing getNeighbors(int position) four corners
    public void getNeighborsBotRightCornerTest() {
        ArrayList<Integer> neighborPos = testBoardSeed.getNeighborPositions((BOARD_WIDTH * BOARD_HEIGHT) - 1);
        assertEquals(neighborPos.size(), 3);
        assertTrue(neighborPos.contains((BOARD_WIDTH * BOARD_HEIGHT) - 2));
        assertTrue(neighborPos.contains((BOARD_WIDTH * (BOARD_HEIGHT - 1)) - 1));
        assertTrue(neighborPos.contains((BOARD_WIDTH * (BOARD_HEIGHT - 1)) - 2));
    }

    @Test
    //Testing getNeighbors(int position) left side
    //        which is any position with the formula position = n * BOARD_WIDTH for any integer n
    public void getNeighborsLeftSideTest() {
        ArrayList<Integer> neighborPos = testBoardSeed.getNeighborPositions(BOARD_WIDTH);
        assertEquals(neighborPos.size(), 5);
        assertTrue(neighborPos.contains(0));
        assertTrue(neighborPos.contains(1));
        assertTrue(neighborPos.contains(BOARD_WIDTH + 1));
        assertTrue(neighborPos.contains(BOARD_WIDTH * 2));
        assertTrue(neighborPos.contains((BOARD_WIDTH * 2) + 1));
    }

    @Test
    //Testing getNeighbors(int position) right side
    //        which is any position with the formula pos = (n * BOARD_WIDTH) - 1 for any integer n and pos >= 0
    public void getNeighborsRightSideTest() {
        ArrayList<Integer> neighborPos = testBoardSeed.getNeighborPositions((BOARD_WIDTH * 3) - 1);
        assertEquals(neighborPos.size(), 5);
        assertTrue(neighborPos.contains((BOARD_WIDTH * 2) - 1));
        assertTrue(neighborPos.contains((BOARD_WIDTH * 2) - 2));
        assertTrue(neighborPos.contains((BOARD_WIDTH * 3) - 1));
        assertTrue(neighborPos.contains((BOARD_WIDTH * 4) - 1));
        assertTrue(neighborPos.contains((BOARD_WIDTH * 4) - 2));
    }

    @Test
    //Testing getNeighbors(int position) top side
    //  which is any position from 0 - (BOARD_WIDTH - 2)
    public void getNeighborsTopSideTest() {
        ArrayList<Integer> neighborPos = testBoardSeed.getNeighborPositions(BOARD_WIDTH - 2);
        assertEquals(neighborPos.size(), 5);
        assertTrue(neighborPos.contains(BOARD_WIDTH - 3));
        assertTrue(neighborPos.contains(BOARD_WIDTH + 1));
        assertTrue(neighborPos.contains((BOARD_WIDTH * 2) - 3));
        assertTrue(neighborPos.contains((BOARD_WIDTH * 2) - 2));
        assertTrue(neighborPos.contains((BOARD_WIDTH * 2) - 1));
    }

    @Test
    //Testing getNeighbors(int position) bot side
    //  which is any position from (BOARD_WIDTH * (BOARD_HEIGHT - 1)) to (BOARD_SIZE - 2)
    public void getNeighborsBotSideTest() {
        ArrayList<Integer> neighborPos = testBoardSeed.getNeighborPositions(BOARD_SIZE - 2);
        assertEquals(neighborPos.size(), 5);
        assertTrue(neighborPos.contains(BOARD_SIZE - 3));
        assertTrue(neighborPos.contains(BOARD_SIZE - 1));
        assertTrue(neighborPos.contains((BOARD_HEIGHT * (BOARD_WIDTH - 1)) - 3));
        assertTrue(neighborPos.contains((BOARD_HEIGHT * (BOARD_WIDTH - 1)) - 2));
        assertTrue(neighborPos.contains((BOARD_HEIGHT * (BOARD_WIDTH - 1)) - 1));
    }

    @Test
    //Testing getNeighbors(int position) middle
    public void getNeighborsMiddleTest() {
        ArrayList<Integer> neighborPos = testBoardSeed.getNeighborPositions(BOARD_WIDTH + 1);
        assertEquals(neighborPos.size(), 8);
        assertTrue(neighborPos.contains(0));
        assertTrue(neighborPos.contains(1));
        assertTrue(neighborPos.contains(2));
        assertTrue(neighborPos.contains(BOARD_WIDTH));
        assertTrue(neighborPos.contains(BOARD_WIDTH + 1));
        assertTrue(neighborPos.contains(BOARD_WIDTH * 2));
        assertTrue(neighborPos.contains((BOARD_WIDTH * 2) + 1));
        assertTrue(neighborPos.contains((BOARD_WIDTH * 2) + 2));
    }

    @Test
    //changeSquareFlag(int position) for
    public void changeSquareFlagTest() {
        assertFalse(testBoardSeed.getSquare(5).isFlagged());
        //flag
        testBoardSeed.changeSquareFlag(5);
        assertTrue(testBoardSeed.getSquare(5).isFlagged());
        //un-flag
        testBoardSeed.changeSquareFlag(BOARD_WIDTH);
        assertFalse(testBoardSeed.getSquare(5).isFlagged());
    }

    @Test
    //doesNotContainBombInPosAlready(int position) bomb in position already
    //TODO requires set seed to verify where bombs are
    public void doesNotContainBombInPosAlreadyAlreadyThereTest() {

    }

    @Test
    //doesNotContainBombInPosAlready(int position) bomb not in position already
    //TODO requires set seed to verify where bombs are
    public void doesNotContainBombInPosAlreadyNotThereTest() {

    }

    @Test
    //Testing getSquare(int position)
    public void testGetSquarePos() {
        //TODO requires set seed to verify identity
    }

    @Test
    //Testing getSquare(int col, int row) // one for BOMB, BLANK, NUMBER
    public void testGetSquareRowCol() {
        //TODO requires set seed to verify identity
    }


    @Test
    //Testing getListOfBombPos()
    public void testGetListOfBombPos() {
        ArrayList<Integer> listOfBombPos = testBoardSeed.getListOfBombPos();

        for (int position : listOfBombPos) {
            assertEquals(testBoardSeed.getSquare(position).getIdentity(), BOMB);
        }
    }


}


