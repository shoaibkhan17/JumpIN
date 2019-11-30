import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Auto solver class. Board uses the level builder class internally
 * So, initializing board contributes towards testing the level builder parsing functionality.
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class LevelBuilderTest {
	Board board;
	AutoSolver solver;

	/**
	 * Set up method for the auto solve class
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	/**
	 * Tear down method for the auto solve class
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		board = null;
		assertNull(board);
	}

	/**
	 * Test build for level 1
	 */
	@Test
	public void testBuildLevel1() {
		board = new Board(1);
		assertEquals(board.rabbitCount, 1);
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 2
	 */
	@Test
	public void testBuildLevel2() {
		board = new Board(2);
		assertEquals(board.rabbitCount, 1);
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 3
	 */
	@Test
	public void testBuildLevel3() {
		board = new Board(3);
		assertEquals(board.rabbitCount, 2);
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 4
	 */
	@Test
	public void testBuildLevel4() {
		board = new Board(4);
		assertEquals(board.rabbitCount, 3);
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 5
	 */
	@Test
	public void testBuildLevel5() {
		board = new Board(5);
		assertEquals(board.rabbitCount, 1);
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 6
	 */
	@Test
	public void testBuildLevel6() {
		board = new Board(6);
		assertEquals(board.rabbitCount, 1);
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 7
	 */
	@Test
	public void testBuildLevel7() {
		board = new Board(7);
		assertEquals(board.rabbitCount, 1);
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 8
	 */
	@Test
	public void testBuildLevel8() {
		board = new Board(8);
		assertEquals(board.rabbitCount, 1);
		assertFalse(board.isGameWon());
	}
}
