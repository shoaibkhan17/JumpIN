import java.util.*;
/**
 * Class that initializes the board of the game along with board size and squares
 * Checks whether each move is valid or not and allows the user to play the game by initializing the game
 * 
 */
public class Board {
	private Square[][] squares;
	private Piece selectedPiece;
	private Location selectedPieceLocation;
	//An array of the hole squares initialized along with the board
	private Square[] holeSquares;
	private static final int BOARD_SIZE = 5;
	private static final char BOARD_PRINT_CHAR = '*';

	// Default Constructor
	public Board(int level) {
		squares = new Square[BOARD_SIZE][BOARD_SIZE];
		selectedPiece = null;
		selectedPieceLocation = new Location(-1, -1);
		for (int x = 0; x < Board.BOARD_SIZE; x++) {
			for (int y = 0; y < Board.BOARD_SIZE; y++) {
				this.squares[x][y] = new Square();
			}
		}
		this.initBoard(level);
	}
	
	public void initBoard(int level) {
		// Create and add pieces into the board
		switch (level) {
			case 1:
				this.initToLevel1();
				break;

			case 2:
				this.initToLevel2();
				break;
		
			default:
				this.initToLevel1();
				break;
		}
	}

	public void initToLevel1() {
		squares[0][0] = (new Hole());
		squares[1][0].setPiece(new Mushroom());
		squares[2][0].setPiece(new Mushroom());
		squares[4][0] = (new Hole());
		squares[3][1].setPiece(new Mushroom());
		squares[2][2] = (new Hole());
		squares[3][2].setPiece(new Rabbit());
		squares[0][4] = (new Hole());
		squares[4][4] = (new Hole());
		
		holeSquares = new Square[5];
		holeSquares[0] = (squares[0][0]);
		holeSquares[1] = (squares[4][0]);
		holeSquares[2] = (squares[2][2]);
		holeSquares[3] = (squares[0][4]);
		holeSquares[4] = (squares[4][0]);
	}

	public void initToLevel2() {
		squares[0][0] = (new Hole());
		squares[1][0].setPiece(new Rabbit());
		squares[3][0].setPiece(new Mushroom());
		squares[4][0] = (new Hole());
		squares[2][1].setPiece(new Mushroom());
		squares[2][2] = (new Hole());
		squares[2][3].setPiece(new Mushroom());
		squares[0][4] = (new Hole());
		squares[2][4].setPiece(new Rabbit());
		squares[4][4] = (new Hole());
		
		holeSquares = new Square[5];
		holeSquares[0] = (squares[0][0]);
		holeSquares[1] = (squares[4][0]);
		holeSquares[2] = (squares[2][2]);
		holeSquares[3] = (squares[0][4]);
		holeSquares[4] = (squares[4][0]);
	}

	public boolean selectPiece(Location location) {
		int x = location.getX();
		int y = location.getY();

		// No piece is located at that spot or the piece cannot be moved. 
		if (squares[x][y].getPiece() == null || !squares[x][y].getPiece().isMovable()) {
			return false;
		}

		selectedPiece = squares[x][y].getPiece();
		selectedPieceLocation.setLocation(location);
		return true;
	}

	private void movePiece(Location oldLocation, Location newLocation, Piece piece) {
		int x1 = oldLocation.getX();
		int y1 = oldLocation.getY();
		int x2 = newLocation.getX();
		int y2 = newLocation.getY();

		squares[x1][y1].removePiece();
		squares[x2][y2].setPiece(piece);
	}

	// Generic move function
	public boolean move(Location location) {
		int x = location.getX();
		int y = location.getY();
		Piece piece = squares[x][y].getPiece();

		// TODO THIS LOGIC NEEDS TO BE CHANGED 
		// SPECIALLY FOR FOX
		// There is no piece located at that location
		// or the located piece is a hole.
		// or the piece is being place where it currently located.
		if (piece == null || piece.getType() == PieceType.HOLE || location.equals(selectedPieceLocation)) {
			this.movePiece(selectedPieceLocation, location, selectedPiece);
			selectedPiece = null;
			selectedPieceLocation.setLocation(new Location(-1, -1));;
			return true;
		}

		return false;
	}
	
	public Square[] getHoleSquares() {
		return holeSquares;
	}

	public String getBoardLine() {
		String boardLine = "\n  ";
        for (int i = 0; i < 21; i++) { 
            boardLine += Board.BOARD_PRINT_CHAR; 
		}
		boardLine += "\n"; 
		return boardLine;
	}

	public String toString() {
		String board = "\n    A   B   C   D   E";
		for (int y = 0; y < Board.BOARD_SIZE; y++) {
			board += this.getBoardLine();
			board += y + 1 + " ";
			for (int x = 0; x < Board.BOARD_SIZE; x++) {
				board += BOARD_PRINT_CHAR + " " + squares[x][y].toString() + " ";
			}
			board += Board.BOARD_PRINT_CHAR;
		}
		board += this.getBoardLine() + '\n';
		return board;
	}

	// Function to print the board. 
	public void printBoard() {
		System.out.print(this.toString());
	}
}