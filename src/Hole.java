/**
 * Hole is a subclass of Piece abstract class
 * It is stationary and cannot be moved
 * There are always 5 holes and the location is consistent for any game
 * 4 holes in the four corners of the board and 1 hole in the centre of the board
 */
public class Hole extends Piece {
    public Hole() {
        super('H', false);
    }
}
