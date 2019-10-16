/**
 * Mushroom class is a subclass of Piece Class.
 * They are stationary and cannot move while the game is in progress
 */
public class Mushroom extends Piece {

    private static final boolean isMovable = false;
    private static final boolean isSelectable = false;

    public Mushroom() {
        super(PieceType.MUSHROOM, isMovable, isSelectable);
    }
}
