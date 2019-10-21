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

    public Hole() {
        super(PieceType.HOLE, isMovable, isSelectable);
        innerPiece = null;
    }

    public void setPiece(Piece piece) {
        this.innerPiece = piece;
    }

    public void removePiece() {
        innerPiece = null;
    }

    public Piece getPiece() {
        return innerPiece;
    }

    public boolean isOccupied() {
        return innerPiece != null;
    }

    public String getStatus() {
        String text = "Hole is ";
        text += isOccupied() ? "occupied by a " + innerPiece.getClass().getName() + "." : "not occupied.";
        return text;
    }

    public String toString() {
        return isOccupied() ? this.innerPiece.toString() : super.toString();
    }
}