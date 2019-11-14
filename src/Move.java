

public class Move {
	private Location oldLocation;
	private Location newLocation;
	private Piece piece; 
	
	public Move(Location oldLocation, Location newLocation, Piece piece) {
		this.oldLocation = oldLocation;
		this.newLocation = newLocation;
		this.piece = piece;
	}
	
	public Location getOldLocation() {
		return oldLocation;
	}
	
	public Location getNewLocation() {
		return newLocation;
	}
	
	public Piece getPiece() {
		return piece;
	}
}
