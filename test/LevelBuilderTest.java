import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Auto solver class
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
	LevelBuilder levelBuilder;

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
		levelBuilder = null;
		assertNull(board);
		assertNull(levelBuilder);
	}

	/**
	 * Test build for level 1
	 */
	@Test
	public void testBuildLevel1() {
		levelBuilder = new LevelBuilder(1, board);	
		assertTrue(levelBuilder.buildLevel());
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 2
	 */
	@Test
	public void testBuildLevel2() {
		levelBuilder = new LevelBuilder(2, board);	
		assertTrue(levelBuilder.buildLevel());
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 3
	 */
	@Test
	public void testBuildLevel3() {
		levelBuilder = new LevelBuilder(3, board);	
		assertTrue(levelBuilder.buildLevel());
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 4
	 */
	@Test
	public void testBuildLevel4() {
		levelBuilder = new LevelBuilder(4, board);	
		assertTrue(levelBuilder.buildLevel());
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 5
	 */
	@Test
	public void testBuildLevel5() {
		levelBuilder = new LevelBuilder(5, board);	
		assertTrue(levelBuilder.buildLevel());
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 6
	 */
	@Test
	public void testBuildLevel6() {
		levelBuilder = new LevelBuilder(6, board);	
		assertTrue(levelBuilder.buildLevel());
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 7
	 */
	@Test
	public void testBuildLevel7() {
		levelBuilder = new LevelBuilder(7, board);	
		assertTrue(levelBuilder.buildLevel());
		assertFalse(board.isGameWon());
	}
	
	/**
	 * Test build for level 8
	 */
	@Test
	public void testBuildLevel8() {
		levelBuilder = new LevelBuilder(8, board);	
		assertTrue(levelBuilder.buildLevel());
		assertFalse(board.isGameWon());
	}
}
