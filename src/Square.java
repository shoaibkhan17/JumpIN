import java.io.Serializable;

import javax.swing.JButton;

/**
 * The Square class contains the pieces
 * and the location of the pieces
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
@SuppressWarnings("serial")
public class Square extends JButton implements Serializable {
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
     * @param piece that is going to be stored inside the square
     * @param location of the piece on the square
     */
    public Square(Piece piece, Location location) {
    	super();
		this.setFocusPainted(false);
        this.piece = piece;
        this.location = location;
    }

    /**
     * Determines whether the piece is on it or not
     * @return boolean true if the piece is on it, else returns false if the piece is not on the square
     */
    public boolean hasPiece() {
        return this.piece != null;
    }

    /**
     * Method to set the piece
     * @param piece to be set
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    /**
     * method which sets the location of the piece
     * @param location that is going to be set which is passed in as a parameter
     */
    public void setLocation(Location location) {
    	this.location = location;
    }
    
    /**
     * Method that gets the location of the square
     * @return Location of the square
     */
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
     * Method to get the piece
     * @return Piece if the piece is found
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
     * @return PieceType the type of piece
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
