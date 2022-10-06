package model;

public class Position {
    private int rowPosition;
    private int columnPosition;

    //TODO add comments for all methods
    // - do I want to include final field with grid length? would it be useful

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
