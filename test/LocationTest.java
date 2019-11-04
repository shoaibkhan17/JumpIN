import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testLocation() {
		Board board = new Board(1);
		Square squares[][] = board.getSquares();
		Location oldLoc = new Location(3, 2);
		Piece piece = squares[oldLoc.getX()][oldLoc.getY()].getPiece();
		Location newLoc = new Location(4, 1);
		board.selectPiece(oldLoc);
		board.movePiece(oldLoc, newLoc, piece);
		
		assertTrue(squares[newLoc.getX()][newLoc.getY()].getPiece() == piece);
		
	}

	@Test
	void testLocationIntInt() {
		fail("Not yet implemented");
	}

	@Test
	void testGetX() {
		fail("Not yet implemented");
	}

	@Test
	void testSetX() {
		fail("Not yet implemented");
	}

	@Test
	void testGetY() {
		fail("Not yet implemented");
	}

	@Test
	void testSetY() {
		fail("Not yet implemented");
	}

	@Test
	void testClear() {
		fail("Not yet implemented");
	}

	@Test
	void testSetLocation() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsLocation() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testComparesTo() {
		fail("Not yet implemented");
	}

}
