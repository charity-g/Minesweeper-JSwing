package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
    Position startingPosition;
    Position firstRowPos;
    Position firstColumnPos;
    Position testPos;

    @BeforeEach
    public void setup(){
        startingPosition = new Position(1,1);
        firstRowPos = new Position(1, 20);
        firstColumnPos = new Position(20, 1);
        testPos = new Position(3,2);
    }

    @Test
    public void PositionConstructorTest(){
        Assertions.assertEquals(startingPosition.getColumnPosition(), 1);
        Assertions.assertEquals(startingPosition.getRowPosition(), 1);

        Assertions.assertEquals(firstRowPos.getColumnPosition(), 20);
        Assertions.assertEquals(firstRowPos.getRowPosition(), 1);

        Assertions.assertEquals(firstColumnPos.getColumnPosition(), 1);
        Assertions.assertEquals(firstColumnPos.getRowPosition(), 20);

        Assertions.assertEquals(testPos.getColumnPosition(), 2);
        Assertions.assertEquals(testPos.getRowPosition(), 3);
    }

}
