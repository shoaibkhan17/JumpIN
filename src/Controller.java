import java.awt.event.ActionEvent;
/**
 * Controller class which holds the controller for the game
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class Controller {

	private Board board;
	private View view;
	private Square oldSelectSquare;
	
	/**
	 * Default constructor initializes instance variables
	 * @param board of the game
	 * @param view instance of view
	 */
	public Controller(Board board, View view) {
		this.board = board;
		this.view = view;
		oldSelectSquare = null;
	}
	
	/**
	 * Method to handle the event, this method gets triggered when a button is pressed
	 * @param event of type ActionEvent that takes care of the correspondence event
	 */
	public void eventHandler(ActionEvent event) {
		if (board.selectedPiece == null) {
			this.select(event);
		}
		
		else {
			this.move(event);
		}
	}
	
	/**
	 * Method to select a square or a piece, this method gets triggered when a button is pressed
	 * in this case by 'mouse"
	 * @param event the event that takes care of the correspondence event
	 */
	public void select(ActionEvent event) {
		Square square = (Square) event.getSource();
		Location location = square.getLoc();
		if (board.selectPiece(location)) {
			view.highlightSelectedSquare(square);
			oldSelectSquare = square;
			
			Piece selectedPiece = square.getPiece();
			
			// If the fox was moved.
			// Update and render the entire view.
			if (selectedPiece.getType() == PieceType.FOX) {
				Fox fox = (Fox) selectedPiece;
				Square foxBodySquare = board.getSquareAtLocation(fox.getBodyLocation());
				view.highlightSelectedSquare(foxBodySquare);
			}
		}
	}
	
	/**
	 * Method to move a piece from its location to a different location
	 * @param event which handles what happens after the button is pressed
	 */
	public void move(ActionEvent event) {
		Square square = (Square) event.getSource();
		Location location = square.getLoc();
		if (board.move(location)) {
			Piece selectedPiece = square.getPiece();
			
			// If the fox was moved
			// Update and render the entire view
			if (selectedPiece.getType() == PieceType.FOX) {
				view.updateView();
			}
			
			// If the rabbit was moved
			// Just update the squares rabbit hopped from and to
			else {
				view.imageHandler(oldSelectSquare);
				view.imageHandler(square);
			}
			
			view.unhighlightAllSquares();
			
			if (board.isGameWon()) {
				view.displayLevelCompeletePopup();
			}
		}
	}
	
	/**
	 * Controller function for auto solving the game.
	 */
	public void autoSolver() {
		System.out.println("AUTO SOLVING THE GAME");
	}

	/**
	 * Controller undo function to undo a move.
	 */
	public void undo() {
		board.undo();
		view.updateView();
	}
	
	/**
	 * Controller redo function to redo a move.
	 */
	public void redo() {
		board.redo();
		view.updateView();
	}
}
