import java.io.Serializable;
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

public class Board implements Serializable {	
	
	/**
	 * Serial version UID for serialization and de-serialization 
	 */
	private static final long serialVersionUID = 2L;
	
	protected Square[][] squares;
	protected Animal selectedPiece;
	protected LinkedList<Location> holeLocations;
	protected int rabbitCount;
	protected MoveStack moveStack;
	protected MoveStack redoStack;
	private int currentLevel;
	private int turnsTaken;
	
	/**
	 * Default constructor with no params.
	 * Initializes the object.
	 */
	public Board() {
		this.init();
		this.initBoard(0);
	}
	
	/** 
	 * Constructor to initialize the instance variables
	 * @param level this is the level of the game
	 */
	public Board(int level) {
		this.init();
		currentLevel = level;
		
		// Sets the level of the game.
		this.initBoard(level);
	}
	
	/**
	 * Method to initialize the board.
	 */
	private void init() {
		squares = new Square[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
		holeLocations = new LinkedList<>();
		selectedPiece = null;
		rabbitCount = 0;
		turnsTaken = 0;
		moveStack = new MoveStack();
		redoStack = new MoveStack();
		
		for (int x = 0; x < Constants.BOARD_SIZE; x++) {
			for (int y = 0; y < Constants.BOARD_SIZE; y++) {
				this.squares[x][y] = new Square(new Location(x, y));
			}
		} 
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
	 * Method to change the level of the game.
	 * @param level this is the level of the game which is to be changed.
	 * @returns true if it a valid solvable level. 
	 */
	public boolean changeLevel(int level) {
		this.reinitialize();
		this.currentLevel = level;
		return this.initBoard(level);
	}

	/**
	 * Method to re-initialize the instance variables when changing level
	 */
	private void reinitialize() {
		// Default values.
		for (int x = 0; x < Constants.BOARD_SIZE; x++) {
			for (int y = 0; y < Constants.BOARD_SIZE; y++) {
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
		if (location.getX() >= Constants.BOARD_SIZE || location.getY() >= Constants.BOARD_SIZE) {
			return null;
		}
		return squares[location.getX()][location.getY()];
	}

	/**
	 * Initializes the game to the selected level
	 * @param level used to set the level of the game passed in as a parameter
	 * @returns true if it a valid solvable level. 
	 */
	private boolean initBoard(int level) {
		LevelBuilder levelBuilder = new LevelBuilder(level, this);
		boolean valid = levelBuilder.buildLevel();
		levelBuilder = null;
		return valid;
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
	
	/**
	 * Undo and redo method handler for the board object.
	 * @param newLocation
	 * @param animalPiece
	 * @param userMove
	 * @param redo
	 */
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
	 * Method to move the fox.
	 * @param newLocation the new location
	 * @param animalPiece the piece that is about to be moved
	 * @param userMove If it is a user move or an automated move
	 * @param redo if the move needs to be added to the redo stack 
	 */
	public void moveFox(Location newLocation, Animal animalPiece, boolean userMove, boolean redo) {
		Fox fox = (Fox) animalPiece;
		Location oldLoc = new Location(fox.getPieceLocation());
		this.removePiece(fox.getPieceLocation());
		Location oldBodyLoc = new Location(fox.getBodyLocation());
		Fox body = (Fox) squares[oldBodyLoc.getX()][oldBodyLoc.getY()].getPiece();
		this.removePiece(oldBodyLoc);
		String movementType = fox.calculatePieceLocation(newLocation, body);
		this.undoRedoHandler(movementType.equals("head") ? oldLoc : oldBodyLoc, fox, userMove, redo);
		Location foxLocation = new Location(fox.getPieceLocation());
		squares[foxLocation.getX()][foxLocation.getY()].setPiece(animalPiece);
		Location newBodyLoc = new Location(fox.getBodyLocation());
		squares[newBodyLoc.getX()][newBodyLoc.getY()].setPiece(body);
	}
	
	/**
	 * Method to move the rabbit.
	 * @param newLocation the new location
	 * @param animalPiece the piece that is about to be moved
	 * @param userMove If it is a user move or an automated move
	 * @param redo if the move needs to be added to the redo stack 
	 */
	public void moveRabbit(Location newLocation, Animal animalPiece, boolean userMove, boolean redo) {
		int x = newLocation.getX();
		int y = newLocation.getY();
		
		Piece locationPiece = squares[x][y].getPiece();
		
		if (locationPiece != null && locationPiece.getType() == PieceType.HOLE) {
			Hole hole = (Hole) locationPiece;
			this.undoRedoHandler(animalPiece.getPieceLocation(), animalPiece, userMove, redo);
			this.removePiece(animalPiece.getPieceLocation());
			animalPiece.setPieceLocation(newLocation);
			hole.setPiece(userMove ? animalPiece : animalPiece);
		}
		
		else {
			this.undoRedoHandler(animalPiece.getPieceLocation(), animalPiece, userMove, redo);
			this.removePiece(animalPiece.getPieceLocation());
			animalPiece.setPieceLocation(newLocation);
			squares[x][y].setPiece(animalPiece);
		}
	}
	
	/**
	 * Method that moves the piece from the initial location to the new location 
	 * @param oldLocation initial location of the piece to be moved
	 * @param newLocation new location of the piece
	 * @param piece piece that is moved
	 */
	public boolean movePiece(Location newLocation, Animal animalPiece, boolean userMove, boolean redo) {


		// If the new location is the same as the old location.
		if (animalPiece.getPieceLocation().equals(newLocation)) {
			return true;
		}
			
		switch (animalPiece.getType()) {
			case RABBIT:
				this.moveRabbit(newLocation, animalPiece, userMove, redo);
				return true;
				
			case FOX:
				this.moveFox(newLocation, animalPiece, userMove, redo);
				return true;
			
			default:
				return false;
		}
	}
	
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
			boardLine += Constants.BOARD_PRINT_CHAR;
		}

		boardLine += "\n";
		return boardLine;
	}
	
	/**
	 * Method returns a string representation of the board.
	 * @return board contains the board as a string representation
	 */
	@Override
	public String toString() {
		String board = "\n    A   B   C   D   E";
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			board += this.getBoardLine();
			board += y + 1 + " ";

			for (int x = 0; x < Constants.BOARD_SIZE; x++) {
				board += Constants.BOARD_PRINT_CHAR + " " + squares[x][y].toString() + " ";
			}

			board += Constants.BOARD_PRINT_CHAR;
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
	 * Method to get the board state
	 * @return boardState
	 */
	public String getBoardState() {
		String boardState = "";
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			for (int x = 0; x < Constants.BOARD_SIZE; x++) {
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
	
    /**
     * Method to generate the xml structure of the object
     */
    public String toXML() {
    	
    	String xml = "";
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			for (int x = 0; x < Constants.BOARD_SIZE; x++) {
				String internalXML = squares[x][y].toXML();
				if (!internalXML.equals("")) {
					xml += "\n" + internalXML;
				}
			}
		}
		return xml;
    }
}