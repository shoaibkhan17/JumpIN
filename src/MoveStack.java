import java.util.ArrayList;

public class MoveStack {
	private ArrayList<Move> movesMade;
	
	public MoveStack() {
		movesMade = new ArrayList<>();
	}
	
	public void push(Location oldLocation, Location newLocation, Piece piece) {
		Location oldLoc = new Location();
		oldLoc.setLocation(oldLocation);
		Location newLoc = new Location();
		newLoc.setLocation(newLocation);
		Move move = new Move(oldLoc, newLoc, piece);
		movesMade.add(move);
	}
	
	public void popAll() {
		movesMade.clear();
	}
	
	public Move pop() {
		if (movesMade.isEmpty()) {
			return null;
		}
		
		Move move = movesMade.get(movesMade.size() - 1);
		movesMade.remove(movesMade.size() - 1);
		return move;
	}
	
	public Move peek() {
		if (movesMade.isEmpty()) {
			return null;
		}
		
		Move move = movesMade.get(movesMade.size() - 1);
		return move;
	}
	
	public void printAllMoves() {
//		for (Move move: movesMade) {
//			Location loc = move.getLocation();
//			Piece piece = move.getPiece();
//			System.out.println("Moved " + piece.toString() + " from " + loc.toString());
//		}
	}
}
