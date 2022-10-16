package model;

import java.util.ArrayList;

import static model.Identity.BOMB;

public class Utils {
    //EFFECTS: return first square on the board that has the identity, else null if no square exists with that identity
    public Square findSquare(Board board, Identity id) {
        ArrayList<Square> squares = board.getAllSquares();
        for (int i = 0; i < squares.size(); i++) {
            Square sq = squares.get(i);
            if (sq.getIdentity() == id) {
                return sq;
            }
        }
        return null;
    }

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
        if (square.getIdentity() == BOMB){
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
        } else {
            return false;
        }
    }

    //EFFECTS: returns true
    public boolean listDoesNotRepeat(ArrayList<Integer> list) {
        return false;
    }

}
