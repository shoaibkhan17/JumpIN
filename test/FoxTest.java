import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Fox class
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class FoxTest {
	Fox fox;
	Board board;
	Location bodyLocation;
	
	@Before
	public void setUp() throws Exception {
		bodyLocation = new Location(1, 4);
		fox = new Fox(bodyLocation, false, false);
		board = new Board(5);
	}

	@After
	public void tearDown() throws Exception {
		bodyLocation = null;
		fox = null;
		board = null;
		assertNull(bodyLocation);
		assertNull(fox);
		assertNull(board);
	}

	@Test
	public void testValidMove() {
		Square[][] squares = board.getSquares();
		Location foxLocation = squares[1][3].getLoc();
		Location location = new Location(1, 2);
		assertTrue(fox.move(foxLocation, location, board));
	}
	
	@Test
	public void testInvalidMove() {
		Square[][] squares = board.getSquares();
		Location foxLocation = squares[1][3].getLoc();
		Location location = new Location(4, 2);
		assertFalse(fox.move(foxLocation, location, board));
	}

	@Test
	public void testSetOtherPieceLocation() {
		Location newBodyLocation = new Location(0, 0);
		fox.setOtherPieceLocation(newBodyLocation);
		assertEquals(newBodyLocation, fox.getBodyLocation());
	}
	
	@Test
	public void testGetBodyLocation() {
		assertEquals(bodyLocation, fox.getBodyLocation());
	}

}
