import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Board class 
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class BoardTest {
	private int testingLevel;
	private static final int HOLE_COUNT_AT_LEVEL1 = 5; 
	private static final int RABBIT_COUNT_AT_LEVEL1 = 1;
	private static final int HOLE_COUNT_AT_LEVEL3 = 5;
	private static final int RABBIT_COUNT_AT_LEVEL3 = 2;
	private Board board;
	private Square squares[][];
	
	/**
	 * Set up method for the Board test class.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		testingLevel = 3;
		board = new Board(testingLevel);
		squares = board.getSquares();
	}

	/**
	 * Tear down method for the Board test class.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		testingLevel = 0;
		board = null;
		squares = null;
		assertNull(board);
		assertNull(squares);
	}

	/**
	 * Method to test the board constructor.
	 */
	@Test
	public void testBoard() {
		board = new Board(testingLevel);
		assertNotNull(squares);
		assertEquals(HOLE_COUNT_AT_LEVEL3, board.holeLocations.size());
		assertEquals(RABBIT_COUNT_AT_LEVEL3, board.rabbitCount);
		assertNull(board.selectedPiece);
	}

	/**
	 * Method to test removing a piece from the board.
	 */
	@Test
	public void testRemovePiece() {
		assertTrue(squares[3][0].hasPiece());
		board.removePiece(new Location(3, 0));
		assertFalse(squares[3][0].hasPiece());
	}

	/**
	 * Method to test the select a valid piece.
	 */
	@Test
	public void testSelectValidPiece() {
		Location rabbitLocation = new Location(3, 0);
		Piece rabbit = squares[rabbitLocation.getX()][rabbitLocation.getY()].getPiece();
		assertTrue(board.selectPiece(rabbitLocation));
		assertEquals(rabbit, board.selectedPiece);
	}
	
	/**
	 * Method to test selecting an invalid piece.
	 */
	@Test
	public void testSelectInvalidPiece() {
		assertFalse(board.selectPiece(new Location(3, 1)));
		assertNull(board.selectedPiece);
	}

	/**
	 * Method to test the move piece functionality of the board.
	 */
	@Test
	public void testMovePiece() {
		Location oldLoc = new Location(3, 0);
		Animal animal = (Animal) squares[oldLoc.getX()][oldLoc.getY()].getPiece();
		Location newLoc = new Location(3, 2);
		board.selectPiece(oldLoc);
		board.movePiece(newLoc, animal, true, false);
		assertEquals(animal, (Animal) squares[newLoc.getX()][newLoc.getY()].getPiece());
	}

	/**
	 * Method to test the main move functionality of the board.
	 */
	@Test
	public void testMove() {
		Location oldLoc = new Location(3, 0);
		Piece piece = squares[oldLoc.getX()][oldLoc.getY()].getPiece();
		Location newLoc = new Location(3, 2);
		assertTrue(board.selectPiece(oldLoc));
		assertTrue(board.move(newLoc));
		assertEquals(piece, squares[newLoc.getX()][newLoc.getY()].getPiece());
	}

	/**
	 * Method to test the is game won functionality of the board.
	 * When the game is not won.
	 */
	@Test
	public void testIsGameWonNotComplete() {
		assertFalse(board.isGameWon());
	}

	/**
	 * Method to test the is game won functionality of the board.
	 * When the game is won.
	 */
	@Test
	public void testIsGameWonComplete() {
		Location oldLoc;
		Location newLoc;
		
		// Move 1
		oldLoc = new Location(3, 0);
		newLoc = new Location(3, 2);
		assertTrue(board.selectPiece(oldLoc));
		assertTrue(board.move(newLoc));
		assertFalse(board.isGameWon());
		
		// Move 2
		oldLoc = new Location(4, 2);
		newLoc = new Location(2, 2);
		assertTrue(board.selectPiece(oldLoc));
		assertTrue(board.move(newLoc));
		assertFalse(board.isGameWon());
		
		// Move 3
		oldLoc = new Location(3, 2);
		newLoc = new Location(3, 0);
		assertTrue(board.selectPiece(oldLoc));
		assertTrue(board.move(newLoc));
		assertFalse(board.isGameWon());
		
		// Move 4
		oldLoc = new Location(3, 0);
		newLoc = new Location(0, 0);
		assertTrue(board.selectPiece(oldLoc));
		assertTrue(board.move(newLoc));

		// The game should be won by now.
		assertTrue(board.isGameWon());
	}
	
	/**
	 * Method to test changing the board level.
	 */
	@Test
	public void testChangeLevel() {
		board.changeLevel(1);
		assertNotNull(squares);
		assertEquals(HOLE_COUNT_AT_LEVEL1, board.holeLocations.size());
		assertEquals(RABBIT_COUNT_AT_LEVEL1, board.rabbitCount);
		assertNull(board.selectedPiece);
	}
}
