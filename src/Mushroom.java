/**
 * Mushroom class is a subclass of Piece Class.
 * They are stationary and cannot move while the game is in progress
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakirki - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class Mushroom extends Piece {

    /**
     * Mushrooms are not movable,
     */
    private static final boolean isMovable = false;

    /**
     * Mushrooms are not selectable.
     */
    private static final boolean isSelectable = false;

    /**
     * Default constructor initializing instance variables
     */
    public Mushroom() {
        super(PieceType.MUSHROOM, isMovable, isSelectable);
    }
}
