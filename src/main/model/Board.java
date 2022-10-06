package model;

import java.util.ArrayList;

public class Board {

    private int boardLength;
    private ArrayList<Square> bombs;
    private ArrayList<Square> allSquaresOnBoard; // TODO: unpack this guy

    //TODO add comments for all methods

    //Places bombs and hides all squares
    //Create all the squares all at once + the identities of the squares
    //purpose: create a board of length x and thus a grid of x by x
    //  final field Grid length
    //  final positions of bombs
    public Board() {
    }

    //Unearth square
    //If square is already visible, return false and do nothing
    // else return true and one of the following
    // if bomb = game over
    // if number, show number
    // if blank, unearth multiple
    public boolean unearthSquare() {
        return false;
    }

    public boolean hasGameBeenWon() {
        return false;
    }

    public void handleBomb(){}

    //find a square with position of row and column
    public Square findSquare(int row, int column) {
        return null;
    }


    //makes the bombs and sets their position
    //should only be called once
    private void setBombs() {
    }
}
