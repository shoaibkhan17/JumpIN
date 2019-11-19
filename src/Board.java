import java.util.LinkedList;

/**
 * Class that initializes the board of the game Handles movement of Piece within
 * the Squares Checks if the game is completed
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class Board {
	protected Square[][] squares;
	protected Animal selectedPiece;
	protected LinkedList<Location> holeLocations;
	protected int rabbitCount;
	protected static final int BOARD_SIZE = 5;
	protected static final char BOARD_PRINT_CHAR = '*';
	protected static final int TOTAL_LEVELS = 5;
	protected MoveStack moveStack;
	protected MoveStack redoStack;
	private int currentLevel;
	private int turnsTaken;
	
	/** 
	 * Constructor to initialize the instance variables
	 * @param level this is the level of the game
	 */
	public Board(int level) {

		squares = new Square[BOARD_SIZE][BOARD_SIZE];
		holeLocations = new LinkedList<>();
		selectedPiece = null;
		rabbitCount = 0;
		turnsTaken = 0;
		moveStack = new MoveStack();
		redoStack = new MoveStack();
		this.currentLevel = level;
		
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
	 * Method to get the current level of the board.
	 * @return currentLevel (int)
	 */
	public int getLevel() {
		return currentLevel;
	}
	
	/**
	 * Method to get the turns taken to solve the level.
	 * @return turnsTaken (int)
	 */
	public int getTurnsTaken() {
		return turnsTaken;
	}
	
	/**
	 * Method to change the level of the game
	 * @param level this is the level of the game which is to be changed
	 */
	public void changeLevel(int level) {
		this.reinitialize();
		this.currentLevel = level;
		this.initBoard(level);
	}

	/**
	 * Method to re-initialize the instance variables when changing level
	 */
	private void reinitialize() {
		// Default values.
		for (int x = 0; x < Board.BOARD_SIZE; x++) {
			for (int y = 0; y < Board.BOARD_SIZE; y++) {
				this.squares[x][y].setPiece(null);
			}
		}

		selectedPiece = null;
		rabbitCount = 0;
		turnsTaken = 0;
		holeLocations.clear();
		moveStack.popAll();
		redoStack.popAll();
	}
	
	/**
	 * Method to access the squares
	 * @return squares the array to be returned
	 */
	public Square[][] getSquares() {
		return this.squares;
	}
	
	/**
	 * Method to get a square at a particular location
	 * @param location at which the x and y coordinates are accessed
	 * @return squares at the location
	 */

	public Square getSquareAtLocation(Location location) {
		if (location.getX() >= BOARD_SIZE || location.getY() >= BOARD_SIZE) {
			return null;
		}
		return squares[location.getX()][location.getY()];
	}

	/**
	 * Initializes the game to the selected level
	 * @param level used to set the level of the game passed in as a parameter
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

		case 3:
			this.initToLevel3();
			break;

		case 4:
			this.initToLevel4();
			break;

		case 5:
			this.initToLevel5();
			break;

		default:
			this.initToLevel1();
			break;
		}
	}

	/**
	 * Initialize the level 1 of the game Method which creates and add pieces onto the board
	 */
	private void initToLevel1() {
		// Create and add pieces.
		squares[0][0].setPiece(new Hole());
		squares[4][0].setPiece(new Hole());
		squares[4][1].setPiece(new Mushroom());
		squares[2][2].setPiece(new Hole());
		squares[0][2].setPiece(new Rabbit(Rabbit.RABBIT_COLORS.Gray, new Location(0, 2)));
		squares[0][3].setPiece(new Mushroom());
		squares[0][4].setPiece(new Hole());
		squares[4][4].setPiece(new Hole());

		// Store the hold locations.
		holeLocations.add(new Location(0, 0));
		holeLocations.add(new Location(4, 0));
		holeLocations.add(new Location(2, 2));
		holeLocations.add(new Location(0, 4));
		holeLocations.add(new Location(4, 4));

		// Store the number of rabbits.
		rabbitCount = 1;
	}
	
	/**
	 * Initialize the level 2 of the game Method which creates and add pieces onto the board
	 */
	private void initToLevel2() {
		// Create and add pieces.
		squares[0][0].setPiece(new Hole());
		squares[4][0].setPiece(new Hole());
		squares[4][1].setPiece(new Mushroom());
		squares[4][2].setPiece(new Mushroom());
		squares[2][2].setPiece(new Hole());
		squares[2][3].setPiece(new Rabbit(Rabbit.RABBIT_COLORS.White, new Location(2, 3)));
		squares[3][3].setPiece(new Mushroom());
		squares[0][4].setPiece(new Hole());
		squares[4][4].setPiece(new Hole());

		// Store the hold locations.
		holeLocations.add(new Location(0, 0));
		holeLocations.add(new Location(4, 0));
		holeLocations.add(new Location(2, 2));
		holeLocations.add(new Location(0, 4));
		holeLocations.add(new Location(4, 4));

		// Store the number of rabbits.
		rabbitCount = 1;
	}

	/**
	 * Initialize the level 3 of the game Method which creates and add pieces onto the board
	 */
	private void initToLevel3() {		
		// Create and add pieces.
		squares[0][0].setPiece(new Hole());
		squares[1][0].setPiece(new Mushroom());
		squares[2][0].setPiece(new Mushroom());
		squares[4][0].setPiece(new Hole());
		squares[3][1].setPiece(new Mushroom());
		squares[2][2].setPiece(new Hole());
		squares[3][0].setPiece(new Rabbit(Rabbit.RABBIT_COLORS.White, new Location(3, 0)));
		squares[0][4].setPiece(new Hole());
		squares[4][4].setPiece(new Hole());
		squares[4][2].setPiece(new Rabbit(Rabbit.RABBIT_COLORS.Brown, new Location(4, 2)));

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
	 * Initialize the level 4 of the game Method which creates and add pieces onto the board
	 */
	private void initToLevel4() {
		// Create and add pieces.
		squares[0][0].setPiece(new Hole());
		squares[3][4].setPiece(new Rabbit(Rabbit.RABBIT_COLORS.Gray, new Location(3, 4)));
		squares[4][0].setPiece(new Hole());
		squares[0][1].setPiece(new Mushroom());
		squares[0][2].setPiece(new Mushroom());
		squares[2][2].setPiece(new Hole());
		squares[1][0].setPiece(new Fox(new Location(1, 0), new Location(1, 1), false, false));
		squares[2][4].setPiece(new Mushroom());
		squares[0][4].setPiece(new Hole());
		squares[1][1].setPiece(new Fox(new Location(1, 1), new Location(1, 0), false, true));
		squares[4][4].setPiece(new Hole());

		// Store the hold locations.
		holeLocations.add(new Location(0, 0));
		holeLocations.add(new Location(4, 0));
		holeLocations.add(new Location(2, 2));
		holeLocations.add(new Location(0, 4));
		holeLocations.add(new Location(4, 4));

		// Store the number of rabbits.
		rabbitCount = 1;
	}
	
	/**
	 * Initialize the level 5 of the game Method which creates and add pieces onto the board
	 */
	private void initToLevel5() {
		// Create and add pieces.
		squares[0][0].setPiece(new Hole());
		squares[3][0].setPiece(new Rabbit(Rabbit.RABBIT_COLORS.White, new Location(3, 0)));
		squares[4][0].setPiece(new Mushroom());
		squares[0][1].setPiece(new Fox(new Location(0, 1), new Location(1, 1), true, false));
		squares[1][1].setPiece(new Fox(new Location(1, 1), new Location(0, 1), true, true));
		squares[2][2].setPiece(new Hole());
		squares[3][2].setPiece(new Mushroom());
		squares[1][3].setPiece(new Fox(new Location(1, 3), new Location(1, 4), false, false));
		squares[0][4].setPiece(new Hole());
		squares[1][4].setPiece(new Fox(new Location(1, 4), new Location(1, 3), false, true));
		squares[2][4].setPiece(new Mushroom());
		squares[4][4].setPiece(new Hole());

		// Store the hold locations.
		holeLocations.add(new Location(0, 0));
		holeLocations.add(new Location(2, 2));
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

			// Clear out the selected piece
			selectedPiece = null;
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
				return true;
			}

			return false;
		}

		// if the piece cannot be moved.
		else if (!piece.isMovable()) {
			return false;
		}

		// get the selected piece from location x, y
		selectedPiece = (Animal) squares[x][y].getPiece();

		return true;
	}
	
	/**
	 * checks if the piece can move or not 
	 * @param oldLocation initial location of the piece
	 * @param newLocation new location of the piece to be moved to
	 * @param piece this is the piece that is to be moved
	 * @return true if the piece can be moved to be new location, return false if it can't be moved
	 */
	protected boolean canMove(Location newLocation, Animal animalPiece) {
		// If the new location is the same as the old location.
		if (animalPiece.getPieceLocation().equals(newLocation)) {
			return true;
		}
		
		return animalPiece.canMove(newLocation, squares);
	}
	
	private void undoRedoHandler(Location newLocation, Animal animalPiece, boolean userMove, boolean redo) {
		if (userMove) {
			// Clear the redo stack if a move was made between an undo and a redo.
			// Clearing the stack, to prevent redoing to an invalid location.
			if (!redoStack.isEmpty()) {
				redoStack.popAll();
			}
			moveStack.push(newLocation, animalPiece);
		}
		
		else if (!redo) {
			moveStack.push(newLocation, animalPiece);
		}
		
		else {
			redoStack.push(newLocation, animalPiece);
		}
	}
	
	/**
	 * Method that moves the piece from the initial location to the new location 
	 * @param oldLocation initial location of the piece to be moved
	 * @param newLocation new location of the piece
	 * @param piece piece that is moved
	 */
	public boolean movePiece(Location newLocation, Animal animalPiece, boolean userMove, boolean redo) {
		int x = newLocation.getX();
		int y = newLocation.getY();

		// If the new location is the same as the old location.
		if (animalPiece.getPieceLocation().equals(newLocation)) {
			return true;
		}
			
		switch (animalPiece.getType()) {
		case RABBIT:
			Piece locationPiece = squares[x][y].getPiece();
			if (locationPiece != null && locationPiece.getType() == PieceType.HOLE) {
				Hole hole = (Hole) locationPiece;
				this.undoRedoHandler(animalPiece.getPieceLocation(), animalPiece, userMove, redo);
				this.removePiece(animalPiece.getPieceLocation());
				animalPiece.setPieceLocation(newLocation);
				hole.setPiece(userMove ? animalPiece : animalPiece);
				return true;
			}
			
			else {
				this.undoRedoHandler(animalPiece.getPieceLocation(), animalPiece, userMove, redo);
				this.removePiece(animalPiece.getPieceLocation());
				animalPiece.setPieceLocation(newLocation);
				squares[x][y].setPiece(animalPiece);
				return true;
			}
			
		case FOX:
			Fox fox = (Fox) animalPiece;
			Location oldLoc = new Location(fox.getPieceLocation());
			this.removePiece(fox.getPieceLocation());
			Location oldBodyLoc = new Location(fox.getBodyLocation());
			Fox body = (Fox) squares[oldBodyLoc.getX()][oldBodyLoc.getY()].getPiece();
			this.removePiece(oldBodyLoc);
			String movementType = fox.calcaulePieceLocation(newLocation, body);
			this.undoRedoHandler(movementType.equals("head") ? oldLoc : oldBodyLoc, fox, userMove, redo);
			Location foxLocation = new Location(fox.getPieceLocation());
			squares[foxLocation.getX()][foxLocation.getY()].setPiece(animalPiece);
			Location newBodyLoc = new Location(fox.getBodyLocation());
			squares[newBodyLoc.getX()][newBodyLoc.getY()].setPiece(body);
			return true;
		
		default:
			return false;
		}
		
		
//
//		// If the location where is piece is about to moved is empty or it is same
//		// location.
//		if (locationPiece == null || locationPiece == piece) {
//			if (userMove) {
//				// Clear the redo stack if a move was made between an undo and a redo.
//				// Clearing the stack, to prevent redoing to an invalid location.
//				if (!redoStack.isEmpty()) {
//					redoStack.popAll();
//				}
//				moveStack.push(oldLocation, newLocation, piece);
//			}
//			
//			else if (!redo) {
//				moveStack.push(oldLocation, newLocation, piece);
//			}
//			
//			else {
//				redoStack.push(oldLocation, newLocation, piece);
//			}
//			squares[x][y].setPiece(piece);
//			this.removePiece(oldLocation);
//			return true;
//		}
//
//		// If the location where is piece is about to moved a hole and the moving piece
//		// is a rabbit.
//		else if (locationPiece.getType() == PieceType.HOLE && piece.getType() == PieceType.RABBIT) {
//			Hole hole = (Hole) locationPiece;
//			if (!hole.isOccupied()) {
//				if (userMove) {
//					// Clear the redo stack if a move was made between an undo and a redo.
//					// Clearing the stack, to prevent redoing to an invalid location.
//					if (!redoStack.isEmpty()) {
//						redoStack.popAll();
//					}
//					moveStack.push(oldLocation, newLocation, piece);
//				}
//				
//				else if (!redo) {
//					moveStack.push(oldLocation, newLocation, piece);
//				}
//				
//				else {
//					redoStack.push(oldLocation, newLocation, piece);
//				}
//				// Add the piece in the hole.
//				hole.setPiece(userMove ? selectedPiece : piece);
//				this.removePiece(oldLocation);
//				return true;
//			}
//			return false;
//		}
//
//		else {
//			return false;
//		}
	}
	
//	public boolean canMovePiece(Location newLocation, Piece piece) {
//		int x = newLocation.getX();
//		int y = newLocation.getY();
//		Piece locationPiece = squares[x][y].getPiece();
//
//		// If the location where is piece is about to moved is empty or it is same
//		// location.
//		if (locationPiece == null || locationPiece == piece) {
//			return true;
//		}
//
//		// If the location where is piece is about to moved a hole and the moving piece
//		// is a rabbit.
//		else if (locationPiece.getType() == PieceType.HOLE && piece.getType() == PieceType.RABBIT) {
//			Hole hole = (Hole) locationPiece;
//			if (!hole.isOccupied()) {
//
//				return true;
//			}
//			return false;
//		}
//
//		else {
//			return false;
//		}
//	}
	
	/**
	 * Calls the canMove() method sets the old location of the piece to null once
	 * the piece has moved to the new location and clears the selected piece
	 * location
	 * @param location on the board
	 * @return true if the operation is successful, else returns false if not successful
	 */
	public boolean move(Location newLocation) {
		if (this.canMove(newLocation, selectedPiece)) {
			if (this.movePiece(newLocation, selectedPiece, true, false)) {
				selectedPiece = null;
				turnsTaken++;
				return true;
			}	
		}
		return false;
	}
	
	/**
	 * Function to undo a move.
	 */
	public void undo() {
		Move move = moveStack.pop();
		if (move == null) {
			System.out.println("No moves were made to undo");
			return;
		}
		
		Location newLocation = move.getNewLocation();
		Animal animalPiece = move.getPiece();
		this.movePiece(newLocation, animalPiece, false, true);
	}
	
	/**
	 * Function to redo a move.
	 */
	public void redo() {
		Move move = redoStack.pop();
		if (move == null) {
			System.out.println("No moves were undoed to redo");
			return;
		}
		
		Location newLocation = move.getNewLocation();
		Animal animalPiece = move.getPiece();
		this.movePiece(newLocation, animalPiece, false, false);
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
		for (Location holeLocation : holeLocations) {
			Hole hole = (Hole) squares[holeLocation.getX()][holeLocation.getY()].getPiece();
			String text = holeLocation.toString();
			text += " " + hole.getStatus();
			System.out.println(text);
		}

		System.out.println();
	}
	
	/**
	 * ADD JAVA DOC
	 * @return
	 */
	public String getBoardState() {
		String boardState = "";
		for (int y = 0; y < Board.BOARD_SIZE; y++) {
			for (int x = 0; x < Board.BOARD_SIZE; x++) {
				boardState += squares[x][y].getPiece() == null ? "E" : squares[x][y];
			}
		}
		
		return boardState;
	}
	
	/**
	 * isGameWon checks all the holes on the board. If the number of rabbits in the
	 * game (rabbitCount) is equal to the number of rabbits in the holes the game is
	 * won and the method returns true
	 * 
	 * @return status of the game won or lost
	 */
	public boolean isGameWon() {
		int count = 0;
		for (Location holeLocation : holeLocations) {
			Hole hole = (Hole) squares[holeLocation.getX()][holeLocation.getY()].getPiece();
			if (hole.isOccupied() && hole.getPiece().getType() == PieceType.RABBIT) {
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
			System.out.println("-- SELECTED PIECE " + selectedPiece + " at " + selectedPiece.getPieceLocation() + " --");
		}
		System.out.print(this.toString());
		this.getHoleStatus();
	}
}