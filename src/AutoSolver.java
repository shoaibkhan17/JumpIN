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
	private Node<String> root;
	
	public AutoSolver(Board board, View view) {
		this.board = board;
		squares = board.getSquares();
		this.view = view;
		moves = new ArrayList<>();
		movesTest = new MoveStack();
		moveHolder = new MoveStack();
		boardStateTree = new TreeSet<>();
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
					if (squares[x][y].getPiece().getType() == PieceType.RABBIT) {
						animalPiece = (Animal) squares[x][y].getPiece();
//						AnimalTEMP animal = new AnimalTEMP(piece, squares[x][y].getLoc(), counter++);
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
		String boardState;
		
		while(!movesTest.isEmpty()) {
			Move move = movesTest.pop();
			moveHolder.push(move.getNewLocation(), move.getPiece());
			board.movePiece(move.getNewLocation(), move.getPiece(), true, false);
			boardState = board.getBoardState();
			boardStates.add(boardState);
			board.undo();
		}
		
		return boardStates;
	}
	
	public void solve(int number, ArrayList<Animal> animals) {
		
		root = new Node<>(board.getBoardState());	
		System.out.println("----> " + number + " <----");
		for (Animal animal: animals) {
			this.findPossibleMoves(animal);
		}
		
		for (String string : this.getBoardStatesFromPossibleMoves()) {
			root.addChild(new Node<String>(string));
		}
		
		
		root.printTree(root, "-");
		System.out.println("");
		
		
		System.out.println("trying " + moveHolder.first());
		
		
//		
//		root.getChildren().get(0).deleteNode();
//		
//		root.printTree(root, "-");
		
	}
	
	public void autoSolve() {
		ArrayList<Animal> animals = this.getAnimalsWithLocation();
		int counter = 1;
//		while (!board.isGameWon()) {
//			this.solve(counter++, animals);
//		}
		
//		view.displayLevelCompeletePopup();

// 		For manually going through the auto solver
		this.solve(counter++, animals);
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
				
	}
}
