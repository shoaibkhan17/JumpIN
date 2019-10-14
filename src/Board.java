import java.io.IOException;
import java.util.Scanner;

public class Board {
    private Square[][] squares;
    private static final int BOARD_SIZE = 5;
    private static final char boardPrintChar = '*';

    // Default Constructor
    public Board() {
        squares = new Square[BOARD_SIZE][BOARD_SIZE];
        for (int x = 0; x < Board.BOARD_SIZE; x++) {
            for (int y = 0; y < Board.BOARD_SIZE; y++) {
                this.squares[x][y] = new Square();
            }
        }
    }

    //initialize board
    public void initBoard() {
        // Create and add pieces into the board
        squares[0][0].setPiece(new Hole());
        squares[1][0].setPiece(new Mushroom());
        squares[2][0].setPiece(new Mushroom());
        squares[4][0].setPiece(new Hole());
        squares[3][1].setPiece(new Mushroom());
        squares[2][2].setPiece(new Hole());
        squares[3][2].setPiece(new Rabbit());
        squares[0][4].setPiece(new Hole());
        squares[4][4].setPiece(new Hole());
    }

    //prints out board 
    public void printBoardLine() {
        System.out.print("\n  ");
        for (int i = 0; i < 21; i++) {
            System.out.print(Board.boardPrintChar);
        }
        System.out.println();
    }

    public void printBoard() {
        System.out.print("    A   B   C   D   E");
        for (int y = 0; y < Board.BOARD_SIZE; y++) {
            this.printBoardLine();
            System.out.print(y + 1 + " ");
            for (int x = 0; x < Board.BOARD_SIZE; x++) {
                System.out.print(Board.boardPrintChar + " " + squares[x][y].printSquare() + " ");
            }
            System.out.print(Board.boardPrintChar);
        }
        this.printBoardLine();
        System.out.println();
    }

    // validates move
    public void moveValidator(String moveText, String invalidText) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;

        while (!valid) {
            this.printBoard();
            System.out.print(moveText);
            String input = scanner.nextLine();
            if (input.length() == 2) {
                valid = true;
            } else {
                System.out.println(invalidText);
            }
        }
        scanner.close();
    }

    
    public void moveText() {
        this.moveValidator("What peice would you like to move: ", "\nPlease enter a valid peice \n");

        // Some issues with the scanner currently.
        this.moveValidator("Where would you like to move this peice: ", "\nPlease enter a valid location \n");
    }


    public void playGame() {
        // TODO move this into a while loop
        this.initBoard();
        this.moveText();
    }

    /**
     * For testing
     */
    public static void main(String[] args) {
        System.out.println("In board class");
        System.out.println("---> Printing board <--- \n");

        Board testingBoard = new Board();
        testingBoard.playGame();
	}
}
