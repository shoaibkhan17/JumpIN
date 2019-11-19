import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Move class
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class MoveTest {
	
	Animal animal;
	Location newLocation;
	Move move;

	/**
 	 * Set up method for the move test class.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		animal = new Rabbit(Rabbit.RABBIT_COLORS.Brown, new Location(0, 0));
		newLocation = new Location(1, 1);
		move = new Move(newLocation, animal);
	}

	/**
 	 * Tear down up method for the move test class.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		animal = null;
		newLocation = null;
		move = null;
		assertNull(animal);
		assertNull(newLocation);
		assertNull(move);
	}

	/**
	 * Testing the move class constructor
	 */
	@Test
	public void testMove() {
		assertEquals(move.getNewLocation(), newLocation);
		assertEquals(move.getPiece(), animal);
		
	}

	/**
	 * Method to test the get new location
	 */
	@Test
	public void testGetNewLocation() {
		assertEquals(move.getNewLocation(), newLocation);
	}

	/**
	 * Method to test the get piece
	 */
	@Test
	public void testGetPiece() {
		assertEquals(move.getPiece(), animal);
	}

}
