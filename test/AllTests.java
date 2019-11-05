import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

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
