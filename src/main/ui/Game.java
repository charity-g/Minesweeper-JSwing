package ui;

import model.Board;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

//Game represents a minesweeper game
//   - what board is currently being played
//   - how the user can interact and take actions to win(or lose!) the game
// note: unable to be tested due to ui capabilities

public class Game {
    private Board boardInProgress;
    Random random;

    //EFFECTS: Creates a new game with no boardInProgress yet
    public Game() {
        random = new Random();
    }

    //EFFECTS: unflag the square at position picked
    private void unFlagPlayerChoice(int positionPicked) {
        if (boardInProgress.getSquare(positionPicked).isIdentityHidden()) {
            boardInProgress.unflagSquare(positionPicked);
        } else {
            System.out.println("This square has already been flipped! You can't unflag it.");
        }
    }

    //EFFECTS: unflag the square at position picked
    private void flagPlayerChoice(int positionPicked) {
        if (boardInProgress.getSquare(positionPicked).isIdentityHidden()) {
            boardInProgress.flagSquare(positionPicked);
        } else {
            System.out.println("This square has already been flipped! You can't flag it.");
        }
    }

    //EFFECTS: takes in the player's move and unearths the necessary square
    public void unearthPlayerChosenSquare(int positionPicked) throws CannotFlipFlaggedSquareException {
        if (this.boardInProgress.getSquare(positionPicked).isFlagged()) {
            throw new CannotFlipFlaggedSquareException();
        } else {
            boolean flipped = this.boardInProgress.unearthSquare(positionPicked);
            assert (flipped);
        }

    }



    //EFFECTS: returns true if the position is NOT on the board, else returns false
    private boolean invalidPosition(int rowPicked, int columnPicked) {
        if (rowPicked < 0 || rowPicked >= this.boardInProgress.getBoardHeight()) {
            System.out.println("Not a valid row");
            return true;
        }
        if (columnPicked < 0 || columnPicked >= this.boardInProgress.getBoardWidth()) {
            System.out.println("Not a valid column");
            return true;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: reads the saved json board data and sets the board in progress to be the saved data
    public void loadBoard() throws IOException {
        JsonReader reader = new JsonReader("./data/savedBoardInProgress.json");

        this.boardInProgress = reader.read();
    }

    //EFFECTS: saves this board into json data into the file
    public void saveBoard() throws FileNotFoundException {
        JsonWriter writer = new JsonWriter("./data/savedBoardInProgress.json");

        writer.write(this.boardInProgress);
    }

    // CREATE NEW BOARD IN PROGRESS METHODS
    //EFFECTS: creates a new beginner board and sets that as the current board
    public void setupNewBeginnerBoard() {
        int boardLength = 8 + random.nextInt(3);
        this.boardInProgress = new Board(boardLength, boardLength, 10);
    }


    //EFFECTS: creates a new intermediate board and sets that as the current board
    public void setupNewIntermediateBoard() {
        int boardWidth = 13 + random.nextInt(4);
        int boardHeight = 15 + random.nextInt(2);
        this.boardInProgress = new Board(boardWidth, boardHeight, 40);
    }


    //EFFECTS: creates a new advanced board and sets that as the current board
    public void setupNewAdvancedBoard() {
        this.boardInProgress = new Board(26, 16, 95);
    }

    public Board getBoardInProgress() {
        return this.boardInProgress;
    }
}
