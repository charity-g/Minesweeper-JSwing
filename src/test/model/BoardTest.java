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
        assertTrue(testBoardSeed.getSquare(7).getIdentity() == BOMB);
    }

    @Test
    public void constructorInitializeAllSquaresTest() {
        ArrayList<Square> allSquaresOnBoard = testBoardSeed.getAllSquares();
        //test to see correct number of squares have been made
        assertEquals(allSquaresOnBoard.size(), BOARD_SIZE);
        //test to see the positions of squares are correct
        assertTrue(util.areSquarePositionsInOrder(allSquaresOnBoard));
        //test to see the positions of squares match up with supposed identity
        for (Square square : allSquaresOnBoard) {
            assertTrue(util.checkBombsAroundSquare(testBoardSeed,
                    square));
        }
        assertTrue(testBoardSeed.getSquare(3).getBoardWidth() == BOARD_WIDTH);
        assertTrue(testBoardSeed.getSquare(8).getBoardWidth() == BOARD_WIDTH);

        assertTrue(testBoardSeed.getSquare(2).getBoardHeight() == BOARD_HEIGHT);
        assertTrue(testBoardSeed.getSquare(BOARD_SIZE-1).getBoardHeight() == BOARD_HEIGHT);
    }

    //NON-CONSTRUCTOR BASED TESTS

    @Test
    public void unearthSquareTestNumber() {
        int pos = 4;
        Square normalSquare = testBoardSeed.getSquare(4);
        assertTrue(normalSquare.isIdentityHidden());

        assertTrue(testBoardSeed.unearthSquare(pos));
        assertFalse(normalSquare.isIdentityHidden());

        assertFalse(testBoardSeed.unearthSquare(pos));
        assertFalse(normalSquare.isIdentityHidden());

    }

    @Test
    //testing click top left blank
    public void unearthSquareTestBlankTopLeft() {
        testBoardSeed.unearthSquare(0);
        ArrayList<Square> allSquaresOnBoard = testBoardSeed.getAllSquares();
        assertFalse(allSquaresOnBoard.get(0).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(1).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(2).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(3).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(4).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(5).isIdentityHidden());
        assertTrue(allSquaresOnBoard.get(6).isIdentityHidden());
        assertTrue(allSquaresOnBoard.get(7).isIdentityHidden());
        assertTrue(allSquaresOnBoard.get(8).isIdentityHidden());
    }

    @Test
    //testing click top mid blank
    public void unearthSquareTestBlankTop() {
        testBoardSeed.unearthSquare(1);
        ArrayList<Square> allSquaresOnBoard = testBoardSeed.getAllSquares();
        assertFalse(allSquaresOnBoard.get(0).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(1).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(2).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(3).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(4).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(5).isIdentityHidden());
        assertTrue(allSquaresOnBoard.get(6).isIdentityHidden());
        assertTrue(allSquaresOnBoard.get(7).isIdentityHidden());
        assertTrue(allSquaresOnBoard.get(8).isIdentityHidden());
    }

    @Test
    //testing click top right blank
    public void unearthSquareTestBlankTopRight() {
        testBoardSeed.unearthSquare(2);
        ArrayList<Square> allSquaresOnBoard = testBoardSeed.getAllSquares();
        assertFalse(allSquaresOnBoard.get(0).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(1).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(2).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(3).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(4).isIdentityHidden());
        assertFalse(allSquaresOnBoard.get(5).isIdentityHidden());
        assertTrue(allSquaresOnBoard.get(6).isIdentityHidden());
        assertTrue(allSquaresOnBoard.get(7).isIdentityHidden());
        assertTrue(allSquaresOnBoard.get(8).isIdentityHidden());
    }

    @Test
    public void unearthSquareTestBomb() {
        testBoardSeed.unearthSquare(7);
        assertFalse(testBoardSeed.getSquare(7).isIdentityHidden());
        assertTrue(testBoardSeed.getGameStatus() == GameStatus.LOST);
    }

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
        ArrayList<Integer> positions = new ArrayList<>();
        //adding positions
        for (int i = 0; i <= BOARD_SIZE; i++) {
            positions.add(i);
        }
        ArrayList<Integer> filteredPositions = testBoardSeed.filterOutOfBounds(positions);
        assertFalse(positions == filteredPositions);
        assertEquals(filteredPositions.size(), BOARD_SIZE);
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
        assertTrue(neighborPos.contains((BOARD_WIDTH * 3) - 2));
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
        assertTrue(neighborPos.contains((BOARD_WIDTH * (BOARD_HEIGHT - 1)) - 3));
        assertTrue(neighborPos.contains((BOARD_WIDTH * (BOARD_HEIGHT - 1)) - 2));
        assertTrue(neighborPos.contains((BOARD_WIDTH * (BOARD_HEIGHT - 1)) - 1));
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
        assertTrue(neighborPos.contains(BOARD_WIDTH + 2));
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
        //flag something else
        testBoardSeed.changeSquareFlag(BOARD_WIDTH);
        assertTrue(testBoardSeed.getSquare(5).isFlagged());
        assertTrue(testBoardSeed.getSquare(BOARD_WIDTH).isFlagged());
        //un-flag
        testBoardSeed.changeSquareFlag(5);
        assertFalse(testBoardSeed.getSquare(5).isFlagged());
    }

    @Test
    //doesNotContainBombInPosAlready(int position) bomb in position already
    public void doesNotContainBombInPosAlreadyAlreadyThereTest() {
        assertFalse(testBoardSeed.givenPositionIsNotAlreadyABomb(7 % BOARD_WIDTH, 7 / BOARD_WIDTH));
    }

    @Test
    //doesNotContainBombInPosAlready(int position) bomb not in position already
    public void doesNotContainBombInPosAlreadyNotThereTest() {
        assertTrue(testBoardSeed.givenPositionIsNotAlreadyABomb(6 % BOARD_WIDTH, 6 / BOARD_WIDTH));
        assertTrue(testBoardSeed.givenPositionIsNotAlreadyABomb(8 % BOARD_WIDTH, 8 / BOARD_WIDTH));
        assertTrue(testBoardSeed.givenPositionIsNotAlreadyABomb(4 % BOARD_WIDTH, 4 / BOARD_WIDTH));
        assertTrue(testBoardSeed.givenPositionIsNotAlreadyABomb(10 % BOARD_WIDTH, 10 / BOARD_WIDTH));
    }

    @Test
    //Testing getSquare(int position)
    public void testGetSquarePos() {
        assertEquals(testBoardSeed.getSquare(7).getIdentity(), BOMB);
        assertEquals(testBoardSeed.getSquare(5).getIdentity(), ONE);
        assertEquals(testBoardSeed.getSquare(2).getIdentity(), BLANK);
    }

    @Test
    //Testing getSquare(int col, int row) // one for BOMB, BLANK, NUMBER
    public void testGetSquareRowCol() {
        assertEquals(testBoardSeed.getSquare(2, 1).getIdentity(), BOMB);
        assertEquals(testBoardSeed.getSquare(3,1).getIdentity(), ONE);
        assertEquals(testBoardSeed.getSquare(0,2).getIdentity(), BLANK);
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


