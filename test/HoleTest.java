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

class HoleTest {
	Hole hole;
	Rabbit innerPiece;

	@BeforeEach
	void setUp() throws Exception {
		hole = new Hole();
		innerPiece = new Rabbit(Rabbit.RABBIT_COLORS.White);
	}

	@AfterEach
	void tearDown() throws Exception {
		hole = null;
		innerPiece = null;
		assertNull(hole);
		assertNull(innerPiece);
	}
	
	@Test
	void testIsMovable() {
		assertFalse(hole.isMovable());
	}
	
	@Test
	void testIsSelectable() {
		assertFalse(hole.isSelectable());
	}

	@Test
	void testHole() {
		hole = new Hole();
		assertNull(hole.getPiece());
		assertFalse(hole.isOccupied());
	}

	@Test
	void testSetPiece() {
		hole.setPiece(innerPiece);
		assertEquals(innerPiece, hole.getPiece());
		assertTrue(hole.isOccupied());
	}

	@Test
	void testRemovePiece() {
		hole.setPiece(innerPiece);
		assertEquals(innerPiece, hole.getPiece());
		assertTrue(hole.isOccupied());
		hole.removePiece();
		assertNull(hole.getPiece());
		assertFalse(hole.isOccupied());
	}

	@Test
	void testGetPiece() {
		assertNull(hole.getPiece());
	}

	@Test
	void testIsOccupied() {
		assertFalse(hole.isOccupied());
	}

	@Test
	void testGetStatusUnOccupied() {
		assertEquals("Hole is not occupied.", hole.getStatus());
	}
	
	@Test
	void testGetStatusOccupied() {
		hole.setPiece(innerPiece);
		assertEquals("Hole is occupied by a Rabbit.", hole.getStatus());
	}
}
