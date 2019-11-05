import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Hole class
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class HoleTest {
	Hole hole;
	Rabbit innerPiece;

	/**
	 * Set up method for the Hole test class.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		hole = new Hole();
		innerPiece = new Rabbit(Rabbit.RABBIT_COLORS.White);
	}

	/**
	 * Tear down method for the Hole test class.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		hole = null;
		innerPiece = null;
		assertNull(hole);
		assertNull(innerPiece);
	}
	
	/**
	 * Method to test if the hole is movable.
	 */
	@Test
	public void testIsMovable() {
		assertFalse(hole.isMovable());
	}
	
	/**
	 * Method to test if the hole is selectable.
	 */
	@Test
	public void testIsSelectable() {
		assertFalse(hole.isSelectable());
	}

	/**
	 * Method to test the hole class constructor. 
	 */
	@Test
	public void testHole() {
		hole = new Hole();
		assertNull(hole.getPiece());
		assertFalse(hole.isOccupied());
	}

	/**
	 * Method to test when setting the inner piece of the hole.
	 */
	@Test
	public void testSetPiece() {
		hole.setPiece(innerPiece);
		assertEquals(innerPiece, hole.getPiece());
		assertTrue(hole.isOccupied());
	}

	/**
	 * Method to test when removing the inner piece of the hole.
	 */
	@Test
	public void testRemovePiece() {
		hole.setPiece(innerPiece);
		assertEquals(innerPiece, hole.getPiece());
		assertTrue(hole.isOccupied());
		hole.removePiece();
		assertNull(hole.getPiece());
		assertFalse(hole.isOccupied());
	}

	/**
	 * Method to test inner piece of the hole.
	 */
	@Test
	public void testGetPiece() {
		assertNull(hole.getPiece());
	}

	/**
	 * Method to test if when the hole is occupied.
	 */
	@Test
	public void testIsOccupied() {
		assertFalse(hole.isOccupied());
	}

	/**
	 * Method to test when the hole is unoccupied.
	 */
	@Test
	public void testGetStatusUnOccupied() {
		assertEquals("Hole is not occupied.", hole.getStatus());
	}
	
	/**
	 * Method to test the hole status.
	 */
	@Test
	public void testGetStatusOccupied() {
		hole.setPiece(innerPiece);
		assertEquals("Hole is occupied by a Rabbit.", hole.getStatus());
	}
}
