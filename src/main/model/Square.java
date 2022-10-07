package model;

public class Square {

    static final int SQUARE_LENGTH = 2;

    private final int gridLength;
    // rowPosition = positionInGrid % gridLength
    // columnPosition = positionInGrid / gridLength
    private final int rowPosition;
    private final int columnPosition;
    private final int positionInGrid;

    private final Identity identity;
    private boolean identityHidden;
    private boolean isFlagged;

    //CONSTRUCTOR
    //REQUIRES: rowPos columnPos >= 0; maximum rowPos/columnPos needs to be < gridLength
    //EFFECTS : hides Identity, sets identity and position to one given, and sets position
    public Square(Identity identity, int rowPos, int columnPos, int gridLength) {
        this.identityHidden = true;
        this.isFlagged = false;
        this.identity = identity;
        this.rowPosition = rowPos;
        this.columnPosition = columnPos;
        this.gridLength = gridLength;
        this.positionInGrid = rowPos + (gridLength * columnPos);
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
    public int getRowPosition() {
        return rowPosition;
    }

    //EFFECTS: returns column Position (y-value)
    public int getColumnPosition() {
        return columnPosition;
    }

    public int getPosition() {
        return this.positionInGrid;
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
