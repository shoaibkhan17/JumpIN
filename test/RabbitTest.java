import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Rabbit class
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class RabbitTest {
	Rabbit rabbit;
	Board board;

	/**
 	 * Set up method for the rabbit test class.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		rabbit = new Rabbit(Rabbit.RABBIT_COLORS.Brown);
		board = new Board(1);
	}

	/**
 	 * Tear down method for the rabbit test class.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		rabbit = null;
		board = null;
		assertNull(rabbit);
		assertNull(board);
	}

	/**
	 * Method to test the is movable property of the rabbit class.
	 */
	@Test
	public void testIsMovable() {
		assertTrue(rabbit.isMovable());
	}
	
	/**
	 * Method to test the is selectable property of the rabbit class.
	 */
	@Test
	public void testIsSelectable() {
		assertTrue(rabbit.isSelectable());
	}
	
	/**
	 * Method to test when moving the rabbit to a valid location.
	 */
	@Test
	public void testValidMove() {
		Square[][] squares = board.getSquares();
		Location rabbitLocation = squares[3][0].getLoc();
		Location location = new Location(3, 2);
		assertTrue(rabbit.move(rabbitLocation, location, board));
	}
	
	/**
	 * Method to test when moving the rabbit to a invalid location.
	 */
	@Test
	public void testInvalidMove() {
		Square[][] squares = board.getSquares();
		Location rabbitLocation = squares[3][0].getLoc();
		Location location = new Location(3, 3);
		assertFalse(rabbit.move(rabbitLocation, location, board));
	}
}
