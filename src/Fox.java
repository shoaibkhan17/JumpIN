/**
 * Fox is a subclass of Animal class
 * Its movement is different than Rabbit since it requires two space in the board
 * Fox can only move in the direction that it is facing. Fox cannot not jump
 */
		
public class Fox extends Animal {

	Location otherPieceLocation;
	boolean horizontalMovement;
	boolean tail;

	public Fox(Location otherPieceLocation, boolean horizontalMovement, boolean tail) {
		super(PieceType.FOX);
		this.otherPieceLocation = new Location();
		this.otherPieceLocation.setX(otherPieceLocation.getX());
		this.otherPieceLocation.setY(otherPieceLocation.getY());
		this.horizontalMovement = horizontalMovement;
		this.tail = tail;
	}

	public void setOtherPieceLocation(Location otherPieceLocation) {
		this.otherPieceLocation.setX(otherPieceLocation.getX());
		this.otherPieceLocation.setY(otherPieceLocation.getY());
	}
    
	public boolean checkValid(int constNumber, boolean x, int number1, int number2, Square[][] squares) {
		boolean noPieceInMiddle = true;
		int diff = Math.abs(number1 - number2) - 1;
		int smallestNumber = number1 > number2 ? number2 : number1;

		if(diff >= 1) {
			for(int i = 0; i < diff; i++) {
				Piece piece = squares[x ? constNumber : (smallestNumber + i + 1)][x ? (smallestNumber + i + 1) : constNumber].getPiece();
				if (piece != null && piece.getType() != PieceType.FOX) {
					noPieceInMiddle = false;
				}
			}
			
			return noPieceInMiddle;
		}

		else {
			Piece piece = squares[x ? constNumber : number2][x ? number2 : constNumber].getPiece();
			if (piece == null) {
				return true;
			}
			return false;
		}
	}

	public boolean moveItselfAndBody(Location oldLocation, Location newLocation, Board board) {
		Square[][] squares = board.getSquares();
		int x = otherPieceLocation.getX();
		int y = otherPieceLocation.getY();
		boolean oldLocationGreater = oldLocation.comparesTo(newLocation, horizontalMovement) == 1;
		Location tempLocation; 
		
		if (oldLocationGreater) {
			if (horizontalMovement) {
				tempLocation = new Location(newLocation.getX() + 1, newLocation.getY());
			}

			else {
				tempLocation = new Location(newLocation.getX(), newLocation.getY() + 1);
			}

		}

		else {
			if (horizontalMovement) {
				tempLocation = new Location(newLocation.getX() - 1, newLocation.getY());
			}

			else {
				tempLocation = new Location(newLocation.getX(), newLocation.getY() - 1);
			}
		}

		Fox temp = new Fox(newLocation, horizontalMovement, !this.tail);
		if (board.movePiece(oldLocation, newLocation, this)) {
			if (board.movePiece(otherPieceLocation, tempLocation, temp)) {
				this.otherPieceLocation = tempLocation;
				return true;
			}
		}

		return false;
	}

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

		else if (newLocation.equals(otherPieceLocation)) {
			return true;
		}

		else if (horizontalMovement && y1 == y2) {
			if (this.checkValid(y1, false, x1, x2, squares)) {
				return this.moveItselfAndBody(oldLocation, newLocation, board);
			}
		}

		else if (!horizontalMovement && x1 == x2) {
			if (this.checkValid(x1, true, y1, y2, squares)) {
				return this.moveItselfAndBody(oldLocation, newLocation, board);
			}
		}

		return false;
	}	
}
