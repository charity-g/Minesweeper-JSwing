package model;

public class Square {

    static final int SQUARE_LENGTH = 2;

    private final Identity identity;
    private boolean identityHidden;
    private final int rowPosition;
    private final int columnPosition;
    private final int positioninGrid;
    private final int gridLength;

    //CONSTRUCTOR
    //REQUIRES: rowPos columnPos >= 0; maximum rowPos/columnPos needs to be < gridLength
    //EFFECTS : hides Identity, sets identity and position to one given, and sets position
    public Square(Identity identity, int rowPos, int columnPos, int gridLength) {
        this.identityHidden = true;
        this.identity = identity;
        this.rowPosition = rowPos;
        this.columnPosition = columnPos;
        this.gridLength = gridLength;
        this.positioninGrid = rowPos + (gridLength * columnPos);
    }

    //GETTERS
    //returns whether this square is supposed to be visible or not
    public boolean isIdentityHidden() {
        return this.identityHidden;
    }

    //returns identity of the square
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

    public int getPosition(){
        return this.positioninGrid;
    }

    //SETTERS
    //changes visibility of this square to
    public void setIsIdentityHiddenTo(boolean isHidden) {
        this.identityHidden = isHidden;
    }
}
