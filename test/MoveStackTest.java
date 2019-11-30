import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MoveStackTest {

	private MoveStack moveStack;
	
	@Before
	public void setUp() throws Exception {
		 moveStack = new MoveStack();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		assertEquals(moveStack.isEmpty(),true);
	}
	
	@Test
	public void testSize() {
		assertEquals(moveStack.size(),0);
	}
	
	@Test
	public void testPush() {
		assertEquals(moveStack.size(),0);
		moveStack.push(new Location(0,0), new Rabbit(Constants.RABBIT_COLORS.White, new Location(1,1)));
		assertEquals(moveStack.size(),1);
	}
	
	@Test
	public void testPop() {
		moveStack.push(new Location(0,0), new Rabbit(Constants.RABBIT_COLORS.White, new Location(1,1)));
		moveStack.pop();
		assertEquals(moveStack.isEmpty(),true);
	}
	
	@Test
	public void testGetAllMoves() {
		moveStack.push(new Location(0,0), new Rabbit(Constants.RABBIT_COLORS.White, new Location(1,1)));
		assertEquals(moveStack.getAllMoves().get(0), new Move(new Location(0,0), new Rabbit(Constants.RABBIT_COLORS.White, new Location(1,1))));
	}
	
	

}
