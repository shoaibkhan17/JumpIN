/**
 * An abstract Animal class that extends Piece
 * contains the state descriptions and properties of the Animal Pieces
 * 
 */

public abstract class Animal extends Piece {
	
	private static final boolean isMovable = true; 
    private static final boolean isSelectable = true; 
	
	/** Constructor to initialize the instance variables
	 * @param type this is the type of the piece 
	 */
	public Animal(PieceType type) {
		super(type, isMovable, isSelectable);
	}

	/** 
	 * The following is an abstract method which is implemented by the Pieces that can move
	 * @param oldLocation this is the initial location of the piece to be moved
	 * @param newLocation this is the new location of the piece
	 * @param board this is a parameter board passed in as an instance of Board class
	 * @return boolean returns true if the move has been made, else returns false
	 */
	public abstract boolean move(Location oldLocation, Location newLocation, Board board);
}
