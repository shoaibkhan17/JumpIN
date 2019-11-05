import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({
	BoardTest.class,
	FoxTest.class,
	HoleTest.class,
	LocationTest.class,
	ParserTest.class,
	PieceTest.class,
	RabbitTest.class,
	SquareTest.class
	})

public class AllTests {
	
}
