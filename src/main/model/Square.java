package model;

public class Square {

    static final int SQUARE_LENGTH = 2;

    //TODO: eventually turn into enum because flag, bomb and number and blank must all be accounted for
    private final Identity identity;
    private boolean identityHidden;
    private final Position position;

    //TODO add comments for all methods

    //CONSTRUCTOR
    //EFFECTS: hides Identity, sets identity and position to one given
    public Square(Identity identity, Position pos) {
        this.identityHidden = true;
        this.identity = identity;
        this.position = pos;
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

    //returns position of the square
    public Position getPosition() {
        return this.position;
    }

    //SETTERS
    //changes visibility of this square to
    public void setIsIdentityHiddenTo(boolean isHidden) {
        this.identityHidden = isHidden;
    }
}
