import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PieceTest {
	Piece piece;
	
	@BeforeEach
	void setUp() throws Exception {
		piece = new Mushroom();
	}

	@AfterEach
	void tearDown() throws Exception {
		piece = null;
		assertNull(piece);
	}

	@Test
	void testPiece() {
		piece = new Rabbit(Rabbit.RABBIT_COLORS.Gray);
		assertEquals(piece.getType(), PieceType.RABBIT);
	}

	@Test
	void testIsSelectable() {
		assertFalse(piece.isSelectable());
	}

	@Test
	void testIsMovable() {
		assertFalse(piece.isMovable());
	}

	@Test
	void testGetType() {
		assertEquals(piece.getType(), PieceType.MUSHROOM);
	}
}
