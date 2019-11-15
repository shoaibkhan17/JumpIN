import java.util.Stack;

public class Animal {
	protected Piece piece;
	protected Stack<Location> possibleMoves;
	protected Stack<Location> previousMoves;
	protected Location currentLocation;
	private int animalID;
	
	public Animal(Piece piece, Location currentLocation, int animalID) {
		this.piece = piece;
		this.currentLocation = new Location();
		this.currentLocation.setLocation(currentLocation);
		this.animalID = animalID;
		possibleMoves = new Stack<>();
		previousMoves = new Stack<>();
	}
	
	public Location getCurrentLocation() {
		return currentLocation;
	}
	
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation.setLocation(currentLocation);
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public void addPossibleMoves(Location possibleMove) {
		Location location = new Location();
		location.setLocation(possibleMove);
		
		if (!possibleMoves.contains(location) && !previousMoves.contains(location)) {
			possibleMoves.push(location);
		}
	}
	
	public String getAnimalNameAndID() {
		return piece.getType() + "" + animalID;
	}
	
	public Location getPossibleMove() {
		if (possibleMoves.isEmpty()) {
			return null;
		}
		
		Location location = possibleMoves.pop();
		Location tempLocation = new Location();
		tempLocation.setLocation(location);
		previousMoves.push(tempLocation);
		
		if (possibleMoves.isEmpty() && !previousMoves.isEmpty()) {
			previousMoves.clear();
		}
		
		return location;
	}
	
	public String getAllPossibleMovesString() {
		String possibleMovesLocations = "";
		Stack<Location> possibleMovesTemp = new Stack<>();
		for (Location location : possibleMoves) {
			possibleMovesTemp.add(location);
		}
		
		while (!possibleMovesTemp.isEmpty()) {
			possibleMovesLocations += possibleMovesTemp.pop() + " ";
		}
		
		return possibleMovesLocations;
	}
	
	public void print() {
		String moveText = possibleMoves.size() == 0 ? "cannot move" : "can move to " + this.getAllPossibleMovesString();
		System.out.println(piece.getType() + "" + animalID + " at " + currentLocation + " --> " + moveText);
	}
}
