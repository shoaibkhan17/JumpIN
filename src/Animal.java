/**
 * Animal is an abstract, sub-class of Piece class
 *
 */
public abstract class Animal extends Piece{

	private static final boolean isMovable = true;
    private static final boolean isSelectable = true;
	
	public Animal(PieceType type) {
		super(type, isMovable, isSelectable);
	}

	public abstract void move(int x,int y);
}
