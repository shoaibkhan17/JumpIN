/**
 * Mushroom class is a subclass of Piece Class.
 * They are stationary and cannot move while the game is in progress
 */
public class Mushroom extends Piece {

    private static final boolean isMovable = false; //sets isMovable to false initially
    private static final boolean isSelectable = false; //sets isSelectable to false initially

    /**
     * Default constructor initializing instance variables
     */
    public Mushroom() {
        super(PieceType.MUSHROOM, isMovable, isSelectable);
    }
}
