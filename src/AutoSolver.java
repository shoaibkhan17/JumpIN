import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 * AUTOSOLVER CLASS 
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class AutoSolver {
	private enum Directions {UP, DOWN, LEFT, RIGHT}
	private Board board;
	private Square[][] squares;
	private View view;
	private ArrayList<Move> moves;
	private MoveStack movesTest;
	private MoveStack moveHolder;
	private TreeSet<String> boardStateTree;
	private DefaultTreeModel treeTest;
	private Node root;
	private ArrayList<String> visitedStates;
	
	public AutoSolver(Board board, View view) {
		this.board = board;
		squares = board.getSquares();
		this.view = view;
		moves = new ArrayList<>();
		movesTest = new MoveStack();
		moveHolder = new MoveStack();
		boardStateTree = new TreeSet<>();
		visitedStates = new ArrayList<>();
		root = new Node(board.getBoardState());	
	}
	
	private Location possibleMoveBasedOnDirection(Animal animal, Directions direction) {
		Location location = animal.getPieceLocation();
		int i;
		boolean horizonal;
		int limit;
		Location possibleMove = null;

		
		switch(direction) {
		case UP:
			i = 0;
			horizonal = false;
			limit = location.getY();
			break;
		case DOWN:
			i = location.getY() + 1;
			horizonal = false;
			limit = Board.BOARD_SIZE;
			break;
		case LEFT:
			i = 0;
			horizonal = true;
			limit = location.getX() - 1;
			break;
		case RIGHT:
			i = location.getX() + 1;
			horizonal = true;
			limit = Board.BOARD_SIZE;
			break;
		default:
			return possibleMove;
		}
		
		while (i < limit) {
			Location newLocation = new Location(horizonal ? i : location.getX(), horizonal ? location.getY() : i);
			if (animal.canMove(newLocation, squares)) {
				possibleMove = new Location(newLocation);
			}	
			i++;
		}
		
		return possibleMove;
	}
	
	private void findPossibleMoves(Animal animal) {
		for (Directions direction: Directions.values()) {
			Location possibleMove = this.possibleMoveBasedOnDirection(animal, direction);
			if (possibleMove != null) {
				movesTest.push(possibleMove, animal);
			}
		}
	}
	
	private ArrayList<Animal> getAnimalsWithLocation() {
		ArrayList<Animal> animals = new ArrayList<>(); 
		Animal animalPiece = null;
//		int counter = 1;
		for (int x = 0; x < Board.BOARD_SIZE; x++) {
			for (int y = 0; y < Board.BOARD_SIZE; y++) {
				if (squares[x][y].getPiece() != null) {
					if (squares[x][y].getPiece().getType() == PieceType.RABBIT || squares[x][y].getPiece().getType() == PieceType.FOX) {
						animalPiece = (Animal) squares[x][y].getPiece();
						animals.add(animalPiece);
					}
					else if (squares[x][y].getPiece().getType() == PieceType.HOLE) {
						Hole hole = (Hole) squares[x][y].getPiece();
						
						if (!hole.isOccupied()) {
							continue;
						}
								
						animalPiece = hole.getPiece();
						animals.add(animalPiece);
					}
				}
			}
		}
		
		return animals;
	}
	
//	private void moving(AnimalTEMP animal) {
//		Location movingLocation = animal.getPossibleMove();
//		if (movingLocation != null) {
//			System.out.println("Moving " + animal.getAnimalNameAndID() + " to " + movingLocation);
//			Piece piece = animal.getPiece();
//			Location currentLocation = animal.getCurrentLocation();
//			if (this.board.movePiece(movingLocation, ((Animal) piece), false, false)) {
//				animal.setCurrentLocation(movingLocation);
//				this.view.updateView();
//			}
//		}
//	}
	
	private ArrayList<String> getBoardStatesFromPossibleMoves() {
		
		ArrayList<String> boardStates = new ArrayList<>();
		String boardState = "";
		
		while(!movesTest.isEmpty()) {
			Move move = movesTest.pop();
			board.movePiece(move.getNewLocation(), move.getPiece(), true, false);
			boardState = board.getBoardState();
			
			if (!visitedStates.contains(boardState)) {
//				System.out.println("does not contain");
				moveHolder.push(move.getNewLocation(), move.getPiece());
//				System.out.println("does not contain");
				boardStates.add(boardState);
			}
		
			else {
				
//				if (movesTest.size() != 0) {
//					moveHolder.pop();
//					moveHolder.push(move.getNewLocation(), move.getPiece());
//					board.undo();
//				}
				System.out.println("movesTest size" + movesTest.size());

				System.out.println("contains" + boardState);
				
				if (movesTest.size() == 0 && boardStates.size() == 0) {
					moveHolder.push(move.getNewLocation(), move.getPiece());
					System.out.println("SPECIAL CASE");
				}
//				Move a = moveHolder.peek();
////				moveHolder.pop();
//				System.out.println("already there "+ a);
//				
//				System.out.println("contents of move ");
//				moveHolder.printAllMoves();
			}
			
			board.undo();
		}
		
		return boardStates;
	}
	
	public void solve(int number, ArrayList<Animal> animals) {
		System.out.println("----> " + number + " <----");
		this.test(animals);
	}
	
	public void printMoves() {
		for (Move move: moveHolder.getAllMoves()) {
			System.out.println(move);
		}
	}
	
	public void test(ArrayList<Animal> animals) {
		
		for (Animal animal: animals) {
			this.findPossibleMoves(animal);
		}
		
		System.out.println("moves to try:");
		this.printMoves();
				
		ArrayList<String> boardStates = this.getBoardStatesFromPossibleMoves();

		
		if (boardStates.size() == 0) {
			System.out.println("got no new states");
			System.out.println("last visited state " + visitedStates.get(visitedStates.size() - 1));
//			visitedStates.remove(visitedStates.size() - 1);
			
			System.out.println("new state" + visitedStates.size());
			for (String string: visitedStates) {
				System.out.println(string);
			}
			
			System.out.println("overide move");
			this.printMoves();
			
			Move move = moveHolder.first();
			board.movePiece(move.getNewLocation(), move.getPiece(), true, false);
			moveHolder.popAll();
			view.updateView();
			
			return;

//			Node child = root.getChildren().get(0);
//			if (child != null) {
//				child.deleteNode();
//			}
			
//			child.deleteNode();
		}
		
		else {
			System.out.println("got new states:");
			for (String string : boardStates) {
				System.out.println(string);
//				node.addChild(new Node(string));
			}
		}
		
		
		System.out.println("\ngot new moves:");
		this.printMoves();
		
//		Node child = root.getChildren().get(0);
//		// Making move
//		System.out.println("working with state \n" + child);
		Move move = moveHolder.first();
		System.out.println("\nTrying move:" + move);
		
//		if (move == null) {
//			board.undo();
//			return;
//		}
		
//		if (board.canMove(move.getNewLocation(), move.getPiece())) {
			board.movePiece(move.getNewLocation(), move.getPiece(), true, false);
			visitedStates.add(boardStates.get(0));
			moveHolder.popAll();
			
//			System.out.println("\nTree\n");
			
//			root.printTree(root, "-");
			view.updateView();
		
			
			System.out.println("\nAll visited states:" + visitedStates.size());
			for (String string: visitedStates) {
				System.out.println(string);
			}
//		}
	}
	
	public void autoSolve() {
		ArrayList<Animal> animals = this.getAnimalsWithLocation();
		int counter = 1;
//		while (!board.isGameWon()) {
//			this.solve(counter++, animals);
//		}
		
		this.solve(counter++, animals);
		this.solve(counter++, animals);
		this.solve(counter++, animals);
		this.solve(counter++, animals);
		
//		view.displayLevelCompeletePopup();
				
	}
}
