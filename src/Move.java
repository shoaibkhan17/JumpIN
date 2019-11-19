
/**
 * Move class containing description for the 
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
	
	/**
	 * 
	 * @param newLocation
	 * @param piece
	 */
	public Move(Location newLocation, Animal piece) {
		this.newLocation = new Location(newLocation);
		this.animalPiece = piece;
	}
	
	/**
	 * 
	 * @return
	 */
	public Location getNewLocation() {
		return newLocation;
	}
	
	/**
	 * 
	 * @return
	 */
	public Animal getPiece() {
		return animalPiece;
	}
	
	/**
	 * 
	 */
	public void printMove() {
		System.out.println(this.toString());
	}
	
	/**
	 * 
	 */
	public String toString() {
		return animalPiece.getPieceLocation() +  "->" + newLocation;
	}
}