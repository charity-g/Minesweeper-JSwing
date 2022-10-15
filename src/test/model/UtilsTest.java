package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {
    Utils testUtil;

    @BeforeEach
    public void setup(){
        testUtil = new Utils();
    }

    @Test
    //findSquare
    public void findSquareTest(){}

    @Test
    //findSquare
    public void assertSquarePositionsInOrder(){}

    @Test
    //findSquare
    public void checkBombsAroundSquare(){
        //TODO requires set seed
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
}
