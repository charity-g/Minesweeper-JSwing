package model;

import static model.Identity.*;

public class Square {

    static final int SQUARE_LENGTH = 2;

    private final int gridWidth;
    private final int gridHeight;
    // column = positionInGrid % gridWidth; column < gridHeight
    // row = positionInGrid / gridWidth
    private final int column;
    private final int row;
    private final int positionInGrid;

    private final Identity identity;
    private boolean identityHidden;
    private boolean isFlagged;

    //CONSTRUCTOR
    //REQUIRES: col row >= 0; maximum col/row needs to be < gridLength
    //EFFECTS : hides Identity, sets identity and position to one given, and sets position
    public Square(Identity identity, int col, int row, int gridWidth, int gridHeight) {
        this.identityHidden = true;
        this.isFlagged = false;
        this.identity = identity;
        this.column = col;
        this.row = row;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.positionInGrid = col + (gridWidth * row);
    }

    //GETTERS =====================================================
    //EFFECTS: returns whether this square is supposed to be visible or not
    public boolean isIdentityHidden() {
        return this.identityHidden;
    }

    //EFFECTS: returns whether this square has been flagged or not
    public boolean isFlagged() {
        return this.isFlagged;
    }

    //EFFECTS: returns identity of the square
    public Identity getIdentity() {
        return this.identity;
    }

    //EFFECTS: returns row Position (x-value)
    public int getColumn() {
        return column;
    }

    //EFFECTS: returns column Position (y-value)
    public int getRow() {
        return row;
    }

    public int getPosition() {
        return this.positionInGrid;
    }

    public int getIntegerIdentity() {
        switch (this.identity) {
            case BLANK:
                return 0;
            case ONE:
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            default:
                return getBombIdentity();
        }
    }

    private int getBombIdentity() {
        if (this.identity == BOMB) {
            return -1;
        } else {
            return -1000;
        }
    }

    //MODIFIES: this for all setters
    //SETTERS =====================================================

    //MODIFIES: this
    //EFFECTS: shows square, returns false if already shown
    public boolean showSquare() {
        if (this.isIdentityHidden()) {
            this.identityHidden = false;
            return true;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: flags the square if it is not, or takes away flag if it is
    public void changeFlag() {
        this.isFlagged = !this.isFlagged;
    }
}
