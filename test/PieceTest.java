import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

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

public class PieceTest {
	Piece piece;
	
	@Before
	public void setUp() throws Exception {
		piece = new Mushroom();
	}

	@After
	public void tearDown() throws Exception {
		piece = null;
		assertNull(piece);
	}

	@Test
	public void testPiece() {
		piece = new Rabbit(Rabbit.RABBIT_COLORS.Gray);
		assertEquals(piece.getType(), PieceType.RABBIT);
	}

	@Test
	public void testIsSelectable() {
		assertFalse(piece.isSelectable());
	}

	@Test
	public void testIsMovable() {
		assertFalse(piece.isMovable());
	}

	@Test
	public void testGetType() {
		assertEquals(piece.getType(), PieceType.MUSHROOM);
	}
}
