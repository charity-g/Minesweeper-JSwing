package ui;


import model.GameStatus;
import model.Identity;
import model.Square;
import model.Board;

import java.util.Random;
import java.util.Scanner;

import static model.GameStatus.*;

//Game represents a minesweeper game
//   - what board is currently being played
//   - how the user can interact and take actions to win(or lose!) the game
// note: unable to be tested due to ui capabilities

public class Game {
    Board boardInProgress;
    Scanner scan;
    Random random;

    //EFFECTS: Creates a new game
    public Game() {
        random = new Random();
        scan = new Scanner(System.in);  // Create a Scanner object
        playGame();
    }

    //EFFECTS: creates a new board and executes each player action and handles effects until game ends
    public void playGame() {
        createNewBoard();
        printBoard();
        while (boardInProgress.getGameStatus() == IN_PROGRESS) {
            playerAction();
            printBoard();
            checkIfGameWon();
        }

        endGame();
    }

    //TODO: catch the case where player tries to flag already flagged square
    //EFFECTS: handles user input for what action they want to take: flagging a square or flipping a square
    private void playerAction() {
        System.out.println("");
        System.out.println("Flag, unflag, or flip a square? ");
        String playerChoice = scan.next();
        switch (playerChoice) {
            case "flag":
            case "unflag":
                changeFlagPlayerChosenSquare(playerChoice);
                break;
            case "flip":
                unearthPlayerChosenSquare();
                break;
            default:
                System.out.println("Sorry, that wasn't a valid choice of action, please try again.");
                playerAction();
        }
    }

    //EFFECTS: flags or unflag the square at position picked
    private void changeFlagPlayerChosenSquare(String playerChoice) {
        int positionPicked = getChosenSquarePosition();
        Square squareToFlag = this.boardInProgress.getSquare(positionPicked);
        squareToFlag.changeFlag();
    }

    //EFFECTS: takes in the player's move and unearths the necessary square
    private void unearthPlayerChosenSquare() {
        int positionPicked = getChosenSquarePosition();
        if (this.boardInProgress.getSquare(positionPicked).isFlagged()) {
            System.out.println("This square is currently flagged! Please unflag it.");
        } else {
            if (!this.boardInProgress.getSquare(positionPicked).isIdentityHidden()) {
                System.out.println("You picked a square that was already flipped! Please choose another one");
                unearthPlayerChosenSquare();
            }

            boolean flipped = this.boardInProgress.unearthSquare(positionPicked);
            assert (flipped == true);
        }
    }

    private int getChosenSquarePosition() {
        int boardLength = this.boardInProgress.getBoardWidth();
        System.out.println("Choose the row of the square you want to do the action to.");
        int rowPicked = scan.nextInt();
        if (rowPicked < 0 || rowPicked >= this.boardInProgress.getBoardHeight()) {
            System.out.println("Not a valid row");
            return getChosenSquarePosition();
        }
        System.out.println("Choose the column of the square you want to do the action to.");
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
        int boardWidth = this.boardInProgress.getBoardWidth();
        System.out.println("");
        printColumnNumbers(boardWidth);
        for (int position = 0; position < this.boardInProgress.getBoardSize(); position++) {
            Square sq = this.boardInProgress.getSquare(position);
            if (position % boardWidth == 0) {
                printNewLine(sq);
            } else {
                printSameLine(sq);
            }
        }
    }

    //EFFECTS: prints out the column numbers in one line
    private void printColumnNumbers(int boardWidth) {
        System.out.print("    | ");
        for (int i = 0; i < boardWidth; i++) {
            if (i < 10) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println("");
        System.out.print("------");
        for (int i = 0; i < boardWidth; i++) {
            System.out.print("---");
        }
    }

    //REQUIRES: internal method to be only called by printBoard, and assumes given Square is in the position that is in
    //          the first column
    //EFFECTS: prints the square at the beginning of each new line (the first number in each new row)
    private void printNewLine(Square sq) {
        System.out.println("");
        int rowNumber = sq.getRow();
        if (rowNumber < 10) {
            if (sq.isFlagged() && sq.isIdentityHidden()) {
                System.out.print(" " + rowNumber + "  | P  ");
            } else if (sq.isIdentityHidden()) {
                System.out.print(" " + rowNumber + "  | ?  ");
            } else if (sq.getIntegerIdentity() != -1) {
                System.out.print(" " + rowNumber + "  | " + sq.getIntegerIdentity() + "  ");
            } else {
                System.out.print(" " + rowNumber + "  | X  ");
            }
        } else {
            if (sq.isFlagged() && sq.isIdentityHidden()) {
                System.out.print(" " + rowNumber + " | P  ");
            } else if (sq.isIdentityHidden()) {
                System.out.print(" " + rowNumber + " | ?  ");
            } else if (sq.getIntegerIdentity() != -1) {
                System.out.print(" " + rowNumber + " | " + sq.getIntegerIdentity() + "  ");
            } else {
                System.out.println(" " + rowNumber + " | X  ");
            }
        }
    }

    //REQUIRES: internal method to be only called by printBoard, and assumes given Square is in the position that isn't
    //          in the first column
    //EFFECTS: prints the square at the next position in the row
    private void printSameLine(Square sq) {
        if (sq.isFlagged() && sq.isIdentityHidden()) {
            System.out.print("P  ");
        } else if (sq.isIdentityHidden()) {
            System.out.print("?  ");
        } else if (sq.getIntegerIdentity() != -1) {
            System.out.print(sq.getIntegerIdentity() + "  ");
        } else {
            System.out.print("X  ");
        }
    }

    //EFFECTS: creates a new board based on user's request for beginner, medium, or advanced
    public void createNewBoard() {
        System.out.println("There are 3 different difficulties of boards: Beginner, Intermediate, or Advanced.");
        System.out.println("What difficulty do you want your next board to be? Capitals do not matter.");
        String difficulty = scan.nextLine();  // Read user input
        handleUserChosenDifficulty(difficulty);
    }

    //EFFECTS: creates a new board and sets the current board to be that board, beginner, medium, or advanced
    private void handleUserChosenDifficulty(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "beginner":
                setupNewBeginnerBoard();
                break;
            case "intermediate":
                setupNewIntermediateBoard();
                break;
            case "advanced":
                setupNewAdvancedBoard();
                break;
            default:
                System.out.println("You input a typo. Please try again.");
                System.out.println("");
                createNewBoard();
        }
    }

    //EFFECTS: prints out the solution and where the bombs were and congratulatory message
    public void endGame() {
        GameStatus endGameStatus = this.boardInProgress.getGameStatus();
        System.out.println(" SOLUTION :");
        printBoardSolutions();
        System.out.println(" ");
        if (endGameStatus == WON) {
            System.out.println("WOO! Congratulations on your win!");
        } else {
            System.out.println("You hit a mine! So close, better luck next time.");
        }
    }

    //EFFECTS: prints out the board solution
    private void printBoardSolutions() {
        for (Square square : this.boardInProgress.getAllSquares()) {
            square.showSquare();
        }
        System.out.println("");
        printBoard();
    }

    //MODIFIES: this.boardInProgress
    //EFFECTS: checks if game won
    private void checkIfGameWon() {
        if (isGameWon()) {
            this.boardInProgress.setGameWon();
        }
    }

    //EFFECTS: returns true if all squares on the board have been flipped except for the bombs
    private boolean isGameWon() {
        for (Square square : this.boardInProgress.getAllSquares()) {
            if (square.getIdentity() != Identity.BOMB && square.isIdentityHidden()) {
                return false;
            }
        }
        return true;
    }


    //TODO once we get more information about saving and loading
    //public void loadBoard() {
    //}

    //public void saveBoard() {
    //}

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
}
