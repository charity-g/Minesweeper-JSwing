package model;

import java.util.ArrayList;

//TODO 2
public class Board {

    private final int boardLength;
    private boolean gameWonYet;
    private final ArrayList<Square> bombs;
    private final ArrayList<Square> allSquaresOnBoard;

    //CONSTRUCTOR
    //REQUIRES: bombNumber < (boardLength^2 / 2), boardLength > 3
    //EFFECTS: Places all the bombs in positions that aren't the user's choice, sets the identities,
    //         sets gameWonYet to false
    public Board(int boardLength, int bombNumber, int chosenRow, int chosenColumn) {
    }

    //Unearth square
    //If square is already visible, return false and do nothing
    // else return true and one of the following
    // if bomb = game over
    // if number, show number
    // if blank, unearth multiple
    //REQUIRES: chosen position to exist on board
    //MODIFIES: this, and Square at chosen position
    //EFFECTS: If square is already visible, return false and do nothing,
    //         else return true after one of the following
    //     if identity of square = bomb, set game over
    //     if identity of square = number, show number
    //     if identity of square = blank, unearth multiple
    public boolean unearthSquare(int chosenRow, int chosenColumn) {
        return false;
    }

    //HELPERS ======================================

    //REQUIRES: should only be called by Constructor
    //MODIFIES: this
    // EFFECTS makes number amount of bombs and sets their position to random positions that aren't their own and aren't
    //         the chosen position the user clicked on
    public void setBombs(int number, int chosenRow, int chosenColumn) {
    }

    //REQUIRES: should only be called by Constructor
    //MODIFIES: this
    //EFFECTS: creates squares and identities of the whole board based on the bombs around them
    public void initializeAllSquaresOnBoard() {
    }

    // GETTERS
    //EFFECTS: return boardLength
    public int getBoardLength() {
        return boardLength;
    }

    //EFFECTS: return true if game has been won, false if not
    public boolean isGameWonYet() {
        return gameWonYet;
    }

    //EFFECTS: returns the square with position of row and column, or null if the square is off the board
    public Square getSquare(int row, int column) {
        return null;
    }
}
