/**
 * Fox is a subclass of Animal class
 * Its movement is different than Rabbit since it requires two space in the board
 * Fox can only move in the direction that it is facing. Fox cannot not jump
 */
		
public class Fox extends Animal {

    // TODO change 2 different classes head and tail
    public Fox() {
        super(PieceType.FOX);
    }

	@Override
	public boolean move(Location oldLocation, Location newLocation,  Board board) {
        return true;
	}
}
