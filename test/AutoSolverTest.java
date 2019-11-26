import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the level builder class
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class AutoSolverTest {
	Board board;
	View view;
	AutoSolver solver;

	/**
	 * Set up method for the auto solve class
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		board = new Board(1);
		view = new View();
		solver = new AutoSolver(board, view);
	}

	/**
	 * Tear down method for the auto solve class
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		board = null;
		view = null;
		solver = null;
		assertNull(board);
		assertNull(view);
		assertNull(solver);
	}

	/**
	 * Test auto solve for level 1
	 */
	@Test
	public void testAutoSolveLevel1() {
		board = new Board(1);
		solver = new AutoSolver(board, view);
		assertTrue(solver.autoSolve(0));
		assertTrue(board.isGameWon());
	}
	
	/**
	 * Test auto solve for level 2
	 */
	@Test
	public void testAutoSolveLevel2() {
		board = new Board(2);
		solver = new AutoSolver(board, view);
		assertTrue(solver.autoSolve(0));
		assertTrue(board.isGameWon());
	}
	
	/**
	 * Test auto solve for level 3
	 */
	@Test
	public void testAutoSolveLevel3() {
		board = new Board(3);
		solver = new AutoSolver(board, view);
		assertTrue(solver.autoSolve(0));
		assertTrue(board.isGameWon());
	}
	
	/**
	 * Test auto solve for level 4
	 */
	@Test
	public void testAutoSolveLevel4() {
		board = new Board(4);
		solver = new AutoSolver(board, view);
		assertTrue(solver.autoSolve(0));
		assertTrue(board.isGameWon());
	}
	
	/**
	 * Test auto solve for level 5
	 */
	@Test
	public void testAutoSolveLevel5() {
		board = new Board(5);
		solver = new AutoSolver(board, view);
		assertTrue(solver.autoSolve(0));
		assertTrue(board.isGameWon());
	}
	
	/**
	 * Test auto solve for level 6
	 */
	@Test
	public void testAutoSolveLevel6() {
		board = new Board(5);
		solver = new AutoSolver(board, view);
		assertTrue(solver.autoSolve(0));
		assertTrue(board.isGameWon());
	}
	
	/**
	 * Test auto solve for level 7
	 */
	@Test
	public void testAutoSolveLevel7() {
		board = new Board(6);
		solver = new AutoSolver(board, view);
		assertTrue(solver.autoSolve(0));
		assertTrue(board.isGameWon());
	}
	
	/**
	 * Test auto solve for level 8
	 */
	@Test
	public void testAutoSolveLevel8() {
		board = new Board(7);
		solver = new AutoSolver(board, view);
		assertTrue(solver.autoSolve(0));
		assertTrue(board.isGameWon());
	}
	
	/**
	 * Test auto solve for an invalid level
	 */
	@Test
	public void testAutoSolveInValid() {
		board = new Board(2);
		Square squares[][] = board.getSquares();
		squares[4][1].removePiece();
		solver = new AutoSolver(board, view);
		assertFalse(solver.autoSolve(0));
		assertFalse(board.isGameWon());
	}
}
