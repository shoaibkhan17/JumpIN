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
	Location foxLocation;
	
	/**
	 * Set up method for the Fox test class.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		bodyLocation = new Location(1, 4);
		foxLocation = new Location(1, 3);
		fox = new Fox(foxLocation, bodyLocation, false, false);
		board = new Board(5);
	}

	/**
	 * Tear down method for the Fox test class
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		bodyLocation = null;
		foxLocation = null;
		fox = null;
		board = null;
		assertNull(bodyLocation);
		assertNull(fox);
		assertNull(board);
		assertNull(foxLocation);
	}

	/**
	 * Method to test if the fox is movable.
	 */
	@Test
	public void testIsMovable() {
		assertTrue(fox.isMovable());
	}
	
	/**
	 * Method to test if the fox is selectable.
	 */
	@Test
	public void testIsSelectable() {
		assertTrue(fox.isSelectable());
	}

	/**
	 * Method to test moving the fox to a valid location.
	 */
	@Test
	public void testValidMove() {
		Square[][] squares = board.getSquares();
		Location foxLocation = squares[1][3].getLoc();
		Location location = new Location(1, 2);
		assertTrue(fox.canMove(location, squares));
	}
	
	/**
	 * Method to test moving the fox to an invalid location.
	 */
	@Test
	public void testInvalidMove() {
		Square[][] squares = board.getSquares();
		Location foxLocation = squares[1][3].getLoc();
		Location location = new Location(4, 2);
		assertFalse(fox.canMove(location, squares));
	}

	/**
	 * Method to test setting the other body part location of the fox.
	 */
	@Test
	public void testSetOtherPieceLocation() {
		Location newBodyLocation = new Location(0, 0);
		fox.setOtherPieceLocation(newBodyLocation);
		assertEquals(newBodyLocation, fox.getBodyLocation());
	}
	
	/**
	 * Method to get the body part location of the fox.
	 */
	@Test
	public void testGetBodyLocation() {
		assertEquals(bodyLocation, fox.getBodyLocation());
	}

}
