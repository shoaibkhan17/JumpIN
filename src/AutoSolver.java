import java.util.ArrayList;

public class AutoSolver {
	private enum Directions {UP, DOWN, LEFT, RIGHT}
	private Board board;
	private Square[][] squares;
	
	public AutoSolver(Board board) {
		this.board = board;
		squares = board.getSquares();
	}
	
	private boolean canMoveDirection(Location location, Piece piece, Directions direction) {
		int i;
		boolean horizonal;
		int limit;
		boolean validMove = false;
		Rabbit rabbit = null;
		
		if (piece == null) {
			return false;
		}
		
		else if (piece.getType() == PieceType.RABBIT) {
			rabbit = (Rabbit) piece;
		}
		
		switch(direction) {
		case UP:
			i = 0;
			horizonal = false;
			limit = location.getY();
			break;
		case DOWN:
			i = location.getY() + 1;
			horizonal = false;
			limit = Board.BOARD_SIZE;
			break;
		case LEFT:
			i = 0;
			horizonal = true;
			limit = location.getX() - 1;
			break;
		case RIGHT:
			i = location.getX() + 1;
			horizonal = true;
			limit = Board.BOARD_SIZE;
			break;
		default:
			return false;
		}
		
		while (i < limit) {
			Location newLocation = new Location(horizonal ? i : location.getX(), horizonal ? location.getY() : i);
			if (rabbit.canMove(location, newLocation, board)) {
				validMove = true;
			}
			
			i++;
		}
		
		System.out.println(direction + " : " + validMove);		
		return validMove;
	}
	
	private ArrayList<Directions> findPossibleMoveDirections(Location location, Piece piece) {
		ArrayList<Directions> possibleDirections = new ArrayList<>();
		for (Directions direction: Directions.values()) {
			if (this.canMoveDirection(location, piece, direction)) {
				possibleDirections.add(direction);
			}
		}
		
		return possibleDirections;
	}
	
	private ArrayList<Move> getRabbitsWithLocation() {
		ArrayList<Move> rabbits = new ArrayList<>(); 
		Piece piece = null;
		for (int x = 0; x < Board.BOARD_SIZE; x++) {
			for (int y = 0; y < Board.BOARD_SIZE; y++) {
				if (squares[x][y].getPiece() != null && squares[x][y].getPiece().getType() == PieceType.RABBIT) {
					piece = squares[x][y].getPiece();
					Move rabbit = new Move(squares[x][y].getLoc(), squares[x][y].getLoc(), piece);
					rabbits.add(rabbit);
				}
			}
		}
		
		return rabbits;
	}
	
	public void autoSolve() {
		ArrayList<Move> rabbits = this.getRabbitsWithLocation();
		
		for (Move rabbit: rabbits) {
			Location location = rabbit.getOldLocation();
			Piece piece = rabbit.getPiece();
			System.out.println("Working with rabbit at location " + location.toString());
			this.findPossibleMoveDirections(location, piece);
		}
	}
}
