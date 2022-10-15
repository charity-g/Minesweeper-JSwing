package model;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquareTest {
    Square testBlankSquare;
    Square testSquare;
    Square testLastSquare;

    @BeforeEach
    public void setup() {
        testBlankSquare = new Square(Identity.BLANK, 0, 0, 8, 8);
        testSquare = new Square(Identity.BOMB, 2, 4, 10, 10);
        testLastSquare = new Square(Identity.EIGHT, 7, 7, 8, 8);
    }

    @Test
    public void SquareConstructorTest(){
        assertTrue(testBlankSquare.isIdentityHidden());
        assertTrue(testSquare.isIdentityHidden());
        assertTrue(testLastSquare.isIdentityHidden());

        assertFalse(testBlankSquare.isFlagged());
        assertFalse(testSquare.isFlagged());
        assertFalse(testLastSquare.isFlagged());

        assertEquals(testBlankSquare.getIdentity(), Identity.BLANK);
        assertEquals(testSquare.getIdentity(), Identity.BOMB);
        assertEquals(testLastSquare.getIdentity(), Identity.EIGHT);

        assertEquals(testBlankSquare.getColumn(), 0);
        assertEquals(testBlankSquare.getRow(), 0);
        assertEquals(testSquare.getColumn(), 2);
        assertEquals(testSquare.getRow(), 4);
        assertEquals(testLastSquare.getColumn(), 7);
        assertEquals(testLastSquare.getRow(), 7);

        assertEquals(testBlankSquare.getPosition(), 0);
        assertEquals(testSquare.getPosition(), 42);
        assertEquals(testLastSquare.getPosition(), 63);
    }

    @Test
    public void showHiddenSquareTest(){
        assertTrue(testSquare.isIdentityHidden());
        assertTrue(testSquare.showSquare());
        assertFalse(testSquare.isIdentityHidden());
    }

    @Test
    public void showShownSquareTest(){
        assertTrue(testSquare.showSquare());
        assertFalse(testSquare.isIdentityHidden());
        assertFalse(testSquare.showSquare());
        assertFalse(testSquare.isIdentityHidden());
    }

    @Test
    public void flagSquareTest(){
        assertFalse(testSquare.isFlagged());
        testSquare.changeFlag();
        assertTrue(testSquare.isFlagged());
    }

    @Test
    public void unflagSquareTest(){
        testSquare.changeFlag();
        assertTrue(testSquare.isFlagged());
        testSquare.changeFlag();
        assertFalse(testSquare.isFlagged());
    }
}