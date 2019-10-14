import java.util.Scanner;

/**
 * Class that initializes the board of the game along with board size and squares
 * Checks whether each move is valid or not and allows the user to play the game by initializing the game
 * 
 */
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

	// prints out board
	public String boardLine() {
		String boardLine = "\n  ";
		for (int i = 0; i < 21; i++) {
			boardLine += boardPrintChar;
		}
		return boardLine;
	}

    // Prints out the entire board's state
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

    /**
     * 
     * @param moveText
     * @param invalidText
     */
    public void moveValidator(String moveText, String invalidText) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;

        String input="";
        while (!valid) {
            this.printBoard();
            System.out.print(moveText);
            input = scanner.nextLine();
            if (input.length() == 2) {
                valid = true;
            } else {
                System.out.println(invalidText);
            }
        }
        
        char firstChar = input.charAt(0); //gets the first character of the input that the user inputs
        int secondNum = input.indexOf(1); //gets the second number of the input
    }
    
	public int charToInt(char c) {
		switch (c) {

		case 'A':
			return 0;
		case 'B':
			return 1;
		case 'C':
			return 2;
		case 'D':
			return 3;
		case 'E':
			return 4;

		default:
			break;
		}

		return 0;
	}

    /**
     * Allows the user to enter which piece they would like to move and the location
     */
    public void moveText() {
        moveValidator("What piece would you like to move: ", "\nPlease enter a valid piece \n");

        // Some issues with the scanner currently.
        moveValidator("Where would you like to move this piece: ", "\nPlease enter a valid location \n");
    }

    /**
     * Method that allows the user to play the game
     */
    public void playGame() {
        // TODO move this into a while loop
        this.initBoard();
        this.moveText();
    }

    /**
     * Main method for testing the game
     */
    public static void main(String[] args) {
        System.out.println("In board class");
        System.out.println("---> Printing board <--- \n");

        Board testingBoard = new Board();
        testingBoard.playGame();
	}
}