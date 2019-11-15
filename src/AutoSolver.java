import java.util.ArrayList;

public class AutoSolver {
	private enum Directions {UP, DOWN, LEFT, RIGHT}
	private Board board;
	private Square[][] squares;
	private View view;
	
	public AutoSolver(Board board, View view) {
		this.board = board;
		squares = board.getSquares();
		this.view = view;
	}
	
	private Location possibleMoveBasedOnDirection(Animal animal, Directions direction) {
		Location location = animal.getCurrentLocation();
		Piece piece = animal.getPiece();
		int i;
		boolean horizonal;
		int limit;
		Location possibleMove = null;
		Rabbit rabbit = null;
		
		if (piece == null) {
			return possibleMove;
		}
		
		else if (piece.getType() == PieceType.RABBIT) {
			rabbit = (Rabbit) piece;
		}
		
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
			if (rabbit.canMove(location, newLocation, board)) {
				possibleMove = new Location();
				possibleMove.setLocation(newLocation);
			}
			
			i++;
		}
		
		return possibleMove;
	}
	
	private void findPossibleMoves(Animal animal) {
		for (Directions direction: Directions.values()) {
			Location possibleMove = this.possibleMoveBasedOnDirection(animal, direction);
			if (possibleMove != null) {
				animal.addPossibleMoves(possibleMove);
			}
		}
	}
	
	private ArrayList<Animal> getAnimalsWithLocation() {
		ArrayList<Animal> animals = new ArrayList<>(); 
		Piece piece = null;
		int counter = 1;
		for (int x = 0; x < Board.BOARD_SIZE; x++) {
			for (int y = 0; y < Board.BOARD_SIZE; y++) {
				if (squares[x][y].getPiece() != null) {
					if (squares[x][y].getPiece().getType() == PieceType.RABBIT) {
						piece = squares[x][y].getPiece();
						Animal animal = new Animal(piece, squares[x][y].getLoc(), counter++);
						animals.add(animal);
					}
					else if (squares[x][y].getPiece().getType() == PieceType.HOLE) {
						Hole hole = (Hole) squares[x][y].getPiece();
						
						if (!hole.isOccupied()) {
							continue;
						}
								
						piece = hole.getPiece();
						Animal animal = new Animal(piece, squares[x][y].getLoc(), counter++);
						animals.add(animal);
					}
				}
			}
		}
		
		return animals;
	}
	
	private void moving(Animal animal) {
		Location movingLocation = animal.getPossibleMove();
		if (movingLocation != null) {
			System.out.println("Moving " + animal.getAnimalNameAndID() + " to " + movingLocation);
			Piece piece = animal.getPiece();
			Location currentLocation = animal.getCurrentLocation();
			if (this.board.movePiece(currentLocation, movingLocation, piece, false, false)) {
				animal.setCurrentLocation(movingLocation);
				this.view.updateView();
			}
		}
	}
	
	public void solve(int number, ArrayList<Animal> animals) {
		System.out.println("----> " + number + " <----");
		for (Animal animal: animals) {
			this.findPossibleMoves(animal);
			animal.print();
		}
		
		for (Animal animal: animals) {
			this.moving(animal);
		}
		
		System.out.println("Game won: " + board.isGameWon() + "\n");
	}
	
	public void autoSolve() {
		ArrayList<Animal> animals = this.getAnimalsWithLocation();
		int counter = 1;
		while (!board.isGameWon()) {
			this.solve(counter++, animals);
		}
		
		view.displayLevelCompeletePopup();

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
