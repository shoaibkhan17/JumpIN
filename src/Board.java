import java.util.ArrayList;

/**
 * Class that initializes the board of the game along with board size and
 * squares Checks whether each move is valid or not and allows the user to play
 * the game by initializing the game
 * 
 */
public class Board {
	private Square[][] squares;
	private Piece selectedPiece;
	private Location selectedPieceLocation;
	private ArrayList<Location> holeLocations; 
	private int rabbitCount;
	private static final int BOARD_SIZE = 5;
	private static final char BOARD_PRINT_CHAR = '*';

	/** Constructor to initialize the instance variables
	 * @param level
	 */
	public Board(int level) {
		squares = new Square[BOARD_SIZE][BOARD_SIZE];
		holeLocations = new ArrayList<>();
		selectedPiece = null;
		selectedPieceLocation = new Location();
		
		for (int x = 0; x < Board.BOARD_SIZE; x++) {
			for (int y = 0; y < Board.BOARD_SIZE; y++) {
				this.squares[x][y] = new Square();
			}
		}

		this.initBoard(level);

		// Hard coding the rabbit count.
		// So this is not required.
		//rabbitCount = countRabbits();
	}

	/** 
	 * @return squares
	 */
	public Square[][] getSquares() {
		return squares;
	}
	
	/**
	 * Method which creates and add pieces onto the board
	 * @param level of difficulty
	 */
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

	// Returns the number of rabbits in the board
	public int countRabbits() {
		int count = 0;
		for (int i = 0; i <BOARD_SIZE; i++) {
			for (int j = 0; i <BOARD_SIZE; i++) {
				if (squares[i][j].getPieceType() == PieceType.RABBIT) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Initialize the level1 of the game
	 */
	public void initToLevel1() {
		squares[0][0].setPiece(new Hole());
		squares[1][0].setPiece(new Mushroom());
		squares[2][0].setPiece(new Mushroom());
		squares[4][0].setPiece(new Hole());
		squares[3][1].setPiece(new Mushroom());
		squares[2][2].setPiece(new Hole());
		squares[3][0].setPiece(new Rabbit());
		squares[0][4].setPiece(new Hole());
		squares[4][4].setPiece(new Hole());
		squares[4][2].setPiece(new Rabbit());

		holeLocations.add(new Location(0, 0));
		holeLocations.add(new Location(4, 0));
		holeLocations.add(new Location(2, 2));
		holeLocations.add(new Location(0, 4));
		holeLocations.add(new Location(4, 4));
		rabbitCount = 2;
		
	}

	/**
	 * Initialize the level2 of the game
	 */
	public void initToLevel2() {
		squares[0][0].setPiece(new Hole());
		squares[3][0].setPiece(new Mushroom());
		squares[4][0].setPiece(new Hole());
		squares[1][1].setPiece(new Mushroom());
		squares[2][1].setPiece(new Rabbit());

		// Add foxes here
		// squares[3][1].setPiece(new Fox());
		// squares[4][1].setPiece(new Fox());

		squares[2][2].setPiece(new Mushroom());
		squares[0][4].setPiece(new Hole());
		squares[4][4].setPiece(new Hole());

		holeLocations.add(new Location(0, 0));
		holeLocations.add(new Location(4, 0));
		holeLocations.add(new Location(0, 4));
		holeLocations.add(new Location(4, 4));
		
		rabbitCount = 1;
	}

	/**
	 * Removes the piece from a certain location
	 * @param location of the piece
	 */
	public void removePiece(Location location) {
		int x = location.getX();
		int y = location.getY();

		if (squares[x][y].hasPiece()) {
			
			Piece piece = squares[x][y].getPiece();
			if (piece.getType() == PieceType.HOLE) {
				Hole hole = (Hole) piece;
				hole.removePiece();
			}

			else {
				squares[x][y].removePiece();
			}

			selectedPiece = null;
			selectedPieceLocation.clear();
		}
	}

	/**
	 * Selects the piece 
	 * @param location of the piece
	 * @return true if the condition is satisfied
	 */
	public boolean selectPiece(Location location) {
		int x = location.getX();
		int y = location.getY();
		Piece piece = squares[x][y].getPiece();
		
		// No piece is located at that spot
		if (piece == null) {
			return false;
		}

		// If the selected piece is a hole.
		else if (piece.getType() == PieceType.HOLE) {
			Hole hole = (Hole) piece;

			if (hole.isOccupied()) {
				selectedPiece = hole.getPiece();
				selectedPieceLocation.setLocation(location);
				return true;
			}

			return false;
		}

		// if the piece cannot be moved. 
		else if (!piece.isMovable()) {
			return false;
		}

		selectedPiece = squares[x][y].getPiece();
		selectedPieceLocation.setLocation(location);
		return true;
	}

	/**
	 * checks if the piece can move or not
	 * @param oldLocation
	 * @param newLocation
	 * @param piece
	 * @return false if the condition is not satisfied
	 */
	private boolean canMove(Location oldLocation, Location newLocation, Piece piece) {

		Animal animal = (Animal) selectedPiece;
		return animal.move(oldLocation, newLocation, this);
	}

	/**
	 * Moving the pieces from old location to a new location
	 * @param oldLocation
	 * @param newLocation
	 * @param piece
	 */
	public void movePiece(Location oldLocation, Location newLocation, Piece piece) {
		int x = newLocation.getX();
		int y = newLocation.getY();
		Piece locationPiece = squares[x][y].getPiece();

		if (locationPiece == null || locationPiece == piece) {
			squares[x][y].setPiece(piece);
			this.removePiece(oldLocation);
		}

		else if (locationPiece.getType() == PieceType.HOLE) {
			Hole hole = (Hole) locationPiece;
			if (!hole.isOccupied()) {
				// Add the piece in the hole.
				hole.setPiece(selectedPiece);
				this.removePiece(oldLocation);
			}
		}
	}
	/**
	 * selects the location of piece, make that location equal to null and clears the location
	 * @param location
	 * @return false if the condition is not satisfied
	 */
	public boolean move(Location location) {
		if (this.canMove(selectedPieceLocation, location, selectedPiece)) {
			selectedPiece = null;
			selectedPieceLocation.clear();
			return true;
		}

		return false;
	}

	/**
	 * Method gets the board line
	 * @return the board line
	 */
	public String getBoardLine() {
		String boardLine = "\n  ";
        for (int i = 0; i < 21; i++) { 
            boardLine += Board.BOARD_PRINT_CHAR; 
		}

		boardLine += "\n"; 
		return boardLine;
	}

	/**
	 * method returns a string representation of the object.
	 *@return board
	 */
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

	/**
	 * Gets the status of the game and prints the text
	 */
	public void getHoleStatus() {
		for (Location holeLocation: holeLocations) {
			Hole hole = (Hole) squares[holeLocation.getX()][holeLocation.getY()].getPiece();
			String text = holeLocation.toString();
			text += " " + hole.getStatus();
			System.out.println(text);
		}

		System.out.println();
	}
	/**
	 * isGameWon checks all the holes on the board. If the number of rabbits in the game (rabbitCount)
	 * is equal to the number of rabbits in the holes
	 * the game is won and the method returns true
	 * @return status of the game won or lost
	 */
	public boolean isGameWon() {
		int count = 0;
		for (Location holeLocation: holeLocations) {
			Hole hole = (Hole) squares[holeLocation.getX()][holeLocation.getY()].getPiece();
			if(hole.isOccupied() && hole.getPiece().getType() == PieceType.RABBIT) {
				count++;
			}
		}
		return count == rabbitCount;
	}
	/**
	 * Prints the board
	 */
	public void printBoard() {
		if (selectedPiece != null) {
			System.out.println("-- SELECTED PIECE " + selectedPiece + " at " + selectedPieceLocation + " --");
		}
		System.out.print(this.toString());
		this.getHoleStatus();
	}
}