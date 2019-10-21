/**
 * Square class contains pieces
 *
 */
public class Square {
    private Piece piece;

    /**
     * 
     */
    public Square() {
        piece = null;
    }

    /**
     * @param piece
     */
    public Square(Piece piece) {
        this.piece = piece;
    }

    /**
     * @return
     */
    public boolean hasPiece() {
        return this.piece != null;
    }

    /**
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    /**
     * 
     */
    public void removePiece() {
    	this.piece = null;
    }
    
    /**
     * @return Piece if the piece is found.
     * @return null if no piece is found
     */
    /**
     * @return
     */
    public Piece getPiece() {
        if (this.hasPiece()) {
            return this.piece;
        }

        return null;
    }
    
    /**
     * @return
     */
    public PieceType getPieceType() {
    	return piece.getType();
    }
    
    /**
     *
     */
    public String toString() {
        return piece == null ? " " : piece.toString();
    }
}
