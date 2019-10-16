/**
 * Hole is a subclass of Piece abstract class
 * It is stationary and cannot be moved
 * There are always 5 holes and the location is consistent for any game
 * 4 holes in the four corners of the board and 1 hole in the centre of the board
 */
//Change Hole to extend square instead of Piece. 
//Previous implementation made Hole a piece with the exact same methods as Square -_-
public class Hole extends Square{
	
	public Hole() {
		super();
	}
	
	@Override
	//Holes now print "O"
	public String toString() {
        return piece == null ? "O" : piece.toString();
    }
	
}