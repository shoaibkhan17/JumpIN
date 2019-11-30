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
	
	/**
	 * Serial version UID for serialization and de-serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Rabbit color
	 */
	protected Constants.RABBIT_COLORS rabbitColor;

	/**
	 * Default constructor
	 */
    public Rabbit(Constants.RABBIT_COLORS rabbitColor, Location pieceLocation) {
        super(PieceType.RABBIT, pieceLocation);
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
		boolean pieceInMiddle = true;
		int diff = Math.abs(number1 - number2);
		int min = Math.min(number1, number2);
		int max = Math.max(number1, number2);
		int numberToCheck = max == number2 ? max : min;
		
		if (diff == 1) {
			return false;
		}
		
		while ((min + 1) <= (max - 1)) {
			Location location = new Location(x ? constNumber : (min + 1), x ? (min + 1) : constNumber);
			Piece piece = squares[location.getX()][location.getY()].getPiece();
			if (piece == null) {
				pieceInMiddle = false;
				break;
			}
			else {
				if (piece.getType() == PieceType.HOLE) {
					Hole hole = (Hole) piece;
					if (!hole.isOccupied()) {
						pieceInMiddle = false;
						break;
					}
				}
			}
			min++;
		}
		
		// Check the last piece
		if (pieceInMiddle) {
			Location location = new Location(x ? constNumber : (numberToCheck), x ? (numberToCheck) : constNumber);
			Piece piece = squares[location.getX()][location.getY()].getPiece();
			if (piece != null) {
				if (piece.getType() == PieceType.HOLE) {
					Hole hole = (Hole) piece;
					return !hole.isOccupied();
				}
				return false;
				
			}
			return true;
		}
		
		return false;
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
	
	/**
	 * Method to check if an object is equal to this object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this.getClass() == obj.getClass()) {
			Rabbit rabbit = (Rabbit) obj;
			return this.rabbitColor == rabbit.rabbitColor && this.pieceLocation.equals(rabbit.getPieceLocation());
    	}
    	return false;
	}
	
    /**
     * Method to generate the xml structure of the object
     */
    public String toXML() {
    	String xml = "    <Rabbit>\n";
    	xml += "        <Coordinate1>" + pieceLocation.toStringNumeric() + "</Coordinate1>\n";
    	xml += "        <Color>" + rabbitColor + "</Color>\n";
    	xml += "    </Rabbit>";
    	return xml;
    }
}
