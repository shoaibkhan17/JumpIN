/**
 * Hole is a subclass of Piece abstract class
 * It is stationary and cannot be moved
 * There are always 5 holes and the location is consistent for any game
 * 4 holes in the four corners of the board and 1 hole in the centre of the board
 */
public class Hole extends Piece {

    private static final boolean isMovable = false;
    private static final boolean isSelectable = false;
    private Piece innerPiece;

    /**
     * Default constructor initializing instance variables
     */
    public Hole() {
        super(PieceType.HOLE, isMovable, isSelectable);
        this.innerPiece = null;
    }

    /**
     * method which sets the piece
     * @param piece to be set
     */
    public void setPiece(Piece piece) {
        this.innerPiece = piece;
    }

    /**
     * method which removes the piece from the hole
     */
    public void removePiece() {
        this.innerPiece = null;
    }

    /**
     * gets the piece
     * @return innerPiece piece that gets returned
     */

    public Piece getPiece() {
        return this.innerPiece;
    }

    /**
     * checks whether the hole is occupied
     * @return true if the hole is occupied, false if the hole is not occupied
     */
    public boolean isOccupied() {
        return innerPiece != null;
    }

    /**
     * gets the status of the hole
     * @return String containing the status of the hole
     */
    public String getStatus() {
        String text = "Hole is ";
        text += isOccupied() ? "occupied by a " + innerPiece.getClass().getName() + "." : "not occupied.";
        return text;
    }

    /**
     * String representation and calls to toString methods
     */
    public String toString() {
        return isOccupied() ? this.innerPiece.toString() : super.toString();
    }
}