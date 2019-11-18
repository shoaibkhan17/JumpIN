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
		visitedStates.add(board.getBoardState());
		root = new Node(board.getBoardState());	
	}

	
	private ArrayList<Location> possibleMoveBasedOnDirection(Animal animal, Directions direction) {
		Location location = animal.getPieceLocation();
		int i;
		boolean horizonal;
		int limit;
		Location possibleMove = null;
		ArrayList<Location> possibleMoves = new ArrayList<>();

		
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
			return null;
		}
		
		while (i < limit) {
			Location newLocation = new Location(horizonal ? i : location.getX(), horizonal ? location.getY() : i);
			if (animal.canMove(newLocation, squares)) {
				possibleMove = new Location(newLocation);
				possibleMoves.add(new Location(newLocation));
			}	
			i++;
		}
		
		return possibleMoves;
	}
	
	private ArrayList<Move> findPossibleMoves(Location previousMove) {
		ArrayList<Move> move = new ArrayList<>();
		ArrayList<Animal> animals = this.getAnimalsWithLocation();
		for (Animal animal: animals) {
			for (Directions direction: Directions.values()) {
				ArrayList<Location> possibleMoves = this.possibleMoveBasedOnDirection(animal, direction);
				if (possibleMoves != null) {			
					for (Location possibleMove: possibleMoves) {
						if (previousMove == null) {
							movesTest.push(possibleMove, animal);
							move.add(new Move(possibleMove, animal));
						}
						
						else {
							System.out.println("previous move " + previousMove);
							move.add(new Move(possibleMove, animal));
	//						if (!possibleMove.isReverse(previousMove)) {
								move.add(new Move(possibleMove, animal));
	//						}
	
						}
					}
				}
				
			}
		}
		return move;
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
	
		Node node;
		if (root.getChildren().size() == 0) {
			node = root;
		}
		
		else {
			node = root.getChildren().get(0);
		}
		
//		while(!board.isGameWon()) {
//			System.out.println("\n\n----> " + ++number + " <----");
//			this.test(node, animals);
//		}
		System.out.println("----> " + number + " <----");
		this.test(node, animals);
//		System.out.println("\n\n----> " + ++number + " <----");
//		this.test(node, animals);
//		System.out.println("\n\n----> " + ++number + " <----");
//		this.test(node, animals);
//		System.out.println("\n\n----> " + ++number + " <----");
//		this.test(node, animals);
//		System.out.println("\n\n----> " + ++number + " <----");
//		this.test(node, animals);
//		System.out.println("\n\n----> " + ++number + " <----");
//		this.test(node, animals);
//		System.out.println("\n\n----> " + ++number + " <----");
//		this.test(node, animals);
//		
		
//		for (Animal animal: animals) {
//			view.highlightSelectedSquare(board.getSquareAtLocation(animal.getPieceLocation()));
//			this.findPossibleMoves(animal);
//		}
//		
//		ArrayList<String> boardStates = this.getBoardStatesFromPossibleMoves();
//		for (String s: boardStates) {
//			System.out.println(s);
//		}
//		
//		this.printMoves();
		
//		System.out.println("\n\n----> " + ++number + " <----");
//		this.test(node, animals);
		
		
//		System.out.println("----> " + ++number + " <----");
//		this.test(animals);
//		for (Animal animal: animals) {
//			this.findPossibleMoves(animal);
//		}
//		
//		for (String string : this.getBoardStatesFromPossibleMoves()) {
//			System.out.println("contains in tree? :" + root.contains(new Node(string)));
//			root.addChild(new Node(string));
//		}
////		this.test(animals);
//		
//		root.printTree(root, "-");
//		System.out.println("");
//		
//		Node child = root.getChildren().get(0);
//	
//		
//		// Making move
//		System.out.println("working with state \n" + child);
//		Move move = moveHolder.first();
//		System.out.println("trying " + move);
//		
//		board.movePiece(move.getNewLocation(), move.getPiece(), true, false);
//		
//		System.out.println("current board state: " + board.getBoardState());
//		view.updateView();
//		System.out.println();
//		
//	
//		// Round 2
//		
//		System.out.println("----> " + ++number + " <----");
//		System.out.println("new possible moves");
//		this.test(animals);


		
		
//		
//		root.getChildren().get(0).deleteNode();
//		
//		root.printTree(root, "-");
	
	}
	
	public void printMoves() {
		for (Move move: moveHolder.getAllMoves()) {
			System.out.println(move);
		}
	}
	
	public void test(Node node, ArrayList<Animal> animals) {
		
		System.out.println("moves to try:");
		this.printMoves();
		
		int before = movesTest.size();
		for (Animal animal: animals) {
//			this.findPossibleMoves(animal);
		}
		
		int after = movesTest.size();
		System.out.println(before == after);
		
		ArrayList<String> boardStates = this.getBoardStatesFromPossibleMoves();

		
		if (boardStates.size() == 0) {
			System.out.println("got no new states");
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
				node.addChild(new Node(string));
				visitedStates.add(string);
			}
		}
		
		
		System.out.println("\ngot new moves:");
		this.printMoves();
		
		Node child = root.getChildren().get(0);
//		// Making move
//		System.out.println("working with state \n" + child);
		Move move = moveHolder.first();
		System.out.println("\nTrying move:" + move);
		
//		if (board.canMove(move.getNewLocation(), move.getPiece())) {
			board.movePiece(move.getNewLocation(), move.getPiece(), true, false);	
//			System.out.println("\nTree\n");
			
//			root.printTree(root, "-");
			view.updateView();
		
			
			System.out.println("\nAll visited states:");
			for (String string: visitedStates) {
				System.out.println(string);
			}
//		}
	}
	
	public void doAllMoves(ArrayList<Move> moves) {
		System.out.println("got moves: ");
		for (Move move: moves) {
			System.out.print(move + " ");
			if (board.canMove(move.getNewLocation(), move.getPiece())) {
				board.movePiece(move.getNewLocation(), move.getPiece(), true, false);
			}
		}
		view.updateView();
	}
	
	public ArrayList<Move> puzzleBreadthSearch() {
		ArrayList<ArrayList<Move>> superMoves = new ArrayList<ArrayList<Move>>();
		superMoves.add(new ArrayList<Move>());
		
		int i = 0;
		
		do {
			
			this.doAllMoves(superMoves.get(i));

			Location previousMove = null;
			
			if (superMoves.get(i).size() == 0) {
				previousMove = null;
			}
			
			else {
				previousMove = superMoves.get(i).get(superMoves.get(i).size() - 1).getNewLocation();
			}
			
			ArrayList<Move> moves = this.findPossibleMoves(previousMove);
			
			System.out.println("moves to try: " + moves);
			
			for (int j = 0; j < moves.size(); j++) {
				ArrayList<Move> currMove = (ArrayList<Move>) superMoves.get(i).clone();
				currMove.add(moves.get(j));
				superMoves.add(currMove);
				
				try {
					Move moveToTry = currMove.get(currMove.size()-1);
					if (board.canMove(moveToTry.getNewLocation(), moveToTry.getPiece())) {
						board.movePiece(moveToTry.getNewLocation(), moveToTry.getPiece(), true, false);
					}
				}
				catch (Exception e) {}
				
				if (board.isGameWon()) {
					return currMove;
				}
				
				else {
					board.undo();
				}
			}
			
			while(!board.moveStack.isEmpty()) {
				board.undo();
			}
			i++;
			if (superMoves.size() > 100000000) {
				System.out.println("not far enough");
				break;
			}
			
			
		} while (i < superMoves.size());
		
		return null;
	}
	
	public Move getNextMove() {
		ArrayList<Move> path;
		try {
			path = puzzleBreadthSearch();
			return path.get(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		throw new RuntimeException("Failed to find solution");
	}
	
	public void autoSolve() {
		
		System.out.println(this.getNextMove());

		
//		for (Animal animal: animals) {
//			this.findPossibleMoves(animal, null);
//		}
		
//		this.doAllMoves();
		view.updateView();
		
		
		
		
//		int counter = 1;
//		while (!board.isGameWon()) {
//			this.solve(counter++, animals);
//		}
		
//		view.displayLevelCompeletePopup();

// 		For manually going through the auto solver
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
//		this.solve(counter++, animals);
				
	}
}
