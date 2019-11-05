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

public class RabbitTest {
	Rabbit rabbit;
	Board board;

	@Before
	public void setUp() throws Exception {
		rabbit = new Rabbit(Rabbit.RABBIT_COLORS.Brown);
		board = new Board(1);
	}

	@After
	public void tearDown() throws Exception {
		rabbit = null;
		board = null;
		assertNull(rabbit);
		assertNull(board);
	}

	@Test
	public void testIsMovable() {
		assertTrue(rabbit.isMovable());
	}
	
	@Test
	public void testIsSelectable() {
		assertTrue(rabbit.isSelectable());
	}
	
	@Test
	public void testValidMove() {
		Square[][] squares = board.getSquares();
		Location rabbitLocation = squares[3][0].getLoc();
		Location location = new Location(3, 2);
		assertTrue(rabbit.move(rabbitLocation, location, board));
	}
	
	@Test
	public void testInvalidMove() {
		Square[][] squares = board.getSquares();
		Location rabbitLocation = squares[3][0].getLoc();
		Location location = new Location(3, 3);
		assertFalse(rabbit.move(rabbitLocation, location, board));
	}
}
