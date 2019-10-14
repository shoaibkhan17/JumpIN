
public class Square {
    private Piece piece;

    public Square() {
        piece = null;
    }

    public Square(Piece piece) {
        this.piece = piece;
    }

    public boolean hasPiece() {
        return this.piece != null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
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
    
    

    public String printSquare() {
        return piece == null ? " " : piece.toString();
    }
}
