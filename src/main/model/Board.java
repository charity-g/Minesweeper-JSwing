package model;

import java.util.ArrayList;
import java.util.Random;

import static model.Identity.*;

public class Board {

    private final int boardLength;
    private final int boardSize;
    private GameStatus gameStatus;
    private final ArrayList<Square> bombs;
    private final ArrayList<Square> allSquaresOnBoard;
    private final Random random = new Random();
    private final ArrayList<Integer> listOfBombPos;
    private ArrayList<Integer> allSquaresMatrix;


    //CONSTRUCTOR
    //REQUIRES: bombNumber < (boardLength^2 / 2), boardLength > 3
    //EFFECTS: Places all the bombs in positions that aren't the user's choice, sets the identities,
    //         sets gameWonYet to false
    public Board(int boardLength, int bombNumber, int rowPosition, int columnPosition) {
        this.boardLength = boardLength;
        this.boardSize = boardLength * boardLength;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.bombs = new ArrayList<Square>();
        this.listOfBombPos = new ArrayList<Integer>();
        initializeAllBombPos();
        this.allSquaresMatrix = new ArrayList<Integer>();
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
        //TODO
        //transfers the matrix version into the square version
        initializeAllSquaresOnBoardMatrix();
        for (int i = 0; i < allSquaresMatrix.size(); i++) {
            int bombCounterAtPositionI = allSquaresMatrix.get(i);
            switch (bombCounterAtPositionI) {
                case 1:
                    this.allSquaresOnBoard.add(new Square(ONE, i % this.boardLength,
                            i / this.boardLength, boardLength));
                    break;
                case 2:
                    this.allSquaresOnBoard.add(new Square(TWO, i % this.boardLength,
                            i / this.boardLength, boardLength));
                    break;
                case 3:
                    this.allSquaresOnBoard.add(new Square(THREE, i % this.boardLength,
                            i / this.boardLength, boardLength));
                    break;
                case 4:
                    this.allSquaresOnBoard.add(new Square(FOUR, i % this.boardLength,
                            i / this.boardLength, boardLength));
                    break;
                case 5:
                    this.allSquaresOnBoard.add(new Square(FIVE, i % this.boardLength,
                            i / this.boardLength, boardLength));
                    break;
                case 6:
                    this.allSquaresOnBoard.add(new Square(SIX, i % this.boardLength,
                            i / this.boardLength, boardLength));
                    break;
                case 7:
                    this.allSquaresOnBoard.add(new Square(SEVEN, i % this.boardLength,
                            i / this.boardLength, boardLength));
                    break;
                case 8:
                    this.allSquaresOnBoard.add(new Square(EIGHT, i % this.boardLength,
                            i / this.boardLength, boardLength));
                    break;
                case 0:
                    this.allSquaresOnBoard.add(new Square(BLANK, i % this.boardLength,
                            i / this.boardLength, boardLength));
                    break;
                case -1:
                    this.allSquaresOnBoard.add(new Square(BOMB, i % this.boardLength,
                            i / this.boardLength, boardLength));
                    break;
            }
        }
    }

    //REQUIRES: the square at the given position is blank
    //MODIFIES: this and the squares in the given position and the immediate neighboring positions around it
    //EFFECTS:
    private void unearthBlank(int position) {

    }

    //REQUIRES: Only called by initializeAllSquaresOnBoard
    //MODIFIES: THIS
    //EFFECTS: Returns a square board of this.boardLength, with numbers 0-8 represent the number of bombs and -1
    //         representing the bomb positions
    private void initializeAllSquaresOnBoardMatrix() {
        for (int i = 0; i < this.boardSize; i++) {
            if (this.listOfBombPos.contains(i)) {
                this.allSquaresMatrix.add(-1);
            } else {
                this.allSquaresMatrix.add(0);
            }
        }
    }

    //REQUIRES: Only called by initializeAllSquaresOnBoardMatrix as an internal method
    //MODIFIES: this
    //EFFECTS: depending on the position of the bombs, update the number of bombs around the matrix and returns the
    //         matrix with all the correct number of bombs/bomb-counters and positions
    private void calculateNeighboringBombsMatrix() {
        for (int pos : this.listOfBombPos) {
            calculateNeighborsFromOneBomb(pos);
        }
    }

    //REQUIRES: only called by calculateNeighboringBombsMatrix as an internal method
    //MODIFIES: this
    //EFFECTS: for the given position, adds a bomb to the counter for all the neighbors and returns the calculated
    //         matrix with the bomb counters of the neighboring squares added in
    private void calculateNeighborsFromOneBomb(int position) {
        ArrayList<Integer> neighbors = new ArrayList<Integer>();
        neighbors.add(position - 1); //left
        neighbors.add(position + 1); //right
        neighbors.add(position - this.boardLength); //up
        neighbors.add(position - this.boardLength - 1); //top left
        neighbors.add(position - this.boardLength + 1); //top right
        neighbors.add(position + this.boardLength); //down
        neighbors.add(position + this.boardLength - 1); //bot left
        neighbors.add(position + this.boardLength + 1); //bot right

        for (int n : neighbors) {
            if (n >= 0 && n < this.boardSize) {
                int oldBombNumber = allSquaresMatrix.get(n);
                allSquaresMatrix.set(n, oldBombNumber++);
            }
        }
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
            if (sq.getColumnPosition() == givenSquare.getColumnPosition()
                    && sq.getRowPosition() == givenSquare.getRowPosition()) {
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

    //REQUIRES: for bombs to be initialized by set bombs
    //EFFECTS: to create the list of all bomb positions after having the bombs set up
    private void initializeAllBombPos() {
        for (Square s : this.bombs) {
            listOfBombPos.add(s.getPosition());
        }
    }
}
