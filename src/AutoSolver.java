import java.util.ArrayList;

/**
 * Class to auto solve the game
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
	private MoveStack possibleMovesHolder;
	private MoveStack moveHolder;
	private ArrayList<String> visitedStates;
	private ArrayList<Animal> animalsInGame;
	
	/**
	 * Default constructor initializing instance variables
	 * @param board instance of Board
	 * @param view instance of View
	 */
	public AutoSolver(Board board, View view) {
		this.board = board;
		this.squares = board.getSquares();
		this.view = view;
		this.possibleMovesHolder = new MoveStack();
		this.moveHolder = new MoveStack();
		this.visitedStates = new ArrayList<>();
		this.animalsInGame = new ArrayList<>();
	}
	
	/**
	 * Method to find all possible location the animal can moved to based on the direction provided
	 * @param animal to be moved in the possible direction
	 * @param direction direction that the animal will be moved in
	 * @return possibleMove this is the possible move that can be made
	 */
	private ArrayList<Location> possibleMoveBasedOnDirection(Animal animal, Directions direction) {
		Location location = animal.getPieceLocation();
		int i;
		boolean horizonal;
		int limit;
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
			limit = Constants.BOARD_SIZE;
			break;
		case LEFT:
			i = 0;
			horizonal = true;
			limit = location.getX() - 1;
			break;
		case RIGHT:
			i = location.getX() + 1;
			horizonal = true;
			limit = Constants.BOARD_SIZE;
			break;
		default:
			return null;
		}
		
		while (i < limit) {
			Location newLocation = new Location(horizonal ? i : location.getX(), horizonal ? location.getY() : i);
			if (animal.canMove(newLocation, squares)) {
				possibleMoves.add(new Location(newLocation));
			}	
			
			i++;
		}
		
		return possibleMoves;
	}
	
	/**
	 * Method to find all the possible moves for a particular animal
	 * @param animal possible moves that can be found for a fox or rabbit
	 */
	private void findPossibleMoves(Animal animal) {
		for (Directions direction: Directions.values()) {
			ArrayList<Location> possibleMoves =  this.possibleMoveBasedOnDirection(animal, direction);
			if (possibleMoves != null) {
				for (Location possibleMove: possibleMoves) {
					possibleMovesHolder.push(possibleMove, animal);
				}
			}
		}
	}
	
	/**
	 * Method to get all the animals in the game
	 */	
	private void getAnimalsInGame() {
		Animal animalPiece = null;
		for (int x = 0; x < Constants.BOARD_SIZE; x++) {
			for (int y = 0; y < Constants.BOARD_SIZE; y++) {
				if (squares[x][y].getPiece() != null) {
					if (squares[x][y].getPiece().getType() == PieceType.RABBIT || squares[x][y].getPiece().getType() == PieceType.FOX) {
						animalPiece = (Animal) squares[x][y].getPiece();
						this.animalsInGame.add(animalPiece);
					}
					else if (squares[x][y].getPiece().getType() == PieceType.HOLE) {
						Hole hole = (Hole) squares[x][y].getPiece();
						
						if (!hole.isOccupied()) {
							continue;
						}
								
						animalPiece = hole.getPiece();
						this.animalsInGame.add(animalPiece);
					}
				}
			}
		}	
	}
	
	
	/**
	 * Method to filter all the possible moves.
	 * @return
	 */
	public MoveStack filterMoves() {
		MoveStack filtedMoves = new MoveStack();
		String boardState = "";
		while(!possibleMovesHolder.isEmpty()) {
			Move move = possibleMovesHolder.pop();
			
			board.movePiece(move.getNewLocation(), move.getPiece(), true, false);
			boardState = board.getBoardState();
			
			if (!visitedStates.contains(boardState)) {
				filtedMoves.push(move.getNewLocation(), move.getPiece());
			}
			
			board.undo();
		}
		
		return filtedMoves;
	}
	
	/**
	 * Method to auto move the animal in order to solve the game
	 * @param animals that can be moved in order to solve the game
	 */
	public void solve() {
		for (Animal animal: this.animalsInGame) {
			this.findPossibleMoves(animal);
		}
		
		MoveStack filteredMoves = this.filterMoves();
		
		if (filteredMoves.isEmpty()) {
			board.undo();
			return;
		}
		
		else {
			for (Move filteredMove: filteredMoves.getAllMoves()) {
				if (!moveHolder.contains(filteredMove)) {
					moveHolder.push(filteredMove.getNewLocation(), filteredMove.getPiece());
				}
			}
		}
		
		Move moveToMake = this.moveHolder.pop();
		
		if (moveToMake != null) {
			board.movePiece(moveToMake.getNewLocation(), moveToMake.getPiece(), true, false);
			visitedStates.add(board.getBoardState());
		}
		
		moveHolder.popAll();
	}
	
	/**
	 * Method to print all the moves. 
	 */
	public void printMoves() {
		for (Move move: moveHolder.getAllMoves()) {
			System.out.println(move);
		}
	}
	
	/**
	 * Method to auto solve the game
	 * @return true if solved, else return false if cannot be solved
	 */
	public boolean autoSolve(int sleepTimer) {
		visitedStates.add(board.getBoardState());
		this.getAnimalsInGame();
		int counter = 1;
		while (!board.isGameWon()) {		
			try {
				this.solve();
				if (view != null) {
					view.updateView();
				}
				Thread.sleep(sleepTimer);
				counter++;
			} catch (Exception e) {
				System.out.println("Failed to find a solution");
				break;
			}
			
			if (counter == 1000) {
				System.out.println("Failed to find a solution");
				break;
			}
		}
		
		return this.board.isGameWon();
	}
}
