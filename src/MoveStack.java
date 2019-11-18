import java.util.ArrayList;

/**
 * MOVESTACK CLASS JAVADOC TO BE DONE
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class MoveStack {
	private ArrayList<Move> movesMade;
	
	public MoveStack() {
		movesMade = new ArrayList<>();
	}
	
	public void push(Location newLocation, Animal animalPiece) {
		Move move = new Move(newLocation, animalPiece);
		movesMade.add(move);
	}
	
	public boolean isEmpty() {
		return movesMade.isEmpty();
	}
	
	public void popAll() {
		movesMade.clear();
	}
	
	public int size() {
		return movesMade.size();
	}
	
	public Move first() {
		if (movesMade.isEmpty()) {
			return null;
		}
	
		return movesMade.remove(0);	
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
	
	public ArrayList<Move> getAllMoves() {
		ArrayList<Move> moves = new ArrayList<>();
		
		for (int i = movesMade.size() - 1; i >= 0; i--) {
			moves.add(movesMade.get(i));
		}
		
		return moves;
		
	}
	
	public void printAllMoves() {
		for (Move move: movesMade) {
			move.printMove();
		}
	}
}