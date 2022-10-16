package model;

import java.util.ArrayList;
import java.util.Random;

import static model.GameStatus.*;
import static model.Identity.*;

public class Board {
    protected final Random random = new Random();

    protected final int boardWidth;
    protected final int boardHeight;
    protected final int boardSize;
    protected final int bombNumber;

    protected final ArrayList<Square> bombs;
    protected final ArrayList<Square> allSquaresOnBoard;

    protected GameStatus gameStatus;

    private ArrayList<Integer> allSquaresMatrix;


    //CONSTRUCTOR
    //EFFECTS: Places all the bombs in positions that aren't the user's choice, sets the identities,
    //         sets gameWonYet to false
    public Board(int boardWidth, int boardHeight, int bombNumber) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.boardSize = boardHeight * boardWidth;
        this.bombNumber = bombNumber;

        this.bombs = new ArrayList<Square>();
        setBombs();
        this.allSquaresOnBoard = new ArrayList<Square>();
        initializeAllSquaresOnBoard();
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    //REQUIRES: only called during testing for a seed
    //EFFECTS: Places all the bombs in positions that aren't the user's choice, sets the identities,
    //         sets gameWonYet to false
    public Board(int boardWidth, int boardHeight, int bombNumber, long seed) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.boardSize = boardHeight * boardWidth;
        this.bombNumber = bombNumber;
        this.bombs = new ArrayList<Square>();
        setBombs(seed);
        this.allSquaresOnBoard = new ArrayList<Square>();
        initializeAllSquaresOnBoard();
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    //REQUIRES: should only be called by Constructor
    //MODIFIES: this
    // EFFECTS makes number amount of bombs and sets their position to random positions that aren't their own and aren't
    //         the chosen position the user clicked on
    protected void setBombs() {
        int bombsMade = 0;
        while (bombsMade < this.bombNumber) {
            if (makeBomb()) {
                bombsMade++;
            }
        }
    }

    //REQUIRES: should only be called by Constructor and in tests
    //MODIFIES: this
    // EFFECTS makes number amount of bombs and sets their position to random positions that aren't their own and aren't
    //         the chosen position the user clicked on
    protected void setBombs(long seed) {
        random.setSeed(seed);
        int bombsMade = 0;
        while (bombsMade < this.bombNumber) {
            if (makeBomb()) {
                bombsMade++;
            }
        }
    }

    //REQUIRES: should only be called through the constructor
    //MODIFIES: this
    //EFFECTS: returns true if it adds a new bomb, returns false if the bomb it was going to add already
    //         existed at that position
    private boolean makeBomb() {
        int potentialCol = random.nextInt(boardWidth);
        int potentialRow = random.nextInt(boardHeight);
        if (givenPositionIsNotAlreadyABomb(potentialCol, potentialRow)) {
            Square potentialBomb = new Square(BOMB, potentialCol, potentialRow,
                    this.boardWidth, this.boardHeight);
            this.bombs.add(potentialBomb);
            return true;
        } else {
            return false;
        }
    }

    //REQUIRES: should only be called by Constructor
    //EFFECTS:
    public void initializeAllSquaresOnBoard() {
        addAllSquareNumericalIdentities(makeAllSquaresMatrixBlank());
        convertMatrixIntoSquares();
    }

    //REQUIRES: should only be called by Constructor
    //EFFECTS:
    private void convertMatrixIntoSquares() {
        for (int index = 0; index < this.allSquaresMatrix.size(); index++) {
            Identity identityAtIndex = convertToIdentity(this.allSquaresMatrix.get(index));
            int col = index % this.boardWidth;
            int row = index / this.boardWidth;
            this.allSquaresOnBoard.add(new Square(identityAtIndex, col, row, this.boardWidth, this.boardHeight));
        }
    }

    private Identity convertToIdentity(Integer numericalIdentity) {
        switch (numericalIdentity) {
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            case 6:
                return SIX;
            case 7:
                return SEVEN;
            case 8:
                return EIGHT;
            case -1:
                return BOMB;
            default:
                return BLANK;
        }
    }

    //REQUIRES: should only be called by Constructor
    //EFFECTS: creates a Square object for each square on the whole board, setting bombs to -1 and other squares to 0
    private ArrayList<Integer> makeAllSquaresMatrixBlank() {
        ArrayList<Integer> allSquaresMatrix = new ArrayList<Integer>();
        ArrayList<Integer> listOfBombPos = getListOfBombPos();
        for (int i = 0; i < this.boardSize; i++) {
            if (listOfBombPos.contains(i)) {
                allSquaresMatrix.add(-1);
            } else {
                allSquaresMatrix.add(0);
            }
        }
        return allSquaresMatrix;
    }

    //REQUIRES: should only be called by Constructor
    //EFFECTS: for each square on the matrix, adds the number of neighboring bombs around
    private ArrayList<Integer> addAllSquareNumericalIdentities(ArrayList<Integer> allSquaresMatrixBlank) {
        ArrayList<Integer> neighborsOfAllBombs = getNeighborsOfAllBombs();
        ArrayList<Integer> allSquareMatrixFilled = addBombCountToPositionsAroundBombs(allSquaresMatrixBlank,
                neighborsOfAllBombs);
        return allSquareMatrixFilled;
    }

    //EFFECTS: for each position that is a neighbor to a bomb, adds one to the bomb count at that position
    private ArrayList<Integer> addBombCountToPositionsAroundBombs(ArrayList<Integer> allSquaresMatrix,
                                                                  ArrayList<Integer> neighborsOfAllBombs) {
        this.allSquaresMatrix = allSquaresMatrix;
        for (int positionToAddBombCountTo : neighborsOfAllBombs) {
            int oldBombCount = this.allSquaresMatrix.get(positionToAddBombCountTo);
            int newBombCount = oldBombCount + 1;
            this.allSquaresMatrix.set(positionToAddBombCountTo, newBombCount);
        }
        return this.allSquaresMatrix;
    }


    //REQUIRES: should only be called by addAllSquareIdentities
    //EFFECTS: returns the list of every single square's position if they are a neighbor to a bomb.
    //         Positions in the list can and should repeat if two bombs have the same neighbor
    private ArrayList<Integer> getNeighborsOfAllBombs() {
        ArrayList<Integer> neighborsOfAllBombs = new ArrayList<Integer>();
        for (int bombPos : getListOfBombPos()) {
            neighborsOfAllBombs.addAll(getNeighborPositions(bombPos));
        }
        return neighborsOfAllBombs;
    }


    //NON-CONSTRUCTOR METHODS ========================================================================
    //REQUIRES: chosen position to exist on board
    //MODIFIES: this, and Square at chosen position
    //EFFECTS: If square is already visible, return false and do nothing,
    //         else return true after one of the following
    //     if identity of square = bomb, set gameStatus to over
    //     if identity of square = number, show number
    //     if identity of square = blank, unearth all squares around it, and if there is a neighboring blank square
    //        around it, keep unearthing until the blank squares  unearthed is surrounded by numbers shown
    public boolean unearthSquare(int position) {
        Square square = getSquare(position);
        boolean wasHidden = square.showSquare();
        if (!wasHidden) {
            return false;
        } else if (square.getIdentity() == BOMB) {
            this.gameStatus = LOST;
            return true;
        } else if (square.getIdentity() == BLANK) {
            unearthNeighborsOfBlankSquare(position);
            return true;
        } else {
            return true;
        }
    }

    //EFFECTS: unearths the neighbors around this position
    private void unearthNeighborsOfBlankSquare(int position) {
        ArrayList<Integer> neighbors = getNeighborPositions(position);
        for (int pos : neighbors) {
            unearthSquare(pos);
        }
    }

    //EFFECTS: returns all the valid neighboring positions on the board around the given position
    public ArrayList<Integer> getNeighborPositions(int position) {
        int positionCol = position % this.boardWidth;

        if (isCornerPosition(position)) {
            return getCornerNeighborPositions(position);
        } else if (positionCol == 0) {
            //left side excluding top left and bot left because those are corners
            return getLeftSidePositions(position);
        } else if (positionCol == this.boardWidth - 1) {
            //right side excluding top right and bot right because those are corners
            return getRightSidePositions(position);
        } else {
            return filterOutOfBounds(getAllNeighborPositions(position));
        }
    }

    //REQUIRES: to be given a corner position
    //EFFECTS: the arrayList with all the neighboring positions for the corners
    private ArrayList<Integer> getCornerNeighborPositions(int cornerPos) {
        ArrayList<Integer> neighbors = new ArrayList<Integer>();

        if (cornerPos == 0) {
            neighbors.add(1);
            neighbors.add(this.boardWidth);
            neighbors.add(this.boardWidth + 1);
        } else if (cornerPos == this.boardWidth - 1) {
            neighbors.add(this.boardWidth - 2);
            neighbors.add((this.boardWidth * 2) - 1);
            neighbors.add((this.boardWidth * 2) - 2);
        } else if (cornerPos == this.boardSize - this.boardWidth) {
            neighbors.add(this.boardSize - this.boardWidth + 1);
            neighbors.add(this.boardSize - (2 * this.boardWidth));
            neighbors.add(this.boardSize - (2 * this.boardWidth) + 1);
        } else if (cornerPos == this.boardSize - 1) {
            neighbors.add(this.boardSize - 2);
            neighbors.add(this.boardSize - this.boardWidth - 1);
            neighbors.add(this.boardSize - this.boardWidth - 2);
        }
        return neighbors;
    }

    //REQUIRES: assumes that the given position is in the left-most column and is not a corner position
    //EFFECTS: returns the list of positions that a square in the left-most column would have
    private ArrayList<Integer> getLeftSidePositions(int leftColPos) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        neighbors.add(leftColPos - this.boardWidth);
        neighbors.add(leftColPos - this.boardWidth + 1);
        neighbors.add(leftColPos + 1);
        neighbors.add(leftColPos + this.boardWidth);
        neighbors.add(leftColPos + this.boardWidth + 1);

        return neighbors;
    }

    //REQUIRES: assumes that the given position is in the right-most column and is not a corner position
    //EFFECTS: returns the list of positions that a square in the right-most column would have
    private ArrayList<Integer> getRightSidePositions(int rightColPos) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        neighbors.add(rightColPos - this.boardWidth);
        neighbors.add(rightColPos - this.boardWidth - 1);
        neighbors.add(rightColPos - 1);
        neighbors.add(rightColPos + this.boardWidth);
        neighbors.add(rightColPos + this.boardWidth - 1);

        return neighbors;
    }

    //EFFECTS: returns true if given position is one of 4 corner positions
    private boolean isCornerPosition(int position) {
        int[] cornerPositions = {0, this.boardWidth - 1, this.boardSize - this.boardWidth, this.boardSize - 1};
        for (int cornerPos : cornerPositions) {
            if (cornerPos == position) {
                return true;
            }
        }
        return false;
    }

    //REQUIRES: assumes the list of positions given is supposed to be on a board of this.boardSize
    //EFFECTS: returns the list of positions with the positions that are negative,
    public ArrayList<Integer> filterOutOfBounds(ArrayList<Integer> allPositions) {
        ArrayList<Integer> positionsToBeKept = new ArrayList<>();
        for (int position : allPositions) {
            if (position >= 0 && position < this.boardSize) {
                positionsToBeKept.add(position);
            }
        }
        return positionsToBeKept;
    }

    //EFFECTS: returns the positions of all the possible neighbors of the given position, even if the neighbor is off
    //         the board or if the neighbor is not actually a neighbor
    protected ArrayList<Integer> getAllNeighborPositions(int position) {
        ArrayList<Integer> allNeighbors = new ArrayList<>();
        allNeighbors.add(position - 1); //left
        allNeighbors.add(position + 1); //right
        allNeighbors.add(position - this.boardWidth); //up
        allNeighbors.add(position - this.boardWidth - 1); //top left
        allNeighbors.add(position - this.boardWidth + 1); //top right
        allNeighbors.add(position + this.boardWidth); //down
        allNeighbors.add(position + this.boardWidth - 1); //bot left
        allNeighbors.add(position + this.boardWidth + 1); //bot right

        return allNeighbors;
    }

    // EFFECTS: flags the Square at the given position if it is unflagged, if it is flagged, unflag it
    public void changeSquareFlag(int position) {
        this.allSquaresOnBoard.get(position).changeFlag();
    }

    //EFFECTS: returns true if the given position is not a position of a previously made bomb, else return false
    public boolean givenPositionIsNotAlreadyABomb(int col, int row) {
        int positionOfPotentialBomb = (this.boardWidth * row) + col;
        if (getListOfBombPos().contains(positionOfPotentialBomb)) {
            return false;
        } else {
            return true;
        }
    }

    //EFFECTS:
    public void setGameWon() {
        this.gameStatus = WON;
    }

    // GETTERS =====================================================

    //REQUIRES: for bombs to be initialized by set bombs
    //EFFECTS: to create the list of all bomb positions after having the bombs set up
    public ArrayList<Integer> getListOfBombPos() {
        ArrayList<Integer> listOfBombPos = new ArrayList<Integer>();
        for (Square s : this.bombs) {
            listOfBombPos.add(s.getPosition());
        }
        return listOfBombPos;
    }

    //EFFECTS: returns the square with position of row and column, or null if the square is off the board
    public Square getSquare(int row, int column) {
        for (Square s : this.allSquaresOnBoard) {
            if (s.getColumn() == column && s.getRow() == row) {
                return s;
            }
        }
        return null;
    }

    //EFFECTS: returns the square with position (row * boardWidth) + column, or null if the square is off the board
    public Square getSquare(int position) {
        for (Square s : this.allSquaresOnBoard) {
            if (s.getPosition() == position) {
                return s;
            }
        }
        return null;
    }

    //EFFECTS: return width of board; ie: how many columns the board has
    public int getBoardWidth() {
        return this.boardWidth;
    }

    //EFFECTS: return height of board; ie: how many rows the board has
    public int getBoardHeight() {
        return this.boardHeight;
    }

    //EFFECTS: returns boardSize
    public int getBoardSize() {
        return this.boardSize;
    }

    //EFFECTS: return the status of the game
    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    //EFFECTS: returns the bombs
    public ArrayList<Square> getBombs() {
        return this.bombs;
    }

    //EFFECTS: returns the bombs
    public ArrayList<Square> getAllSquares() {
        return this.allSquaresOnBoard;
    }

    //EFFECTS: returns bomb number
    public int getBombNumber() {
        return this.bombNumber;
    }
}
