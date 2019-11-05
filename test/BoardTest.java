import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
	private Board board;
	private Square squares[][];
	
	public BoardTest() {
	}
	
	@Before
	public void setUp() throws Exception {
		board = new Board(1);
		squares = board.getSquares();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBoard() {
		
	}

	@Test
	public void testGetSquares() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemovePiece() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectPiece() {
		fail("Not yet implemented");
	}

	@Test
	public void testMovePiece() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testMove() {
		Location oldLoc = new Location(2, 1);
		Piece piece = squares[oldLoc.getX()][oldLoc.getY()].getPiece();
		Location newLoc = new Location(2, 3);
		board.selectPiece(oldLoc);
		board.movePiece(oldLoc, newLoc, piece);
		
		assertTrue(squares[newLoc.getX()][newLoc.getY()].getPiece() == piece);
	}

	@Test
	public void testGetBoardLine() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHoleStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsGameWon() {
		assertTrue(board.isGameWon() == false);
	}

	@Test
	public void testPrintBoard() {
		fail("Not yet implemented");
	}

}
