package model;

import java.util.ArrayList;

public class TestUtils {
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
}
