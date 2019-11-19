import java.util.ArrayList;

/**
 * Custom stack created for Undo and Redo
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class MoveStack {
	private ArrayList<Move> movesMade;
	
	/**
	 * Default constructor initializing instance variables
	 */
	public MoveStack() {
		movesMade = new ArrayList<>();
	}
	
	/**
	 * Method implemented for the push functionality of the stack
	 * Adds an element to the collection
	 * @param newLocation this is the new location of the animal
	 * @param animalPiece this is the animal piece that will be moved
	 */
	public void push(Location newLocation, Animal animalPiece) {
		Move move = new Move(newLocation, animalPiece);
		movesMade.add(move);
	}
	
	/**
	 * Method to check whether the stack is empty 
	 * @return boolean true if the stack is empty, else false if the stack is not empty
	 */
	public boolean isEmpty() {
		return movesMade.isEmpty();
	}
	
	/**
	 * Method to pop all the elements in the stack
	 */
	public void popAll() {
		movesMade.clear();
	}
	
	/**
	 * Method to determine the size of the stack
	 * @return int this is the size of the stack
	 */
	public int size() {
		return movesMade.size();
	}
	
	/**
	 * Method to remove the first element of the stack
	 * @return null if the stack is empty, else returns and removes the first element of the stack
	 */
	public Move first() {
		if (movesMade.isEmpty()) {
			return null;
		}
	
		return movesMade.remove(0);	
	}
	
	/**
	 * Method that pops the last element of the stack
	 * @return null if the stack is empty, else return move
	 */
	public Move pop() {
		if (movesMade.isEmpty()) {
			return null;
		}
		
		Move move = movesMade.get(movesMade.size() - 1);
		movesMade.remove(movesMade.size() - 1);
		return move;
	}
	
	/**
	 * Method that retrieves the element from the stack
	 * @return null if the stack is empty, else return the peeked element 
	 */
	public Move peek() {
		if (movesMade.isEmpty()) {
			return null;
		}
		
		Move move = movesMade.get(movesMade.size() - 1);
		return move;
	}
	
	/**
	 * Method that returns an ArrayList of all the moves
	 * @return moves this contains all the moves 
	 */
	public ArrayList<Move> getAllMoves() {
		ArrayList<Move> moves = new ArrayList<>();
		
		for (int i = movesMade.size() - 1; i >= 0; i--) {
			moves.add(movesMade.get(i));
		}
		
		return moves;
		
	}
	
	/**
	 * Method that calls the printMove() method on each move
	 */
	public void printAllMoves() {
		for (Move move: movesMade) {
			move.printMove();
		}
	}
}