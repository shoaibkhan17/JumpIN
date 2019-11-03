/**
 * Rabbit is subclass of Animal class.
 * Rabbits can only jump if there is/are obstacles on their path.
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class Rabbit extends Piece{
	
	public static enum RABBIT_COLORS {Gray, White, Brown}
	protected RABBIT_COLORS rabbitColor;

	/**
	 * Default constructor
	 */
    public Rabbit(RABBIT_COLORS rabbitColor) {
        super(PieceType.RABBIT, true, true);
        this.rabbitColor = rabbitColor;
    }

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
		int diff = Math.abs(number1 - number2) - 1;
		int smallestNumber = number1 > number2 ? number2 : number1;

		// Go through all the spaces the rabbit has moved and check if there is a piece there.
		if (diff >= 1) {
			for (int i = 0; i < diff; i++) {
				Piece piece = squares[x ? constNumber : (smallestNumber + i + 1)][x ? (smallestNumber + i + 1) : constNumber].getPiece();
				if (piece == null || piece.getType() == PieceType.HOLE) {
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
	 * @param oldLocation old location of the animal
	 * @param newLocation new location of the animal
	 * @param board instance of board used to move the piece
	 * @return true if the move can be made, else false if the move cannot be made
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

		// If the x coordinate of the rabbit has not changed.
		else if (oldLocation.getX() == newLocation.getX()) {
			if (this.checkValid(x1, true, y1, y2, squares)) {
				return board.movePiece(oldLocation, newLocation, this);
			}

			return false;
		}

		// If the y coordinate of the rabbit has not changed.
		else if (oldLocation.getY() == newLocation.getY()) {
			if (this.checkValid(y1, false, x1, x2, squares)) {
				return board.movePiece(oldLocation, newLocation, this);
			}

			return false;
		}

		else {
			return false;
		}
	}
}
