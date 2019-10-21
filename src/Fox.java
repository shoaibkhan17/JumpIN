/**
 * Fox is a subclass of Animal class
 * Its movement is different than Rabbit since it requires two space in the board
 * Fox can only move in the direction that it is facing. Fox cannot not jump
 */
		
public class Fox extends Animal {

	Location otherPieceLocation;
	boolean horizonalMovement; 

	public Fox(Location otherPieceLocation, boolean horizonalMovement) {
		super(PieceType.FOX);
		this.otherPieceLocation = otherPieceLocation;
		this.horizonalMovement = horizonalMovement;
	}
    
	public boolean checkValid(int constNumber, boolean x, int number1, int number2, Square[][] squares) {
		boolean pieceInMiddle = false;
		int diff = Math.abs(number1 - number2) - 1;
		int smallestNumber = number1 > number2 ? number2 : number1;


		if(diff >= 1) {
			for(int i = 0; i < diff; i++) {
				Piece piece = squares[x ? constNumber : (smallestNumber + i + 1)][x ? (smallestNumber + i + 1) : constNumber].getPiece();
				System.out.print((x ? constNumber : (smallestNumber + i + 1)) + "," + (x ? (smallestNumber + i + 1) : constNumber));
				System.out.println(" " + piece);
				if (piece != null) {
					pieceInMiddle = true;
				}
			}
			
			return pieceInMiddle;
		}

		else {
			return false;
		}
	}

	@Override
	public boolean move(Location oldLocation, Location newLocation,  Board board) {
		Square[][] squares = board.getSquares();
		int x1 = oldLocation.getX();
		int y1 = oldLocation.getY();
		int x2 = newLocation.getX();
		int y2 = newLocation.getY();

		if (horizonalMovement && y1 == y2) {
			System.out.println("fox can move there comparing xs");
			this.checkValid(y1, true, x1, x2, squares);
		}

		else if (!horizonalMovement && x1 == x2) {
			System.out.println("fox can move there comparing ys");
			this.checkValid(y1, true, x1, x2, squares);
		}


		return false;
	}	
}
