import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Square class
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class SquareTest {
	Location squareLocation;
	Square square;

	@Before
	public void setUp() throws Exception {
		square = new Square(squareLocation);
	}

	@After
	public void tearDown() throws Exception {
		square = null;
		squareLocation = null;
		assertNull(square);
		assertNull(squareLocation);
	}

	@Test
	public void testSquare() {
		square = new Square(squareLocation);
		assertEquals(squareLocation, square.getLoc());
	}
	
	@Test
	public void testSetLocation() {
		Location newLocation = new Location(2, 2);
		square.setLocation(newLocation);
		assertEquals(newLocation, square.getLoc());
	}
	
	@Test
	public void testGetLoc() {
		assertEquals(squareLocation, square.getLoc());
	}

	@Test
	public void testHasPiece() {
		assertFalse(square.hasPiece());
	}

	@Test
	public void testSetPiece() {
		Piece piece = new Mushroom();
		square.setPiece(piece);
		assertTrue(square.hasPiece());
		assertEquals(piece, square.getPiece());
	}

	@Test
	public void testRemovePiece() {
		Piece piece = new Mushroom();
		square.setPiece(piece);
		assertTrue(square.hasPiece());
		square.removePiece();
		assertFalse(square.hasPiece());
	}

	@Test
	public void testGetPiece() {
		Piece piece = new Mushroom();
		square.setPiece(piece);
		assertEquals(piece, square.getPiece());
	}

	@Test
	public void testGetPieceType() {
		Piece piece = new Mushroom();
		square.setPiece(piece);
		assertEquals(piece.getType(), square.getPiece().getType());
	}
}
