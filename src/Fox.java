/**
 * Fox is a subclass of Animal class
 * Its movement is different than Rabbit since it requires two space in the board
 * Fox can only move in the direction that it is facing. Fox cannot not jump
 */
		
public abstract class Fox extends Animal {

	private Direction direction;
	
	public Fox(PieceType type, Direction direction) {
		super(type);
		this.direction = direction;
	}
    
	public boolean checkValid(int constNumber, boolean x, int number1, int number2, Square[][] squares) {
		return true;
	}

	@Override
	public boolean move(Location oldLocation, Location newLocation,  Board board) {
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
