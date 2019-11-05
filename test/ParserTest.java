import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class ParserTest {
	Parser parser;
	Location validLocation;
	Location inValidLocation;

	@Before
	public void setUp() throws Exception {
		parser = new Parser();
		validLocation = new Location(0, 1);
		inValidLocation = new Location(-1, -1);
	}

	@After
	public void tearDown() throws Exception {
		parser = null;
		validLocation = null;
		inValidLocation = null;
		assertNull(parser);
		assertNull(validLocation);
		assertNull(inValidLocation);
	}
	
	@Test
	public void testValidLocationBeforeSetting() {
		assertFalse(parser.isValidLocation);
	}
	
	@Test
	public void testValidLocation() {
		parser.setText("A2");
		assertTrue(parser.isValidLocation);
		assertEquals(parser.getLocation(), validLocation);
	}
	
	@Test
	public void testInValidLocation() {
		parser.setText("6A");
		assertFalse(parser.isValidLocation);
		assertEquals(parser.getLocation(), inValidLocation);
	}
	
	@Test
	public void testSetText() {
		parser.setText("A2");
		assertEquals(parser.getLocation(), validLocation);
	}

	@Test
	public void testGetLocation() {
		parser.setText("A1");
		assertEquals(parser.getLocation(), new Location(0 , 0));
	}
}
