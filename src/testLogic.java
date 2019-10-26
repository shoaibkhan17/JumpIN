import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testLogic {

	@Test
	public void testingMove() {
		Board board = new Board(1);
		Square squares[][] = board.getSquares();
		Location oldLoc = new Location(2, 1);
		Piece piece = squares[oldLoc.getX()][oldLoc.getY()].getPiece();
		Location newLoc = new Location(2, 3);
		board.selectPiece(oldLoc);
		board.movePiece(oldLoc, newLoc, piece);
		assertTrue(squares[newLoc.getX()][newLoc.getY()].getPiece() == piece);
	}
	

	@Test
	public void testAdd(){
		Board board = new Board(1);
		Square squares[][] = board.getSquares();
		assertTrue(board.isGameWon() == false);
	}

}
