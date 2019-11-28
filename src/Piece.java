import java.io.Serializable;

/**
 * Piece is a abstract class that prints out the type of the piece.
 * This class also checks and stores the value if a piece can be move.
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public abstract class Piece implements Serializable {

    /**
	 * Serial version UID for serialization and de-serialization
	 */
	private static final long serialVersionUID = 10L;

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
     * @return boolean true if selectable, else false if not selectable
     */
    public boolean isSelectable() {
        return this.isSelectable;
    }

    /**
     * method that determines whether a piece can be moved or not
     * @return boolean true if movable, else false if it is not movable
     */
    public boolean isMovable() {
        return this.isMovable;
    }

    /**
     * gets the type of the piece
     * @return PieceType type of the piece
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
