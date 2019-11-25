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
	
	/**
	 * Method to find all the possible moves for a particular animal
	 * @param animal possible moves that can be found for a fox or rabbit
	 */
	private void findPossibleMoves(Animal animal) {
		for (Directions direction: Directions.values()) {
			Location possibleMove = this.possibleMoveBasedOnDirection(animal, direction);
			if (possibleMove != null) {
				possibleMovesHolder.push(possibleMove, animal);
			}
		}
	}
	
	/**
	 * Method to get all animals in the game
	 */	
	private void getAnimalsInGame() {
		Animal animalPiece = null;
		for (int x = 0; x < Board.BOARD_SIZE; x++) {
			for (int y = 0; y < Board.BOARD_SIZE; y++) {
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
	 * Method to get all the possible board states from each possible move
	 * @return boardStates an ArrayList containing all the possible board states
	 */
	private ArrayList<String> getBoardStatesFromPossibleMoves() {
		
		ArrayList<String> boardStates = new ArrayList<>();
		String boardState = "";
		
		while(!possibleMovesHolder.isEmpty()) {
			Move move = possibleMovesHolder.pop();
			board.movePiece(move.getNewLocation(), move.getPiece(), true, false);
			boardState = board.getBoardState();
			
			if (!visitedStates.contains(boardState)) {
				moveHolder.push(move.getNewLocation(), move.getPiece());
				boardStates.add(boardState);
			}
		
			else {
				if (possibleMovesHolder.size() == 0 && boardStates.size() == 0) {
					moveHolder.push(move.getNewLocation(), move.getPiece());
				}
			}
			
			board.undo();
		}
		
		return boardStates;
	}
	
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
	
	
	public void solveTest() {
		for (Animal animal: this.animalsInGame) {
			this.findPossibleMoves(animal);
		}
		
		MoveStack filteredMoves = this.filterMoves();
		
		if (filteredMoves.isEmpty()) {
			System.out.println("got no moves to make\n\n");
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
		
		this.printMoves();
		Move moveToMake = this.moveHolder.pop();
		

		if (moveToMake != null) {
			System.out.println("trying" + moveToMake + "\n\n");
			board.movePiece(moveToMake.getNewLocation(), moveToMake.getPiece(), true, false);
			visitedStates.add(board.getBoardState());
		}
		
		
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
	public boolean autoSolve() {
		visitedStates.add(board.getBoardState());
		this.getAnimalsInGame();
		int counter = 1;
		
//		try {
//			this.solveTest();
////			this.solveTest();
////			this.solveTest();
////			this.solveTest();
////			this.solveTest();
////			this.solveTest();
////			this.solveTest();
////			this.solveTest();
////			this.solveTest();
////			this.solveTest();
////			
//			view.updateView();
//		} catch (Exception e) {
//			System.out.println("Error" +  e);
//		}
		
		while (!board.isGameWon()) {		
			try {
				this.solveTest();
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
