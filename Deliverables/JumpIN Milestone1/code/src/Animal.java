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
	private static final boolean isMovable = true; 

	/**
	 * Animals can be selected.
	 */
    private static final boolean isSelectable = true; 
	
	/** Default constructor.
	 * @param type this is the type of the piece 
	 */
	public Animal(PieceType type) {
		super(type, isMovable, isSelectable);
	}

	/** 
	 * The following is an abstract method which is implemented by the Pieces that can move.
	 * @param oldLocation this is the initial location of the piece to be moved.
	 * @param newLocation this is the new location of the piece.
	 * @param board this is a parameter board passed in as an instance of the Board class.
	 * @return boolean returns true if the move has been made, else returns false.
	 */
	public abstract boolean move(Location oldLocation, Location newLocation, Board board);
}
