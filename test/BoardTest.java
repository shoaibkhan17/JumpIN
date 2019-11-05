import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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

public class BoardTest {
	private int testingLevel;
	private static final int holeCountAtLevel1 = 5; 
	private static final int rabbitCountAtLevel1 = 2;
	private static final int holeCountAtLevel3 = 4; 
	private static final int rabbitCountAtLevel3 = 1;
	private Board board;
	private Square squares[][];
	
	@Before
	public void setUp() throws Exception {
		testingLevel = 1;
		board = new Board(testingLevel);
		squares = board.getSquares();
	}

	@After
	public void tearDown() throws Exception {
		testingLevel = 0;
		board = null;
		squares = null;
		assertNull(board);
		assertNull(squares);
	}

	@Test
	public void testBoard() {
		board = new Board(testingLevel);
		assertNotNull(squares);
		assertEquals(holeCountAtLevel1, board.holeLocations.size());
		assertEquals(rabbitCountAtLevel1, board.rabbitCount);
		assertEquals(new Location(), board.selectedPieceLocation);
		assertNull(board.selectedPiece);
	}

	@Test
	public void testRemovePiece() {
		assertTrue(squares[3][0].hasPiece());
		board.removePiece(new Location(3, 0));
		assertFalse(squares[3][0].hasPiece());
	}

	@Test
	public void testSelectValidPiece() {
		Location rabbitLocation = new Location(3, 0);
		Piece rabbit = squares[rabbitLocation.getX()][rabbitLocation.getY()].getPiece();
		assertTrue(board.selectPiece(rabbitLocation));
		assertEquals(rabbit, board.selectedPiece);
		assertEquals(rabbitLocation, board.selectedPieceLocation);
	}
	
	@Test
	public void testSelectInvalidPiece() {
		assertFalse(board.selectPiece(new Location(3, 1)));
		assertEquals(new Location(), board.selectedPieceLocation);
		assertNull(board.selectedPiece);
	}

	@Test
	public void testMovePiece() {
		Location oldLoc = new Location(2, 1);
		Piece piece = squares[oldLoc.getX()][oldLoc.getY()].getPiece();
		Location newLoc = new Location(2, 3);
		board.selectPiece(oldLoc);
		board.movePiece(oldLoc, newLoc, piece);
		assertEquals(piece, squares[newLoc.getX()][newLoc.getY()].getPiece());
	}

	@Test
	public void testMove() {
		Location oldLoc = new Location(3, 0);
		Piece piece = squares[oldLoc.getX()][oldLoc.getY()].getPiece();
		Location newLoc = new Location(3, 2);
		assertTrue(board.selectPiece(oldLoc));
		assertTrue(board.move(newLoc));
		assertEquals(piece, squares[newLoc.getX()][newLoc.getY()].getPiece());
	}

	@Test
	public void testIsGameWonNotComplete() {
		assertFalse(board.isGameWon());
	}

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
		assertTrue(board.isGameWon());
	}
	
	@Test
	public void testChangeLevel() {
		board.changeLevel(3);
		assertNotNull(squares);
		assertEquals(holeCountAtLevel3, board.holeLocations.size());
		assertEquals(rabbitCountAtLevel3, board.rabbitCount);
		assertEquals(new Location(), board.selectedPieceLocation);
		assertNull(board.selectedPiece);
	}
}
