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

	public String toString() {
		String board = "    A   B   C   D   E";
		for (int y = 0; y < Board.BOARD_SIZE; y++) {
			// this.printBoardLine();
			board += boardLine() + "\n";
			board += (y + 1 + " ");
			for (int x = 0; x < Board.BOARD_SIZE; x++) {
				board += (boardPrintChar + " " + squares[x][y].toString() + " ");
			}
			board += (Board.boardPrintChar);
		}
		board += boardLine();
		return board;
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
}