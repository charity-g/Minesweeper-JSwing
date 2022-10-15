package ui;


import model.Square;
import model.boards.*;

import java.util.Random;
import java.util.Scanner;

import static model.GameStatus.*;

public class Game {
    Board boardInProgress;
    Scanner scan;
    Random random;

    //TODO add comments for all methods

    //EFFECTS: Creates a new game
    public Game() {
        random = new Random();
        scan = new Scanner(System.in);  // Create a Scanner object
        playGame();
    }

    //
    public void playGame() {
        createNewBoard();
        printBoard();
        while (boardInProgress.getGameStatus() == IN_PROGRESS) {
            playerAction();
            printBoard();
            verifyGameStatus();
        }

        endGame();
    }

    private void playerAction() {
        System.out.println("Flag a square or flip a square?");
        String playerChoice = scan.next();
        switch (playerChoice) {
            case "flag":
                flagPlayerChosenSquare();
                break;
            case "flip":
                unearthPlayerChosenSquare();
                break;
            default:
                System.out.println("Sorry, that wasn't a valid choice of action, please try again.");
                playerAction();
        }
    }

    private void flagPlayerChosenSquare() {
        int positionPicked = getChosenSquarePosition();
        Square squareToFlag = this.boardInProgress.getAllSquares().get(positionPicked);
        squareToFlag.changeFlag();
    }

    //EFFECTS: takes in the player's move and unearths the necessary square
    private void unearthPlayerChosenSquare() {
        int positionPicked = getChosenSquarePosition();
        boolean flipped = this.boardInProgress.unearthSquare(positionPicked);
        if (!flipped) {
            System.out.println("You picked a square that was already flipped! Please choose another one");
            unearthPlayerChosenSquare();
        }
    }

    private int getChosenSquarePosition() {
        int boardLength = this.boardInProgress.getBoardWidth();
        System.out.println("Choose the row of the square you want to flip over.");
        int rowPicked = scan.nextInt();
        if (rowPicked < 0 || rowPicked >= boardLength) {
            System.out.println("Not a valid row");
            return getChosenSquarePosition();
        }
        System.out.println("Choose the column of the square you want to flip over.");
        int columnPicked = scan.nextInt();
        if (columnPicked < 0 || columnPicked >= boardLength) {
            System.out.println("Not a valid column");
            return getChosenSquarePosition();
        }
        int positionPicked = (rowPicked * boardLength) + columnPicked;

        return positionPicked;
    }

    //EFFECT: prints out the board with current status of the game( which squares a hidden or shown and what knowledge
    //        is known)
    private void printBoard() {
        int boardLength = this.boardInProgress.getBoardWidth();
        System.out.println("");
        System.out.println("   | ");
        for (int i = 0; i < boardLength; i++) {
            System.out.print(i);
        }
        for (Square sq : this.boardInProgress.getAllSquares()) {
            if (sq.getRow() % boardLength == 0) {
                printNewLine(sq);
            } else {
                printSameLine(sq);
            }
        }
    }

    //REQUIRES: internal method to be only called by printBoard, and assumes given Square is in the position that is in
    //          the first column
    //EFFECTS: prints the square at the beginning of each new line (the first number in each new row)
    private void printNewLine(Square sq) {
        int rowNumber = sq.getRow() / this.boardInProgress.getBoardWidth();
        if (sq.isFlagged()) {
            System.out.println(" " + rowNumber + " | P ");
        } else if (sq.isIdentityHidden()) {
            System.out.println(" " + rowNumber + " | ? ");
        } else {
            System.out.println(" " + rowNumber + " | " + sq.getIntegerIdentity() + " ");
        }
    }

    //REQUIRES: internal method to be only called by printBoard, and assumes given Square is in the position that isn't
    //          in the first column
    //EFFECTS: prints the square at the next position in the row
    private void printSameLine(Square sq) {
        if (sq.isFlagged()) {
            System.out.print("P ");
        } else if (sq.isIdentityHidden()) {
            System.out.print("? ");
        } else if (sq.getIntegerIdentity() != -1) {
            System.out.print(sq.getIntegerIdentity() + " ");
        } else {
            System.out.print("X ");
        }
    }

    //EFFECTS: prints out the bombs and actual values
    private void printBoardSolutions() {
        //TODO
        // turn all squares to shown and the print board
    }

    //EFFECTS: creates a new board based on user's request for beginner, medium, or advanced
    public void createNewBoard() {
        System.out.println("What difficulty do you want your next board to be? choose your difficulty between 1-3");
        System.out.println("1 being the easiest and 3 being the hardest");
        int difficulty = scan.nextInt();  // Read user input
        chooseDifficulty(difficulty);
    }

    //EFFECTS: creates a new board and sets the current board to be that board, beginner, medium, or advanced
    private void chooseDifficulty(int difficulty) {
        //TODO
    }

    //EFFECTS: prints won or lost solution
    public void endGame() {
        //TODO
    }

    //EFFECTS: constantly checks if game won or lost
    private void verifyGameStatus() {
        //TODO
    }


    //TODO once we get more information
    public void loadBoard() {
    }

    public void saveBoard() {
    }
}
