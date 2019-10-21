/**
 * Animal is an abstract, sub-class of Piece class
 *
 */
public abstract class Animal extends Piece {

	private static final boolean isMovable = true;
    private static final boolean isSelectable = true;
	
	/** Constructor to initialize the instance variables
	 * @param type
	 */
	public Animal(PieceType type) {
		super(type, isMovable, isSelectable);
	}

	/** The following method is declaration of the move method which is implemented in Rabbit and fox method
	 * @param oldLocation
	 * @param newLocation
	 * @param board
	 * @return boolean
	 */
	public abstract boolean move(Location oldLocation, Location newLocation, Board board);
}
