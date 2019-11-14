/**
 * An object that contains a piece
 * the new location of the piece and the 
 * old location of the piece
 * 
 *
 */
public class PieceMove {
	private Location oldLocation;
	private Location newLocation;
	private Piece piece;
	
	public PieceMove(Location oldLocation, Location newLocation, Piece piece) {
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
