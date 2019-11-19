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
	private MoveStack movesTest;
	private MoveStack moveHolder;
	private ArrayList<String> visitedStates;
	
	public AutoSolver(Board board, View view) {
		this.board = board;
		squares = board.getSquares();
		this.view = view;
		movesTest = new MoveStack();
		moveHolder = new MoveStack();
		visitedStates = new ArrayList<>();
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
	
	private ArrayList<String> getBoardStatesFromPossibleMoves() {
		
		ArrayList<String> boardStates = new ArrayList<>();
		String boardState = "";
		
		while(!movesTest.isEmpty()) {
			Move move = movesTest.pop();
			board.movePiece(move.getNewLocation(), move.getPiece(), true, false);
			boardState = board.getBoardState();
			
			if (!visitedStates.contains(boardState)) {
				moveHolder.push(move.getNewLocation(), move.getPiece());
				boardStates.add(boardState);
			}
		
			else {
				if (movesTest.size() == 0 && boardStates.size() == 0) {
					moveHolder.push(move.getNewLocation(), move.getPiece());
				}
			}
			
			board.undo();
		}
		
		return boardStates;
	}
	
	public void solve(ArrayList<Animal> animals) {
		for (Animal animal: animals) {
			this.findPossibleMoves(animal);
		}
				
		ArrayList<String> boardStates = this.getBoardStatesFromPossibleMoves();

		if (boardStates.size() == 0) {	
			Move move = moveHolder.first();
			board.movePiece(move.getNewLocation(), move.getPiece(), true, false);
			moveHolder.popAll();
			return;
		}

		Move move = moveHolder.first();
		board.movePiece(move.getNewLocation(), move.getPiece(), true, false);
		visitedStates.add(boardStates.get(0));
		moveHolder.popAll();
		
	}
	
	public void printMoves() {
		for (Move move: moveHolder.getAllMoves()) {
			System.out.println(move);
		}
	}
	
	public boolean autoSolve() {
		ArrayList<Animal> animals = this.getAnimalsWithLocation();
		int counter = 1;
		while (!board.isGameWon()) {		
			try {
				this.solve(animals);
				view.updateView();
				counter++;
			} catch (Exception e) {
				System.out.println("Failed to find a solution");
			}
			
			if (counter == 1000) {
				System.out.println("Failed to find a solution");
				break;
			}
		}
		
		return this.board.isGameWon();
	}
}
