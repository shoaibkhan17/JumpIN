/**
 * Piece is a abstract class that prints out the type of the piece.
 * This class also checks and stores the value if a piece can be move.
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakirki - 101054933
 * @author Simon Yacoub - 101044159
 * @author Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public abstract class Piece {

    /**
     * Type of the piece.
     */
    private PieceType type;

    /**
     * If it is movable.
     */
    private boolean isMovable;

    /**
     * If it is selectable.
     */
    private boolean isSelectable;

    /**
     * Default constructor initializing instance variables
     * @param type of the piece
     * @param isMovable determines whether the piece can be moved 
     * @param isSelectable whether the piece can be selected or not
     */
    public Piece(PieceType type, boolean isMovable, boolean isSelectable) {
        this.type = type;
        this.isMovable = isMovable;
        this.isSelectable = isSelectable;
    }

    /**
     * method that determines whether a piece can be selected or not
     * @return boolean
     */
    public boolean isSelectable() {
        return this.isSelectable;
    }

    /**
     * method that determines whether a piece can be moved or not
     * @return boolean
     */
    public boolean isMovable() {
        return this.isMovable;
    }

    /**
     * gets the type of the piece
     * @return PieceType
     */
    public PieceType getType() {
        return this.type;
    }

    /**
     * toString method implemented
     */
    public String toString() {
        return this.type.getType() + "";
    }
}
