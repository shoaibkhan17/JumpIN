/**
 * Hole is a subclass of the abstract Piece class
 * It is stationary and cannot be moved.
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class Hole extends Piece {

	/**
	 * Serial version UID for serialization and de-serialization 
	 */
	private static final long serialVersionUID = 5L;
	
    /**
     * Holes cannot be moved.
     */
    private static final boolean IS_MOVABLE = false;

    /**
     * Holes cannot be selected.
     */
    private static final boolean IS_SELECTABLE = false;

    /**
     * Inner piece.
     */
    private Animal innerPiece;

    /**
     * Default constructor initializing instance variables
     */
    public Hole() {
        super(PieceType.HOLE, IS_MOVABLE, IS_SELECTABLE);
        this.innerPiece = null;
    }

    /**
     * Method which sets the piece
     * @param piece to be set
     */
    public void setPiece(Animal piece) {
        this.innerPiece = piece;
    }

    /**
     * Method which removes the piece from the hole.
     */
    public void removePiece() {
        this.innerPiece = null;
    }

    /**
     * Method to get the inner piece.
     * @return innerPiece piece that gets returned.
     */

    public Animal getPiece() {
        return this.innerPiece;
    }

    /**
     * Checks whether the hole is occupied.
     * @return true if the hole is occupied, false if the hole is not occupied.
     */
    public boolean isOccupied() {
        return innerPiece != null;
    }

    /**
     * Method to gets the status of the hole
     * @return String containing the status of the hole
     */
    public String getStatus() {
        String text = "Hole is ";
        text += isOccupied() ? "occupied by a " + innerPiece.getClass().getName() + "." : "not occupied.";
        return text;
    }

    /**
     * String representation and calls to toString methods
     * @return the inner piece string or the hole string
     */
    public String toString() {
        return isOccupied() ? this.innerPiece.toString() : super.toString();
    }
}