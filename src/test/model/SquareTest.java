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
        testBlankSquare = new Square(Identity.BLANK, 0, 0, 8);
        testSquare = new Square(Identity.BOMB, 2, 4, 10);
        testLastSquare = new Square(Identity.EIGHT, 7, 7, 8);
    }

    @Test
    public void SquareConstructorTest(){
        assertTrue(testBlankSquare.isIdentityHidden());
        assertTrue(testSquare.isIdentityHidden());
        assertTrue(testLastSquare.isIdentityHidden());

        assertEquals(testBlankSquare.getIdentity(), Identity.BLANK);
        assertEquals(testSquare.getIdentity(), Identity.BOMB);
        assertEquals(testLastSquare.getIdentity(), Identity.EIGHT);

        assertEquals(testBlankSquare.getRowPosition(), 0);
        assertEquals(testBlankSquare.getColumnPosition(), 0);
        assertEquals(testSquare.getRowPosition(), 2);
        assertEquals(testSquare.getColumnPosition(), 4);
        assertEquals(testLastSquare.getRowPosition(), 7);
        assertEquals(testLastSquare.getColumnPosition(), 7);

        assertEquals(testBlankSquare.getPosition(), 0);
        assertEquals(testSquare.getPosition(), 42);
        assertEquals(testLastSquare.getPosition(), 63);
    }
}