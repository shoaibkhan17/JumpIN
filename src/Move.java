
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
	private Location newLocation;
	private Animal animalPiece; 
	
	public Move(Location newLocation, Animal piece) {
		this.newLocation = new Location(newLocation);
		this.animalPiece = piece;
	}
	
	public Location getNewLocation() {
		return newLocation;
	}
	
	public Animal getPiece() {
		return animalPiece;
	}
}