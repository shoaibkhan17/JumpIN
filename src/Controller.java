import java.awt.event.ActionEvent;

public class Controller {

	private Board board;
	private View view;
	private Square oldSelectSquare;
	
	public Controller(Board board, View view) {
		this.board = board;
		this.view = view;
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
			board.printBoard();
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
			view.clearHighlight(oldSelectSquare);
			view.imageHandler(oldSelectSquare, false);
			view.imageHandler(square, false);
			
			if (board.isGameWon()) {
				view.displayLevelCompeletePopup();
			}
		}
	}

}
