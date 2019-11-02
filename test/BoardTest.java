import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
	
	public BoardTest() {
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testBoard() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSquares() {
		fail("Not yet implemented");
	}

	@Test
	void testRemovePiece() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectPiece() {
		fail("Not yet implemented");
	}

	@Test
	void testMovePiece() {
		fail("Not yet implemented");
	}

	@Test
	void testMove() {
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
	void testGetBoardLine() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHoleStatus() {
		fail("Not yet implemented");
	}

	@Test
	void testIsGameWon() {
		Board board = new Board(1);
		
		assertTrue(board.isGameWon() == false);
	}

	@Test
	void testPrintBoard() {
		fail("Not yet implemented");
	}

}
