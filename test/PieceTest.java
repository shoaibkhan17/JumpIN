import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Piece class
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class PieceTest {
	Piece piece;
	
	/**
 	 * Set up method for the Piece test class.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		piece = new Mushroom();
	}

	/**
 	 * Tear down method for the Piece test class.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		piece = null;
		assertNull(piece);
	}

	/**
	 * Method to test the piece class constructor
	 */
	@Test
	public void testPiece() {
		piece = new Rabbit(Rabbit.RABBIT_COLORS.Gray);
		assertEquals(piece.getType(), PieceType.RABBIT);
	}

	/**
	 * Method to test the is selectable property of the piece class.
	 */
	@Test
	public void testIsSelectable() {
		assertFalse(piece.isSelectable());
	}

	/**
	 * Method to test the is movable property of the piece class.
	 */
	@Test
	public void testIsMovable() {
		assertFalse(piece.isMovable());
	}

	/**
	 * Method to test the type of the piece.
	 */
	@Test
	public void testGetType() {
		assertEquals(piece.getType(), PieceType.MUSHROOM);
	}
}
