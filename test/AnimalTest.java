import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Animal class
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class AnimalTest {
	Location loc;
	Animal animal;

	/**
 	 * Set up method for the animal test class.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		loc = new Location(0, 0);
		animal = new Rabbit(Constants.RABBIT_COLORS.Gray, loc);
	}

	/**
 	 * Set up method for the animal test class.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		animal = null;
		loc = null;
		assertNull(loc);
		assertNull(animal);
	}
	
	/**
	 * Method to test the is selectable property of the animal class.
	 */
	@Test
	public void testIsSelectable() {
		assertTrue(animal.isSelectable());
	}

	/**
	 * Method to test the is movable property of the animal class.
	 */
	@Test
	public void testIsMovable() {
		assertTrue(animal.isMovable());
	}
	
	/**
	 * Method to test the get piece location.
	 */
	public void testGetPieceLocation() {
		assertEquals(loc, animal.getPieceLocation());
	}
	
	/**
	 * Method to test the set piece location.
	 */
	public void testSetPieceLocation() {
		Location location = new Location(1, 1);
		animal.setPieceLocation(location);
		assertEquals(animal, animal.getPieceLocation());
	}

}
