package model;

import java.util.ArrayList;

import static model.Identity.BOMB;

// utils is a class with helpful methods so that the test classes can call these methods to verify their classes
// does not represent anything, simply cleans up the code and allows things to be packaged separately
public class Utils {

    //REQUIRES: assumes the given list of
    //EFFECTS: return true if the positions follow one by one in the correct indexes; else false
    public boolean areSquarePositionsInOrder(ArrayList<Square> listOfSquare) {
        for (int index = 0; index < listOfSquare.size(); index++) {
            if (index != listOfSquare.get(index).getPosition()) {
                return false;
            }
        }
        return true;
    }

    //EFFECTS: checks the amount of bombs around the square's position and verifies if it matches up with the
    //         square's identity
    public boolean checkBombsAroundSquare(Board board, Square square) {
        if (square.getIdentity() == BOMB) {
            return true;
        }
        ArrayList<Integer> neighborPositions = board.getNeighborPositions(square.getPosition());
        int bombsInNeighborsCount = 0;
        for (int bombPosition : board.getListOfBombPos()) {
            if (neighborPositions.contains(bombPosition)) {
                bombsInNeighborsCount++;
            }
        }
        if (bombsInNeighborsCount == square.getIntegerIdentity()) {
            return true;
        } //else {
        //TODO throw new Exception();
        // }
        return false;
    }

    //EFFECTS: returns true if the list cannot be considered a set
    public boolean listDoesNotRepeat(ArrayList<Integer> list) {
        for (int element : list) {
            if (!appearsOnlyOnceInList(element, list)) {
                return false;
            }
        }
        return true;
    }

    //REQUIRES: assumes the given element is in the list at least once
    //EFFECTS: returns true if the element appears in the list exactly once, else false
    public boolean appearsOnlyOnceInList(int element, ArrayList<Integer> list) {
        int timesAppeared = 0;
        for (int i : list) {
            if (i == element) {
                timesAppeared++;
            }
        }
        if (timesAppeared == 1) {
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: returns a square that has the given identity, if it cannot find one, returns null
    public Square findSquare(Identity id, Board board) {
        for (Square sq : board.getAllSquares()) {
            if (sq.getIdentity() == id) {
                return sq;
            }
        }
        return null;
    }
}
