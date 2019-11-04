import java.util.LinkedList;

/**
 * Class that initializes the board of the game.
 * Handles movement of Piece within the Squares.
 * Checks if the game is completed.
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class Board {
	protected Square[][] squares;
	protected Piece selectedPiece;
	protected Location selectedPieceLocation;
	protected LinkedList<Location> holeLocations; 
	protected int rabbitCount;
	protected static final int BOARD_SIZE = 5;
	protected static final char BOARD_PRINT_CHAR = '*';

	/** 
	 * Constructor to initialize the instance variables
	 * @param level this is the level of the game
	 */
	public Board(int level) {

		// Default values.
		squares = new Square[BOARD_SIZE][BOARD_SIZE];
		holeLocations = new LinkedList<>();
		selectedPiece = null;
		selectedPieceLocation = new Location();
		rabbitCount = 0;
		
		// Initializes the Squares.
		for (int x = 0; x < Board.BOARD_SIZE; x++) {
			for (int y = 0; y < Board.BOARD_SIZE; y++) {
				this.squares[x][y] = new Square(new Location(x, y));
			}
		}

		// Sets the level of the game.
		this.initBoard(level); 
	}

	/**
	 * method to access the squares.
	 * @return squares 
	 */
	public Square[][] getSquares() {
		return this.squares;
	}
	
	public Square getSquareAtLocation(Location location) {
		if (location.getX() >= BOARD_SIZE || location.getY() >= BOARD_SIZE) {
			return null;
		}
		return squares[location.getX()][location.getY()];
	}
	
	/**
	 * Initializes game to selected level. 
	 * @param level this is the level of difficulty of the game
	 */
	private void initBoard(int level) {
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

	/**
	 * Initialize the level 1 of the game.
	 * Method which creates and add pieces onto the board.
	 */
	private void initToLevel1() {
		// Create and add pieces. 
		squares[0][0].setPiece(new Hole());
		squares[1][0].setPiece(new Mushroom());
		squares[2][0].setPiece(new Mushroom());
		squares[4][0].setPiece(new Hole());
		squares[3][1].setPiece(new Mushroom());
		squares[2][2].setPiece(new Hole());
		squares[3][0].setPiece(new Rabbit(Rabbit.RABBIT_COLORS.White));
		squares[0][4].setPiece(new Hole());
		squares[4][4].setPiece(new Hole());
		squares[4][2].setPiece(new Rabbit(Rabbit.RABBIT_COLORS.Brown));

		// Store the hold locations.
		holeLocations.add(new Location(0, 0));
		holeLocations.add(new Location(4, 0));
		holeLocations.add(new Location(2, 2));
		holeLocations.add(new Location(0, 4));
		holeLocations.add(new Location(4, 4));

		// Store the number of rabbits.
		rabbitCount = 2;
	}

	/**
	 * Initialize the level 2 of the game.
	 * Method which creates and add pieces onto the board.
	 */
	private void initToLevel2() {
		// Create and add pieces. 
		squares[0][0].setPiece(new Hole());
		squares[3][0].setPiece(new Mushroom());
		squares[4][0].setPiece(new Hole());
		squares[1][1].setPiece(new Mushroom());
		squares[2][1].setPiece(new Rabbit(Rabbit.RABBIT_COLORS.Gray));
		squares[3][1].setPiece(new Fox(new Location(4, 1), true, false));
		squares[4][1].setPiece(new Fox(new Location(3, 1), true, true));
		squares[2][2].setPiece(new Mushroom());
		squares[0][4].setPiece(new Hole());
		squares[4][4].setPiece(new Hole());

		// Store the hold locations.
		holeLocations.add(new Location(0, 0));
		holeLocations.add(new Location(4, 0));
		holeLocations.add(new Location(0, 4));
		holeLocations.add(new Location(4, 4));
		
		// Store the number of rabbits.
		rabbitCount = 1;
	}


	/**
	 * Method that removes a piece from the given location.
	 * @param location of the piece
	 */
	public void removePiece(Location location) {
		int x = location.getX();
		int y = location.getY();

		// If there is a piece at that location.
		if (squares[x][y].hasPiece()) {
			
			// Get the piece.
			Piece piece = squares[x][y].getPiece();

			// If the piece is a hole. 
			if (piece.getType() == PieceType.HOLE) {
				Hole hole = (Hole) piece;

				// Remove the piece from the hole.
				hole.removePiece();
			}

			// Otherwise, just remove the piece.
			else {
				squares[x][y].removePiece();
			}

			// Clear out the selected piece and its location.
			selectedPiece = null;
			selectedPieceLocation.clear();
		}
	}

	/**
	 * Method that selects the piece in order to perform operations on it
	 * @param location of the piece
	 * @return true if the piece can be selected/valid, returns false if not valid location selected
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

			// If the hole is occupied by another piece.
			if (hole.isOccupied()) {

				// Make that piece as the selected piece and store its location.
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

		// get the selected piece from location x, y
		selectedPiece = squares[x][y].getPiece();
		selectedPieceLocation.setLocation(location);

		return true;
	}

	/**
	 * checks if the piece can move or not
	 * @param oldLocation initial location of the piece 
	 * @param newLocation new location of the piece to be moved to
	 * @param piece this is the piece that is to be moved
	 * @return true if the piece can be moved to be new location, return false if it can't be moved
	 */
	private boolean canMove(Location oldLocation, Location newLocation, Piece piece) {
		if (selectedPiece.getType() == PieceType.RABBIT) {
			Rabbit rabbit = (Rabbit) selectedPiece;
			return rabbit.move(oldLocation, newLocation, this);
		}
		
		else if (selectedPiece.getType() == PieceType.FOX) {
			Fox fox = (Fox) selectedPiece;
			return fox.move(oldLocation, newLocation, this);
		}
		
		return false;
	}

	/**
	 * Method that moves the piece from the initial location to the new location
	 * @param oldLocation initial location of the piece to be moved
	 * @param newLocation new location of the piece
	 * @param piece piece that is moved
	 */
	public boolean movePiece(Location oldLocation, Location newLocation, Piece piece) {
		int x = newLocation.getX();
		int y = newLocation.getY();
		Piece locationPiece = squares[x][y].getPiece();

		// If the location where is piece is about to moved is empty or it is same location.
		if (locationPiece == null || locationPiece == piece) {
			squares[x][y].setPiece(piece);
			this.removePiece(oldLocation);
			return true;
		}

		// If the location where is piece is about to moved a hole and the moving piece is a rabbit.
		else if (locationPiece.getType() == PieceType.HOLE && piece.getType() == PieceType.RABBIT) {
			Hole hole = (Hole) locationPiece;
			if (!hole.isOccupied()) {
				// Add the piece in the hole.
				hole.setPiece(selectedPiece);
				this.removePiece(oldLocation);
				return true;
			}
			return false;
		}

		else {
			return false;
		}
	}

	/**
	 * Calls the canMove() method
	 * sets the old location of the piece to null once the piece has moved to the new location
	 * and clears the selected piece location
	 * @param location on the board 
	 * @return true if the operation is successful, else returns false if not successful
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
	 * Gets the board line 
	 * @return String board line
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
	 * Method returns a string representation of the board.
	 * @return board contains the board as a string representation
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
	 * Gets the status of the hole and prints the text
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
		

		// Return true if all rabbits are in the hole.
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