package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static model.Identity.*;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {
    Utils testUtil;
    Board testBoardForUtils;
    final long SEED = 40;
    final int BOARD_LENGTH = 5;
    final int BOMB_NUMBER = 4;

    @BeforeEach
    public void setup(){
        testUtil = new Utils();
        testBoardForUtils = new Board(BOARD_LENGTH,BOARD_LENGTH,BOMB_NUMBER, SEED);
    }

    @Test
    public void assertSquarePositionsInOrderTrueTest(){
        ArrayList<Square> testList = new ArrayList<Square>();
        testList.add(new Square(BOMB, 0,0, 3, 3));
        testList.add(new Square(Identity.ONE, 1,0, 3, 3));
        testList.add(new Square(Identity.BLANK, 2,0, 3, 3));

        testList.add(new Square(BOMB, 0,1, 3, 3));
        testList.add(new Square(Identity.ONE, 1,1, 3, 3));
        testList.add(new Square(Identity.BLANK, 2,1, 3, 3));

        testList.add(new Square(BOMB, 0,2, 3, 3));
        testList.add(new Square(Identity.ONE, 1,2, 3, 3));
        testList.add(new Square(Identity.BLANK, 2,2, 3, 3));
        assertTrue(testUtil.areSquarePositionsInOrder(testList));
    }

    @Test
    public void assertSquarePositionsInOrderFalseTest(){
        ArrayList<Square> testList = new ArrayList<Square>();
        testList.add(new Square(BOMB, 0,0, 3, 3));
        testList.add(new Square(Identity.ONE, 1,1, 3, 3));
        testList.add(new Square(Identity.BLANK, 0,2, 3, 3));

        testList.add(new Square(BOMB, 1,0, 3, 3));
        testList.add(new Square(Identity.ONE, 0,1, 3, 3));
        testList.add(new Square(Identity.BLANK, 1,2, 3, 3));

        testList.add(new Square(BOMB, 2,0, 3, 3));
        testList.add(new Square(Identity.ONE, 3,1, 3, 3));
        testList.add(new Square(Identity.BLANK, 2,2, 3, 3));
        assertFalse(testUtil.areSquarePositionsInOrder(testList));
    }

    @Test
    //requires the seed of the board, and based on the seed, we know what it is.
    // testing that a square with identity 1+ bombs in it's neighbors
    public void checkBombsAroundSquareTestMultipleBombs(){
        Square position21 = testBoardForUtils.getSquare(21);
        assertTrue(
                testUtil.checkBombsAroundSquare(testBoardForUtils, position21));
        assertTrue(position21.getIdentity() == Identity.THREE);
        assertTrue(position21.getIntegerIdentity() == 3);
    }

    @Test
    //requires the seed of the board, and based on the seed, we know what it is.
    // testing that a square with identity 1 bombs in it's neighbors
    public void checkBombsAroundSquareTestOneBomb(){
        Square position10 = testBoardForUtils.getSquare(10);
        assertTrue(
                testUtil.checkBombsAroundSquare(testBoardForUtils, position10));
        assertTrue(position10.getIdentity() == Identity.ONE);
        assertTrue(position10.getIntegerIdentity() == 1);
    }

    @Test
    //requires the seed of the board, and based on the seed, we know what it is.
    // testing that a square with identity 0 bombs in it's neighbors
    public void checkBombsAroundSquareTestNoBombs(){
        Square position19 = testBoardForUtils.getSquare(19);
        assertTrue(
                testUtil.checkBombsAroundSquare(testBoardForUtils, position19));
        assertTrue(position19.getIdentity() == Identity.BLANK);
        assertTrue(position19.getIntegerIdentity() == 0);
    }

    @Test
    //requires the seed of the board, and based on the seed, we know what it is.
    // testing that a square that is a bomb
    public void checkBombsAroundSquareTestIsBomb(){
        Square position9 = testBoardForUtils.getSquare(9);
        assertTrue(
                testUtil.checkBombsAroundSquare(testBoardForUtils, position9));
        assertTrue(position9.getIdentity() == BOMB);
        assertTrue(position9.getIntegerIdentity() == -1);
    }

    @Test
    public void doesNotRepeatTestConsecutive() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        assertTrue(testUtil.listDoesNotRepeat(list));
        list.add(1);
        list.add(2);
        assertTrue(testUtil.listDoesNotRepeat(list));
        list.add(2);
        assertFalse(testUtil.listDoesNotRepeat(list));
    }

    @Test
    public void doesNotRepeatTestNonConsecutive() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(3);
        list.add(9);
        assertTrue(testUtil.listDoesNotRepeat(list));
        list.add(2);
        assertFalse(testUtil.listDoesNotRepeat(list));
    }

    @Test
    public void appearsOnlyOnceInListTestTrue() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(3);
        list.add(9);
        assertTrue(testUtil.appearsOnlyOnceInList(2, list));
        assertTrue(testUtil.appearsOnlyOnceInList(3, list));
        assertTrue(testUtil.appearsOnlyOnceInList(9, list));
    }

    @Test
    public void appearsOnlyOnceInListTestFalse() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(9);
        list.add(3);
        list.add(9);
        list.add(2);
        assertTrue(testUtil.appearsOnlyOnceInList(2, list));
        assertTrue(testUtil.appearsOnlyOnceInList(3, list));
        assertFalse(testUtil.appearsOnlyOnceInList(9, list));
    }

    @Test
    public void findSquareTestBomb(){
        Square bomb = testUtil.findSquare(BOMB, testBoardForUtils);
        assertTrue(bomb.getIntegerIdentity() == -1);
        assertTrue(bomb.getIdentity() == BOMB);
    }

    @Test
    public void findSquareTestBlank(){
        Square blank = testUtil.findSquare(BLANK, testBoardForUtils);
        assertTrue(blank.getIntegerIdentity() == 0);
        assertTrue(blank.getIdentity() == BLANK);
    }

    @Test
    public void findSquareTestTwo(){
        Square two = testUtil.findSquare(TWO, testBoardForUtils);
        assertTrue(two.getIntegerIdentity() == 2);
        assertTrue(two.getIdentity() == TWO);
    }

    @Test
    public void findSquareTestNull(){
        Square notFound = testUtil.findSquare(EIGHT, testBoardForUtils);
        assertNull(notFound);
    }
}
