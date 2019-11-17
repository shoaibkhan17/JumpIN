
/**
 * MOVE CLASS JAVADOC TO BE DONE
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

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
