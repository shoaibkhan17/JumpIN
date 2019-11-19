
/**
 * Move class to contain the animal that is about to be moved or is moved
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
	 * Default constructor initializing instance variables
	 * @param newLocation this is the location to where the animal will be moved to 
	 * @param piece this is the piece that will be moved
	 */
	public Move(Location newLocation, Animal piece) {
		this.newLocation = new Location(newLocation);
		this.animalPiece = piece;
	}
	
	/**
	 * Method to get the new location of the animal
	 * @return newLocation the new location to where the animal will be moved to
	 */
	public Location getNewLocation() {
		return newLocation;
	}
	
	/**
	 * Method to get the animal
	 * @return animalPiece this is the animal piece which is returned by this method
	 */
	public Animal getPiece() {
		return animalPiece;
	}
	
	/**
	 * Method to print the animal move
	 */
	public void printMove() {
		System.out.println(this.toString());
	}
	
	/**
	 * Method to get the animal move string
	 * @return String representation of the animal piece movement and new location
	 */
	@Override
	public String toString() {
		return animalPiece.getPieceLocation() +  "->" + newLocation;
	}
	
	/**
	 * Equals method implemented
	 */
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null)
	        return false;
	    if (getClass() != o.getClass())
	        return false;
	    Move m = (Move) o;
	    return this.animalPiece.equals(m.animalPiece)
	            && this.newLocation.equals(m.newLocation);
	}
}