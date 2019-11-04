import java.awt.event.ActionEvent;

public class Controller {

	private Board board;
	private View view;
	private Square oldSelectSquare;
	private Square updateSquare;
	private boolean updateRequired;
	
	public Controller(Board board, View view) {
		this.board = board;
		this.view = view;
		oldSelectSquare = null;
		updateSquare = null;
		updateRequired = false;
	}
	
	/**
	 * Method to handle the event, this method gets triggered when a button is pressed
	 * @param event that takes care of the correspondence event
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
			view.turnsTaken++;
			Piece selectedPiece = square.getPiece();
			
			// If the fox was moved.
			// Update and render the entire view.
			if (selectedPiece.getType() == PieceType.FOX) {
				view.updateView();
			}
			
			// If the rabbit was moved.
			// Just update the squares rabbit hopped from and to. 
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

}
