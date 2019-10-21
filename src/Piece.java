/**
 * Piece is a abstract class that prints out the type of the piece This class
 * also checks and stores the value if a piece can move
 */
public abstract class Piece {
    private PieceType type;
    private boolean isMovable;
    private boolean isSelectable;

    /**
     * @param type
     * @param isMovable
     * @param isSelectable
     */
    public Piece(PieceType type, boolean isMovable, boolean isSelectable) {
        this.type = type;
        this.isMovable = isMovable;
        this.isSelectable = isSelectable;
    }

    /**
     * @return
     */
    public boolean isSelectable() {
        return isSelectable;
    }

    /**
     * @return
     */
    public boolean isMovable() {
        return isMovable;
    }

    /**
     * @return
     */
    public PieceType getType() {
        return type;
    }

    /**
     *
     */
    public String toString() {
        return this.type.getType() + "";
    }
}
