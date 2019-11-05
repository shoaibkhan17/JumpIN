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

class LocationTest {
	Location location;
	int x;
	int y;

	@BeforeEach
	void setUp() throws Exception {
		x = 2;
		y = 4;
		location = new Location(x, y);
	}

	@AfterEach
	void tearDown() throws Exception {
		location = null;
		assertNull(location);
	}

	@Test
	void testLocation() {
		location = new Location(x, y);
		assertEquals(x, location.getX());
		assertEquals(y, location.getY());
	}

	@Test
	void testGetX() {
		assertEquals(x, location.getX());
	}

	@Test
	void testSetX() {
		int newX = 0;
		location.setX(newX);
		assertEquals(newX, location.getX());
	}

	@Test
	void testGetY() {
		assertEquals(y, location.getY());
	}

	@Test
	void testSetY() {
		int newY = 0;
		location.setY(newY);
		assertEquals(newY, location.getY());
	}

	@Test
	void testClear() {
		location.clear();
		assertEquals(location, new Location(-1, -1));
	}

	@Test
	void testSetLocation() {
		Location setLocation = new Location(5, 5);
		location.setLocation(setLocation);
		assertEquals(setLocation, location);
	}

	@Test
	void testEqualsLocation() {
		Location equalLocation = new Location(x, y);
		assertTrue(location.equals(equalLocation));
	}

	@Test
	void testComparesToSmallerXLocation() {
		Location smallerXLocation = new Location(0, 0);
		assertEquals(1, location.comparesTo(smallerXLocation, true));
	}
	
	@Test
	void testComparesToSmallerYLocation() {
		Location smallerYLocation = new Location(4, 6);
		assertEquals(-1, location.comparesTo(smallerYLocation, false));
	}
	
	@Test
	void testComparesToSameYLocation() {
		Location sameLocation = new Location(x, y);
		assertEquals(0, location.comparesTo(sameLocation, true));
		assertEquals(0, location.comparesTo(sameLocation, false));
	}
	
	@Test
	void testComparesToGreaterXLocation() {
		Location greaterXLocation = new Location(4, 4);
		assertEquals(-1, location.comparesTo(greaterXLocation, true));
	}
	
	@Test
	void testComparesToGreaterYLocation() {
		Location greaterYLocation = new Location(4, 6);
		assertEquals(-1, location.comparesTo(greaterYLocation, false));
	}

}
