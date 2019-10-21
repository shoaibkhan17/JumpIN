/**
 * Square class contains pieces
 *
 */
public class Square {
    private Piece piece;

    /**
     * Default constructor initializing instance variables
     */
    public Square() {
        this(null);
    }

    /**
     * Overloaded constructor
     * @param piece
     */
    public Square(Piece piece) {
        this.piece = piece;
    }

    /**
     * Determines whether the piece is on it or not
     * @return boolean
     */
    public boolean hasPiece() {
        return this.piece != null;
    }

    /**
     * method which sets the piece
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    /**
     * removes the specific piece
     */
    public void removePiece() {
    	this.piece = null;
    }
    
    /**
     * @return Piece if the piece is found.
     * @return null if no piece is found
     */
    public Piece getPiece() {
        if (this.hasPiece()) {
            return this.piece;
        }

        return null;
    }
    
    /**
     * gets the piece type
     * @return PieceType
     */
    public PieceType getPieceType() {
    	return piece.getType();
    }
    
    /**
     * toString method implemented
     */
    public String toString() {
        return piece == null ? " " : piece.toString();
    }
}
