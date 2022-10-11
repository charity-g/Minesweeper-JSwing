package model;

import java.util.ArrayList;
import java.util.Random;

//TODO 2 - implement
public class Board {

    private final int boardLength;
    private GameStatus gameStatus;
    private final ArrayList<Square> bombs;
    private final ArrayList<Square> allSquaresOnBoard;
    private final Random random = new Random();

    //CONSTRUCTOR
    //REQUIRES: bombNumber < (boardLength^2 / 2), boardLength > 3
    //EFFECTS: Places all the bombs in positions that aren't the user's choice, sets the identities,
    //         sets gameWonYet to false
    public Board(int boardLength, int bombNumber, int rowPosition, int columnPosition) {
        this.boardLength = boardLength;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.bombs = new ArrayList<Square>();
        this.allSquaresOnBoard = new ArrayList<Square>();
        setBombs(bombNumber, rowPosition, columnPosition);
        initializeAllSquaresOnBoard();
    }

    //REQUIRES: chosen position to exist on board
    //MODIFIES: this, and Square at chosen position
    //EFFECTS: If square is already visible, return false and do nothing,
    //         else return true after one of the following
    //     if identity of square = bomb, set game over
    //     if identity of square = number, show number
    //     if identity of square = blank, unearth all squares around it, and if there is a neighboring blank square
    //        around it, keep unearthing until the blank squares  unearthed is surrounded by numbers shown
    public boolean unearthSquare(int position) {

        //TODO
        return false;
        //USE unearthBlank()
    }

    // EFFECTS: flags the Square at the given position if it is unflagged, if it is flagged, unflag it
    public void changeSquareFlag(int position) {
        this.allSquaresOnBoard.get(position).changeFlag();
    }

    //HELPERS ======================================
    //REQUIRES: should only be called by Constructor
    //MODIFIES: this
    // EFFECTS makes number amount of bombs and sets their position to random positions that aren't their own and aren't
    //         the chosen position the user clicked on
    private void setBombs(int number, int rowPosition, int columnPosition) {
        int bombsMade = 0;
        while (bombsMade < number) {
            int potentialRowPos = random.nextInt(boardLength);
            int potentialColPos = random.nextInt(boardLength);
            Square potentialBomb = new Square(Identity.BOMB, potentialRowPos, potentialColPos, boardLength);
            boolean notUserChosenSquare = (potentialRowPos != rowPosition
                    && potentialColPos != columnPosition);
            if ((doesNotRepeatSquare(potentialBomb, this.bombs))
                    && notUserChosenSquare) {
                this.bombs.add(potentialBomb);
                bombsMade++;
            }
        }
    }

    //REQUIRES: should only be called by Constructor
    //MODIFIES: this
    //EFFECTS: creates squares and identities of the whole board based on the bombs around them
    private void initializeAllSquaresOnBoard() {
    }

    //REQUIRES: the square at the given position is blank
    //MODIFIES: this and the squares in the given position and the immediate neighboring positions around it
    //EFFECTS:
    private void unearthBlank(int position) {

    }

    //EFFECTS: returns true if the given list can be considered a set, else false
    public boolean listDoesNotRepeat(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int integerToCheckRepeat = list.get(i);
            if (!doesNotAppearMoreThanOnceInList(integerToCheckRepeat, list)) {
                return false;
            }
        }
        return true;
    }

    //REQUIRES: the given number to appear at least once in the list
    //EFFECTS: returns true if the given number is only found once in the list,
    public boolean doesNotAppearMoreThanOnceInList(int number, ArrayList<Integer> listOfInt) {
        int timesAppeared = 0;
        for (int i = 0; i < listOfInt.size(); i++) {
            if (listOfInt.get(i) == number) {
                timesAppeared++;
            }
        }
        if (timesAppeared == 1) {
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: returns true if the given object does not repeat in the list of objects
    public boolean doesNotRepeatSquare(Square givenSquare, ArrayList<Square> listOfSq) {
        for (Square sq : listOfSq) {
            if (sq.getColumnPosition() == givenSquare.getColumnPosition() &&
                    sq.getRowPosition() == givenSquare.getRowPosition()) {
                return false;
            }
        }
        return true;
    }

    // GETTERS =====================================================
    //EFFECTS: return boardLength
    public int getBoardLength() {
        return boardLength;
    }

    //EFFECTS: return the status of the game
    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    //EFFECTS: returns the square with position of row and column, or null if the square is off the board
    public Square getSquare(int rowPosition, int columnPosition) {
        for (Square s : this.allSquaresOnBoard) {
            if (s.getRowPosition() == rowPosition && s.getColumnPosition() == columnPosition) {
                return s;
            }
        }
        return null;
    }

    //EFFECTS: returns the bombs
    public ArrayList<Square> getBombs() {
        return this.bombs;
    }

    //EFFECTS: returns the bombs
    public ArrayList<Square> getAllSquares() {
        return this.allSquaresOnBoard;
    }
}
