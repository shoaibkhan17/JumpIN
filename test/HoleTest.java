import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class HoleTest {
	Hole hole;
	Rabbit innerPiece;

	@Before
	public void setUp() throws Exception {
		hole = new Hole();
		innerPiece = new Rabbit(Rabbit.RABBIT_COLORS.White);
	}

	@After
	public void tearDown() throws Exception {
		hole = null;
		innerPiece = null;
		assertNull(hole);
		assertNull(innerPiece);
	}
	
	@Test
	public void testIsMovable() {
		assertFalse(hole.isMovable());
	}
	
	@Test
	public void testIsSelectable() {
		assertFalse(hole.isSelectable());
	}

	@Test
	public void testHole() {
		hole = new Hole();
		assertNull(hole.getPiece());
		assertFalse(hole.isOccupied());
	}

	@Test
	public void testSetPiece() {
		hole.setPiece(innerPiece);
		assertEquals(innerPiece, hole.getPiece());
		assertTrue(hole.isOccupied());
	}

	@Test
	public void testRemovePiece() {
		hole.setPiece(innerPiece);
		assertEquals(innerPiece, hole.getPiece());
		assertTrue(hole.isOccupied());
		hole.removePiece();
		assertNull(hole.getPiece());
		assertFalse(hole.isOccupied());
	}

	@Test
	public void testGetPiece() {
		assertNull(hole.getPiece());
	}

	@Test
	public void testIsOccupied() {
		assertFalse(hole.isOccupied());
	}

	@Test
	public void testGetStatusUnOccupied() {
		assertEquals("Hole is not occupied.", hole.getStatus());
	}
	
	@Test
	public void testGetStatusOccupied() {
		hole.setPiece(innerPiece);
		assertEquals("Hole is occupied by a Rabbit.", hole.getStatus());
	}
}
