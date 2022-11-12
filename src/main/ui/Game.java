package ui;


import model.BoardStatus;
import model.Identity;
import model.Square;
import model.Board;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import static model.BoardStatus.*;

//Game represents a minesweeper game
//   - what board is currently being played
//   - how the user can interact and take actions to win(or lose!) the game
// note: unable to be tested due to ui capabilities

public class Game {
    Board boardInProgress;
    Random random;
    Scanner scan;//TODO tbd

    public Game() {
        random = new Random();
    }


    //EFFECTS: unflag the square at position picked
    private void unFlagPlayerChoice() {
        int positionPicked = getChosenSquarePosition();
        if (boardInProgress.getSquare(positionPicked).isIdentityHidden()) {
            boardInProgress.unflagSquare(positionPicked);
        } else {
            System.out.println("This square has already been flipped! You can't unflag it.");
        }
    }

    //EFFECTS: unflag the square at position picked
    private void flagPlayerChoice() {
        int positionPicked = getChosenSquarePosition();
        if (boardInProgress.getSquare(positionPicked).isIdentityHidden()) {
            boardInProgress.flagSquare(positionPicked);
        } else {
            System.out.println("This square has already been flipped! You can't flag it.");
        }
    }

    //EFFECTS: takes in the player's move and unearths the necessary square
    public void unearthPlayerChosenSquare(int positionPicked) {
        if (this.boardInProgress.getSquare(positionPicked).isFlagged()) {
            System.out.println("This square is currently flagged! Please unflag it.");
        } else if (!this.boardInProgress.getSquare(positionPicked).isIdentityHidden()) {
            System.out.println("You picked a square that was already flipped! Please choose another one");
        } else {
            boolean flipped = this.boardInProgress.unearthSquare(positionPicked);
            assert (flipped == true);
        }

    }

    private int getChosenSquarePosition() {
        System.out.println("Choose the row of the square you want to do the action to.");
        int rowPicked;
        int columnPicked;
        try {
            rowPicked = scan.nextInt();
        } catch (Exception e) {
            rowPicked = -1;
        }

        System.out.println("Choose the column of the square you want to do the action to.");
        try {
            columnPicked = scan.nextInt();
        } catch (Exception e) {
            columnPicked = -1;
        }

        if (invalidPosition(rowPicked, columnPicked)) {
            return getChosenSquarePosition();
        }

        return (rowPicked * this.boardInProgress.getBoardWidth()) + columnPicked;
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

    // PRIVATE METHODS
    //EFFECTS: creates a new beginner board and sets that as the current board
    private void setupNewBeginnerBoard() {
        int boardLength = 8 + random.nextInt(3);
        this.boardInProgress = new Board(boardLength, boardLength, 10);
    }


    //EFFECTS: creates a new intermediate board and sets that as the current board
    private void setupNewIntermediateBoard() {
        int boardWidth = 13 + random.nextInt(4);
        int boardHeight = 15 + random.nextInt(2);
        this.boardInProgress = new Board(boardWidth, boardHeight, 40);
    }


    //EFFECTS: creates a new advanced board and sets that as the current board
    private void setupNewAdvancedBoard() {
        int choice = random.nextInt(2);
        if (choice == 0) {
            this.boardInProgress = new Board(16, 30, 99);
        } else {
            this.boardInProgress = new Board(30, 16, 99);
        }
    }

    public Board getBoardInProgress() {
        return this.boardInProgress;
    }

    public void setBoardInProgress(Board newBoard) {
        this.boardInProgress = newBoard;
    }
}
