package model;

public class Position {
    private final int rowPosition;
    private final int columnPosition;

    // TODO - do I want to include final field with grid length? would it be useful

    //CONSTRUCTOR
    //REQUIRES: row > 0, column > 0
    //EFFECTS: Initializes and sets as final the row and column fields of this position
    public Position(int row, int column) {
        rowPosition = row;
        columnPosition = column;
    }

    //GETTERS
    //EFFECTS: returns row Position (x-value)
    public int getRowPosition() {
        return rowPosition;
    }

    //EFFECTS: returns column Position (y-value)
    public int getColumnPosition() {
        return columnPosition;
    }

}
