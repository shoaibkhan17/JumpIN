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

   
    public void initBoard() {
        // Create and add pecies into the board
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
    public String printBoardLine() {
        String boardLine = "\n  ";
        for (int i = 0; i < 21; i++) {
            boardLine += boardPrintChar;
        }
        return boardLine;
    }

    public String printBoard() {
        String board = "    A   B   C   D   E";
        for (int y = 0; y < Board.BOARD_SIZE; y++) {
            //this.printBoardLine();
        	board += printBoardLine() + "\n";
            board += (y + 1 + " ");
            for (int x = 0; x < Board.BOARD_SIZE; x++) {
                board += (boardPrintChar + " " + squares[x][y].toString() + " ");
            }
            board += (Board.boardPrintChar);
        }
        board += printBoardLine();
        return board;
    }

    // validates move
    public void moveValidator(String moveText, String invalidText) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;

        while (!valid) {
        	 System.out.println(this.printBoard());
            System.out.println(moveText);
            String input = scanner.nextLine();
            if (input.length() == 2) {
                valid = true;
            } else {
                System.out.println(invalidText);
            }
        }
//        scanner.close();
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
}
