import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
class SquareTest {
	Location squareLocation;
	Square square;

	@BeforeEach
	void setUp() throws Exception {
		square = new Square(squareLocation);
	}

	@AfterEach
	void tearDown() throws Exception {
		square = null;
		squareLocation = null;
		assertNull(square);
		assertNull(squareLocation);
	}

	@Test
	void testSquare() {
		square = new Square(squareLocation);
		assertEquals(squareLocation, square.getLoc());
	}
	
	@Test
	void testSetLocation() {
		Location newLocation = new Location(2, 2);
		square.setLocation(newLocation);
		assertEquals(newLocation, square.getLoc());
	}
	
	@Test
	void testGetLoc() {
		assertEquals(squareLocation, square.getLoc());
	}

	@Test
	void testHasPiece() {
		assertFalse(square.hasPiece());
	}

	@Test
	void testSetPiece() {
		Piece piece = new Mushroom();
		square.setPiece(piece);
		assertTrue(square.hasPiece());
		assertEquals(piece, square.getPiece());
	}

	@Test
	void testRemovePiece() {
		Piece piece = new Mushroom();
		square.setPiece(piece);
		assertTrue(square.hasPiece());
		square.removePiece();
		assertFalse(square.hasPiece());
	}

	@Test
	void testGetPiece() {
		Piece piece = new Mushroom();
		square.setPiece(piece);
		assertEquals(piece, square.getPiece());
	}

	@Test
	void testGetPieceType() {
		Piece piece = new Mushroom();
		square.setPiece(piece);
		assertEquals(piece.getType(), square.getPiece().getType());
	}
}
