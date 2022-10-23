package model;

import org.json.JSONObject;
import persistence.Writeable;

//information represented:
// a single square on a board in the game of minesweeper.
//   - what type of square it is- bomb, blank, one...etc
//   - whether it has been flagged or flipped to be visible to the player

public class Square implements Writeable {

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

    //EFFECTS: returns the width of the board the square is meant to be on
    public int getBoardWidth() {
        return this.gridWidth;
    }

    //EFFECTS: returns the height of the board the square is meant to be on
    public int getBoardHeight() {
        return this.gridHeight;
    }

    public int getPosition() {
        return this.positionInGrid;
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
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
            case BOMB:
                return -1;
            default:
                return -1000;
        }
    }

    //EFFECTS: converts this square into information for a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("position", this.positionInGrid);
        json.put("identity", this.identity);
        json.put("isHidden",this.isIdentityHidden());
        json.put("isFlagged", this.isFlagged());
        return json;
    }

    //MODIFIES: this for all setters
    //SETTERS =====================================================

    //MODIFIES: this
    //EFFECTS: shows square and returns false, returns false if already not hidden
    public boolean showSquare() {
        if (this.isIdentityHidden()) {
            this.identityHidden = false;
            return true;
        }
        return false;
    }

    //REQUIRES: this square is hidden, and you are changing the flag when it is already unhidden
    //MODIFIES: this
    //EFFECTS: flags the square if it is not, or takes away flag if it is
    public void changeFlag() {
        this.isFlagged = !this.isFlagged;
    }
}
