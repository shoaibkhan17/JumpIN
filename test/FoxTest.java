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
	Fox bodyFox;
	Board board;
	Location bodyLocation;
	Location foxLocation;
	
	/**
	 * Set up method for the Fox test class.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		bodyLocation = new Location(1, 1);
		foxLocation = new Location(1, 0);
		fox = new Fox(foxLocation, bodyLocation, false, false);
		bodyFox = new Fox(bodyLocation, foxLocation, false, true);
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
	 * Method to test moving the fox to a valid location by head.
	 */
	@Test
	public void testValidMoveByHead() {
		Square[][] squares = board.getSquares();
		Location location = new Location(1, 3);
		assertTrue(fox.canMove(location, squares));
	}
	
	/**
	 * Method to test moving the fox to a valid location by tail.
	 */
	@Test
	public void testValidMoveByTail() {
		Square[][] squares = board.getSquares();
		Location location = new Location(1, 3);
		assertTrue(bodyFox.canMove(location, squares));
	}
	
	/**
	 * Method to test selecting itself head and head.
	 */
	@Test
	public void testSelectingItselfHead() {
		Square[][] squares = board.getSquares();
		assertTrue(fox.canMove(foxLocation, squares));
	}
	
	/**
	 * Method to test selecting itsself tail and tail.
	 */
	@Test
	public void testSelectingItselfBody() {
		Square[][] squares = board.getSquares();
		assertTrue(bodyFox.canMove(bodyLocation, squares));
	}
	
	/**
	 * Method to test selecting body and head.
	 */
	@Test
	public void testSelectingBodyHead() {
		Square[][] squares = board.getSquares();
		assertTrue(bodyFox.canMove(foxLocation, squares));
	}
	
	/**
	 * Method to test selecting head and body.
	 */
	@Test
	public void testSelectingHeadBody() {
		Square[][] squares = board.getSquares();
		assertTrue(fox.canMove(bodyLocation, squares));
	}
	
	/**
	 * Method to test moving the fox to an invalid vertical location.
	 */
	@Test
	public void testInvalidVerticalMove() {
		Square[][] squares = board.getSquares();
		squares[1][3].setPiece(new Mushroom(new Location(1, 3)));
		Location location = new Location(1, 4);
		assertFalse(fox.canMove(location, squares));
	}
	
	/**
	 * Method to test moving the fox to an invalid horizontal location.
	 */
	@Test
	public void testInvalidHorizontalMove() {
		Square[][] squares = board.getSquares();
		Location location = new Location(3, 1);
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
