/**
 * Hole is a subclass of Piece abstract class
 * It is stationary and cannot be moved
 * There are always 5 holes and the location is consistent for any game
 */
public class Hole extends Piece {
    public Hole() {
        super('H', false);
    }
}
