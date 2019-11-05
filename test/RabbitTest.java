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

class RabbitTest {
	Rabbit rabbit;
	Board board;

	@BeforeEach
	void setUp() throws Exception {
		rabbit = new Rabbit(Rabbit.RABBIT_COLORS.Brown);
		board = new Board(1);
	}

	@AfterEach
	void tearDown() throws Exception {
		rabbit = null;
		board = null;
		assertNull(rabbit);
		assertNull(board);
	}

	@Test
	void testIsMovable() {
		assertTrue(rabbit.isMovable());
	}
	
	@Test
	void testIsSelectable() {
		assertTrue(rabbit.isSelectable());
	}
	
	@Test
	void testValidMove() {
		Square[][] squares = board.getSquares();
		Location rabbitLocation = squares[3][0].getLoc();
		Location location = new Location(3, 2);
		assertTrue(rabbit.move(rabbitLocation, location, board));
	}
	
	@Test
	void testInvalidMove() {
		Square[][] squares = board.getSquares();
		Location rabbitLocation = squares[3][0].getLoc();
		Location location = new Location(3, 3);
		assertFalse(rabbit.move(rabbitLocation, location, board));
	}
}
