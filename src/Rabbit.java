/**
 * Rabbit is subclass of Animal class 
 * Rabbits can only jump if there is/are obstacles on their path
 */
public class Rabbit extends Animal {

	/**
	 * Default constructor
	 */
    public Rabbit() {
        super(PieceType.RABBIT);
    }

	/**
	 * Method that checks whether the move made by the Rabbit is valid or not
	 * @param constNumber 
	 * @param x
	 * @param number1
	 * @param number2
	 * @param squares
	 * @return boolean true if the move is valid, false if the move is not valid
	 */
	public boolean checkValid(int constNumber, boolean x, int number1, int number2, Square[][] squares) {
		boolean pieceInMiddle = true;
		int diff = Math.abs(number1 - number2) - 1;
		int smallestNumber = number1 > number2 ? number2 : number1;

		if (diff >= 1) {
			for (int i = 0; i < diff; i++) {
				Piece piece = squares[x ? constNumber : (smallestNumber + i + 1)][x ? (smallestNumber + i + 1)
						: constNumber].getPiece();
				if (piece == null || piece.getType() == PieceType.HOLE) {
					pieceInMiddle = false;
				}
			}

			return pieceInMiddle;
		}

		else {
			return false;
		}
	}

	/**
	 * method which makes the move
	 * @return boolean
	 */
	@Override
	public boolean move(Location oldLocation, Location newLocation, Board board) {
		Square[][] squares = board.getSquares();
		int x1 = oldLocation.getX();
		int y1 = oldLocation.getY();
		int x2 = newLocation.getX();
		int y2 = newLocation.getY();

		if (oldLocation.equals(newLocation)) {
			return true;
		}

		else if (oldLocation.getX() == newLocation.getX()) {
			if (this.checkValid(x1, true, y1, y2, squares)) {
				board.movePiece(oldLocation, newLocation, this);
				return true;
			}

			return false;
		}

		else if (oldLocation.getY() == newLocation.getY()) {
			if (this.checkValid(y1, false, x1, x2, squares)) {
				board.movePiece(oldLocation, newLocation, this);
				return true;
			}

			return false;
		}

		else {
			return false;
		}
	}
}
