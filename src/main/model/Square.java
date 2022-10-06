package model;

public class Square {

    public final int SQUARE_LENGTH = 2;

    private int identity;
    private boolean identityHidden;
    private Position position;

    //TODO add comments for all methods

    // finalizes the identity of the square (the number or bomb) should be final
    // has isVisible = false
    // sets position
    public Square() {
    }


    //GETTERS
    //returns whether this square is supposed to be visible or not
    public boolean isIdentityHidden() {
        return this.identityHidden;
    }

    public int getIdentity() {
        return this.identity;
    }

    //SETTERS
    //changes visibility of this square to
    public void setIsIdentityHiddenTo(boolean isHidden) {
        this.identityHidden = isHidden;
    }
}
