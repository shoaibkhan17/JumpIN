/**
 * Rabbit is subclass of Piece class.
 * Rabbits can only jump if there is/are obstacles on their path.
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class Rabbit extends Animal {
	
	public static enum RABBIT_COLORS {Gray, White, Brown}
	
	protected RABBIT_COLORS rabbitColor;

	/**
	 * Default constructor
	 */
    public Rabbit(RABBIT_COLORS rabbitColor, Location pieceLocation) {
        super(PieceType.RABBIT, pieceLocation);
        this.rabbitColor = rabbitColor;
    }

//	public void setPieceLocation(Location newLocation) {
//
//	}

	/**
	 * Method that checks whether the move made by the Rabbit is valid or not
	 * @param constNumber used for checking against the x value to see whether its a valid number
	 * @param x used for the boolean parameter in the squares array 
	 * @param number1 used to check the differences of the numbers
	 * @param number2 used to check the differences of the numbers
	 * @param squares squares array used to access the squares
	 * @return boolean true if the move is valid, false if the move is not valid
	 */
	private boolean checkValid(int constNumber, boolean x, int number1, int number2, Square[][] squares) {
		
		// Has to have a piece or pieces in the middle to move there. 
		boolean pieceInMiddle = true;
		int diff = Math.abs(number1 - number2);
		int smallestNumber = number1 > number2 ? number2 : number1;
		
		// Go through all the spaces the rabbit has moved and check if there is a piece there.
		if (diff > 1) {
			for (int i = 0; i < diff; i++) {
				Location location = new Location(x ? constNumber : (smallestNumber + i + 1), x ? (smallestNumber + i + 1) : constNumber);
				Piece piece = squares[location.getX()][location.getY()].getPiece();

				if (piece == null) {
					pieceInMiddle = false;
				}
				
				else if (piece.getType() == PieceType.HOLE) {
					Hole hole = (Hole) piece;
					if (!hole.isOccupied()) {
						pieceInMiddle = false;
					}
				}
				
				else if (i + 1 == diff && piece != null) {
					pieceInMiddle = false;
				}
			}

			// Return if there is a piece in the middle.
			return pieceInMiddle;
		}

		else {
			return false;
		}
	}

	/**
	 * Method to move the animal
	 * @param newLocation new location of the animal
	 * @param board instance of board used to move the piece
	 * @return true if the move can be made, else false if the move cannot be made
	 */
	public boolean canMove(Location newLocation, Square[][] squares) {		
		int x1 = pieceLocation.getX();
		int y1 = pieceLocation.getY();
		int x2 = newLocation.getX();
		int y2 = newLocation.getY();

		// If the x coordinate of the rabbit has not changed.
		if (pieceLocation.getX() == newLocation.getX()) {			
			return this.checkValid(x1, true, y1, y2, squares);
		}

		// If the y coordinate of the rabbit has not changed.
		else if (pieceLocation.getY() == newLocation.getY()) {
			return this.checkValid(y1, false, x1, x2, squares);
		}

		else {
			return false;
		}
	}
}
