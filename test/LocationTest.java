import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Test cases for the Location class
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class LocationTest {
	Location location;
	int x;
	int y;

	@Before
	public void setUp() throws Exception {
		x = 2;
		y = 4;
		location = new Location(x, y);
	}

	@After
	public void tearDown() throws Exception {
		location = null;
		assertNull(location);
	}

	@Test
	public void testLocation() {
		location = new Location(x, y);
		assertEquals(x, location.getX());
		assertEquals(y, location.getY());
	}

	@Test
	public void testGetX() {
		assertEquals(x, location.getX());
	}

	@Test
	public void testSetX() {
		int newX = 0;
		location.setX(newX);
		assertEquals(newX, location.getX());
	}

	@Test
	public void testGetY() {
		assertEquals(y, location.getY());
	}

	@Test
	public void testSetY() {
		int newY = 0;
		location.setY(newY);
		assertEquals(newY, location.getY());
	}

	@Test
	public void testClear() {
		location.clear();
		assertEquals(location, new Location(-1, -1));
	}

	@Test
	public void testSetLocation() {
		Location setLocation = new Location(5, 5);
		location.setLocation(setLocation);
		assertEquals(setLocation, location);
	}

	@Test
	public void testEqualsLocation() {
		Location equalLocation = new Location(x, y);
		assertTrue(location.equals(equalLocation));
	}

	@Test
	public void testComparesToSmallerXLocation() {
		Location smallerXLocation = new Location(0, 0);
		assertEquals(1, location.comparesTo(smallerXLocation, true));
	}
	
	@Test
	public void testComparesToSmallerYLocation() {
		Location smallerYLocation = new Location(4, 6);
		assertEquals(-1, location.comparesTo(smallerYLocation, false));
	}
	
	@Test
	public void testComparesToSameYLocation() {
		Location sameLocation = new Location(x, y);
		assertEquals(0, location.comparesTo(sameLocation, true));
		assertEquals(0, location.comparesTo(sameLocation, false));
	}
	
	@Test
	public void testComparesToGreaterXLocation() {
		Location greaterXLocation = new Location(4, 4);
		assertEquals(-1, location.comparesTo(greaterXLocation, true));
	}
	
	@Test
	public void testComparesToGreaterYLocation() {
		Location greaterYLocation = new Location(4, 6);
		assertEquals(-1, location.comparesTo(greaterYLocation, false));
	}

}
