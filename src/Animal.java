/**
 * An abstract Animal class that extends Piece
 * Contains the state descriptions and properties of the Animal Pieces
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public abstract class Animal extends Piece {
	
	/**
	 * Serial version UID for serialization and de-serialization 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Animals can be moved
	 */
	private static final boolean IS_MOVABLE = true; 

	/**
	 * Animals can be selected
	 */
    private static final boolean IS_SELECTABLE = true;
    
    /**
     * Location of Animal
     */
    protected Location pieceLocation; 
	
	/** Default constructor
	 * @param type this is the type of the piece 
	 */
	public Animal(PieceType type, Location pieceLocation) {
		super(type, IS_MOVABLE, IS_SELECTABLE);
		this.pieceLocation = new Location(pieceLocation);
	}
	
	/**
	 * Method that returns the location of the piece
	 * @return pieceLocation this is the location of the piece
	 */
	public Location getPieceLocation() {
		return pieceLocation;
	}
	
	/**
	 * Method that sets the location of the piece 
	 * @param pieceLocation this is the location that is to
	 */
	public void setPieceLocation(Location pieceLocation) {
		this.pieceLocation.setLocation(pieceLocation);
	}
	
	/**
	 * Equals method implemented
	 * @param Object o The object to compare with
	 * @return true if the object is the same as this
	 */
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null)
	        return false;
	    if (getClass() != o.getClass())
	        return false;
	    Animal a = (Animal) o;
	    return this.pieceLocation.equals(a.pieceLocation)
	    		&& this.getType().equals(a.getType());
	           
	}

	/** 
	 * The following is an abstract method which is implemented by the Pieces that can move
	 * @param newLocation this is the new location of the piece
	 * @param squares this is a parameter board passed in as an instance of the Board class
	 * @return boolean returns true if the move has been made, else returns false
	 */
	public abstract boolean canMove(Location newLocation, Square[][] squares);
	
	/**
	 * Abstract method to move the animal 
	 * @param newLocation this is the new location to which the animal can move to
	 * @param board instance of board
	 * @param userMove whether the user can move
	 * @param redo the move
	 * @return true if move can be made, else false if move cannot be made 
	 */
	public abstract boolean move(Location newLocation, Board board, boolean userMove, boolean redo);
}
