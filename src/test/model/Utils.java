package model;

import model.boards.Board;

import java.util.ArrayList;

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

    //EFFECTS: go through each position and see if the positions follow one by one
    public boolean assertSquarePositionsInOrder(ArrayList<Square> listOfSquare){
        return false;
    } //TODO

    //EFFECTS: checks the amount of bombs around the square's position and verifies if it matches up with the
    //         square's identity
    public boolean checkBombsAroundSquare(Board board, int squarePosition){
        return false;
    }

    //EFFECTS: returns true
    public boolean listDoesNotRepeat(ArrayList<Integer> list){return false;}

}
