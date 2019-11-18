/**
 * An abstract Animal class that extends Piece.
 * Contains the state descriptions and properties of the Animal Pieces
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakirki - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public abstract class Animal extends Piece {
	
	/**
	 * Animals can be moved. 
	 */
	private static final boolean IS_MOVABLE = true; 

	/**
	 * Animals can be selected.
	 */
    private static final boolean IS_SELECTABLE = true;
    
    /**
     * ADD JAVA DOC
     */
    protected Location pieceLocation; 
	
	/** Default constructor.
	 * @param type this is the type of the piece 
	 */
	public Animal(PieceType type, Location pieceLocation) {
		super(type, IS_MOVABLE, IS_SELECTABLE);
		this.pieceLocation = new Location(pieceLocation);
	}
	
	/**
	 * ADD JAVA DOC
	 * @return
	 */
	public Location getPieceLocation() {
		return pieceLocation;
	}
	
	/**
	 * ADD JAVA DOC
	 * @param pieceLocation
	 */
	public void setPieceLocation(Location pieceLocation) {
		this.pieceLocation.setLocation(pieceLocation);
	}

	/** 
	 * The following is an abstract method which is implemented by the Pieces that can move.
	 * @param newLocation this is the new location of the piece.
	 * @param squares this is a parameter board passed in as an instance of the Board class.
	 * @return boolean returns true if the move has been made, else returns false.
	 */
	public abstract boolean canMove(Location newLocation, Square[][] squares);
}
