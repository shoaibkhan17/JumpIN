import javax.swing.JButton;

/**
 * Square class contains pieces of the board
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class Square extends JButton {
    private Piece piece;
    private Location location;

    /**
     * Default constructor initializing instance variables
     */
    public Square(Location location) {
        this(null, location);
    }
    /**
     * Overloaded constructor
     * @param piece
     */
    public Square(Piece piece, Location location) {
    	super();
		this.setFocusPainted(false);
        this.piece = piece;
        this.location = location;
    }

    /**
     * Determines whether the piece is on it or not
     * @return boolean
     */
    public boolean hasPiece() {
        return this.piece != null;
    }

    /**
     * Method to set the piece.
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    public void setLocation(Location location) {
    	this.location = location;
    }
    
    public Location getLoc() {
    	return location;
    }
    
    /**
     * Removes the specific piece from the square.
     */
    public void removePiece() {
    	this.piece = null;
    }
    
    /**
     * Method to get the piece.
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
     * Gets the piece type
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
