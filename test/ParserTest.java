import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParserTest {
	Parser parser;
	Location validLocation;
	Location inValidLocation;

	@BeforeEach
	void setUp() throws Exception {
		parser = new Parser();
		validLocation = new Location(0, 1);
		inValidLocation = new Location(-1, -1);
	}

	@AfterEach
	void tearDown() throws Exception {
		parser = null;
		validLocation = null;
		inValidLocation = null;
		assertNull(parser);
		assertNull(validLocation);
		assertNull(inValidLocation);
	}
	
	@Test
	void testValidLocationBeforeSetting() {
		assertFalse(parser.isValidLocation);
	}
	
	@Test
	void testValidLocation() {
		parser.setText("A2");
		assertTrue(parser.isValidLocation);
		assertEquals(parser.getLocation(), validLocation);
	}
	
	@Test
	void testInValidLocation() {
		parser.setText("6A");
		assertFalse(parser.isValidLocation);
		assertEquals(parser.getLocation(), inValidLocation);
	}
	
	@Test
	void testSetText() {
		parser.setText("A2");
		assertEquals(parser.getLocation(), validLocation);
	}

	@Test
	void testGetLocation() {
		parser.setText("A1");
		assertEquals(parser.getLocation(), new Location(0 , 0));
	}
}
