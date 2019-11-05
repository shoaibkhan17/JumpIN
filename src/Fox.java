/**
 * Fox is is a sub-class of Piece class
 * Its movement is different than Rabbit since it requires two space in the board.
 * Fox can only move in the direction that it is facing. Foxes cannot not jump.
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class Fox extends Piece {
	
    /**
     * Foxes can be moved.
     */
    private static final boolean IS_MOVABLE = true;

    /**
     * Foxes can be selected.
     */
    private static final boolean IS_SELECTABLE = true;

	/**
	 * Location of the other body of the fox piece.
	 */
	private Location bodyLocation;

	/**
	 * Direction where the fox can move.
	 * Horizontal or vertical.
	 */
	private boolean horizontalMovement;

	/**
	 * If this is the tail of the fox.
	 */
	private boolean tail;

	/**
	 * Default constructor initializing instance variables
	 * @param bodyLocation initializes bodyLocation
	 * @param horizontalMovement initializes horizontalLocation
	 * @param tail initializes tail
	 */
	public Fox(Location bodyLocation, boolean horizontalMovement, boolean tail) {
		super(PieceType.FOX, IS_MOVABLE, IS_SELECTABLE);
		this.bodyLocation = new Location();
		this.bodyLocation.setX(bodyLocation.getX());
		this.bodyLocation.setY(bodyLocation.getY());
		this.horizontalMovement = horizontalMovement;
		this.tail = tail;
	}
	
	/**
	 * returns the tail
	 * @return tail
	 */
	public boolean isTail() {
		return tail;
	}
	
	/**
	 * checks for horizontal movement
	 * @return horizontalMovement if the movement is horizontal
	 */
	public boolean isHorizontal() {
		return horizontalMovement;
	}
	
	/**
	 * gets the location of the piece
	 * @return bodyLocation of the piece
	 */
	public Location getBodyLocation() {
		return bodyLocation;
	}

	/**
	 * To set the location of the other piece 
	 * @param bodyLocation the location of the x and y coordinate of the piece
	 */
	public void setOtherPieceLocation(Location bodyLocation) {
		this.bodyLocation.setX(bodyLocation.getX());
		this.bodyLocation.setY(bodyLocation.getY());
	}
	
	/**
	 * Method to check if the move is valid or not
	 * @param constNumber used for checking against the x value to see whether its a valid number
	 * @param x used for the boolean parameter in the squares array 
	 * @param number1 used to check the differences of the numbers
	 * @param number2 used to check the differences of the numbers
	 * @param squares squares array used to access the squares
	 * @return boolean true if it is valid, false if it is not valid
	 */
	private boolean checkValid(int constNumber, boolean x, int number1, int number2, Square[][] squares) {

		// Has to have no piece in the middle to move there. 
		boolean noPieceInMiddle = true;
		int diff = Math.abs(number1 - number2) - 1;
		int smallestNumber = number1 > number2 ? number2 : number1;

		// Go through all the spaces the fox has moved and check if there is a piece in the middle.
		// Goes through this condition if the move is greater than 1 square.
		if (diff >= 1) {
			for(int i = 0; i < diff; i++) {
				Piece piece = squares[x ? constNumber : (smallestNumber + i + 1)][x ? (smallestNumber + i + 1) : constNumber].getPiece();
				if (piece != null) {
					noPieceInMiddle = false;
				}
			}
			
			// Return if there is a piece in the middle. 
			return noPieceInMiddle;
		}

		// If the move is smaller than 1 square.
		else {

			// Get the piece. 
			Piece piece = squares[x ? constNumber : number2][x ? number2 : constNumber].getPiece();

			// Check if there is no piece at that location. 
			return piece == null;
		}
	}

	/**
	 * Function to move itself and the other body part of the fox to their new locations.
	 * @param oldLocationGreater used to check whether the old location is greater than the previous location
	 * @param oldLocation previous location of the fox
	 * @param newLocation used to set the new location of the fox
	 * @param board used to select the board and then move the piece 
	 * @return boolean returns true if the piece can be moved, else returns false if the piece cannot be moved
	 */
	private boolean moveItselfAndBody(boolean oldLocationGreater, Location oldLocation, Location newLocation, Board board) {
		Location tempLocation; 

		// If the old location was greater than the previous location
		if (oldLocationGreater) {
			
			// If the fox can only move in the horizontal location. 
			if (horizontalMovement) {
				tempLocation = new Location(newLocation.getX() + 1, newLocation.getY());
			}

			// If the fox can only move in the vertical location. 
			else {
				tempLocation = new Location(newLocation.getX(), newLocation.getY() + 1);
			}
		}

		// If the old location was smaller than the previous location. 
		else {

			// If the fox can only move in the horizontal location. 
			if (horizontalMovement) {
				tempLocation = new Location(newLocation.getX() - 1, newLocation.getY());
			}

			// If the fox can only move in the vertical location. 
			else {
				tempLocation = new Location(newLocation.getX(), newLocation.getY() - 1);
			}
		}

		// Create another fox with the new updated location. 
		Fox temp = new Fox(newLocation, horizontalMovement, !this.tail);

		// Move the fox to new location and remove it from the previous location.
		if (board.movePiece(oldLocation, newLocation, this)) {

			// Move the body part to the new location and remove it from the previous location.
			if (board.movePiece(bodyLocation, tempLocation, temp)) {

				// Update the fox's body part's new location. 
				this.bodyLocation = tempLocation;
				return true;
			}
		}

		return false;
	}

	/**
	 * Function that validates the movement and decides if the fox can be moved or not.
	 * -If a horizontal fox wants to move left, it needs to be moved by it's head.
	 * -if a horizontal fox wants to move right, it needs to be moved by it's tail.
	 * -If a vertical fox wants to move up, it needs to be moved by it's head.
	 * -if a vertical fox wants to move down, it needs to be moved by it's tail.
	 * 
	 * NOTE: Still need to fix that where if a tail was selected to move right, it 
	 * 		 should move the head and then the tail with it.
	 * @param oldLocation old location of the fox
	 * @param newLocation new location of the fox
	 * @param board instance of board used for setting the location of x and y to new location
	 * @return boolean returns true if the move is validated, else returns false if move is not validated
	 */
	private boolean moveValidation(Location oldLocation, Location newLocation, Board board) {
		boolean oldLocationGreater = oldLocation.comparesTo(newLocation, horizontalMovement) == 1;

		// If it is the head piece and the new location is smaller than tail's location. 
		if (!this.tail && newLocation.comparesTo(bodyLocation, horizontalMovement) == -1) {
			return this.moveItselfAndBody(oldLocationGreater, oldLocation, newLocation, board);
		}

		// If it is the tail piece and the new location is greater than head's location. 
		else if (this.tail && newLocation.comparesTo(bodyLocation, horizontalMovement) == 1) {
			return this.moveItselfAndBody(oldLocationGreater, oldLocation, newLocation, board);
		}

		// Need to check if that is not the case, then use the head/tail to move it. And then move the 
		// body part with it.

		return false;
	}

	/**
	 * Function to move the animal to a new location 
	 * @param oldLocation old location of the animal
	 * @param newLocation new location of the animal
	 * @param board instance of board used to move the animal to the new location
	 * @return boolean true if the move has been made, else false if the move could not be made
	 */
	
	public boolean move(Location oldLocation, Location newLocation, Board board) {
		Square[][] squares = board.getSquares();
		int x1 = oldLocation.getX();
		int y1 = oldLocation.getY();
		int x2 = newLocation.getX();
		int y2 = newLocation.getY();

		// If the new location is the same as the old location.
		if (oldLocation.equals(newLocation)) {
			return true;
		}

		// If the new location is the same as its body parts location. 
		else if (newLocation.equals(bodyLocation)) {
			return true;
		}

		// If it is a horizontal fox and it moved in a horizontal direction.
		else if (horizontalMovement && y1 == y2) {
			if (this.checkValid(y1, false, x1, x2, squares)) {
				return this.moveValidation(oldLocation, newLocation, board);
			}
		}

		// If it is a vertical fox and it moved in a vertical direction.
		else if (!horizontalMovement && x1 == x2) {
			if (this.checkValid(x1, true, y1, y2, squares)) {
				return this.moveValidation(oldLocation, newLocation, board);
			}
		}

		return false;
	}
}
