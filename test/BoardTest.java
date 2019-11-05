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

class BoardTest {
	private Board board;
	private Square squares[][];
	
	public BoardTest() {
	}
	
	@BeforeEach
	void setUp() throws Exception {
		board = new Board(1);
		squares = board.getSquares();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testBoard() {
		
	}

	@Test
	void testGetSquares() {
		assertEquals(board.getSquares(), squares);
	}

	@Test
	void testRemovePiece() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectPiece() {
		fail("Not yet implemented");
	}

	@Test
	void testMovePiece() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	void testMove() {
		Location oldLoc = new Location(2, 1);
		Piece piece = squares[oldLoc.getX()][oldLoc.getY()].getPiece();
		Location newLoc = new Location(2, 3);
		board.selectPiece(oldLoc);
		board.movePiece(oldLoc, newLoc, piece);
		
		assertTrue(squares[newLoc.getX()][newLoc.getY()].getPiece() == piece);
	}

	@Test
	void testGetBoardLine() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHoleStatus() {
		fail("Not yet implemented");
	}

	@Test
	void testIsGameWon() {
		assertTrue(board.isGameWon() == false);
	}

	@Test
	void testPrintBoard() {
		fail("Not yet implemented");
	}

}
