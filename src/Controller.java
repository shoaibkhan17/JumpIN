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
			
			Piece selectedPiece = square.getPiece();
			if (selectedPiece.getType() == PieceType.FOX) {
				Fox fox = (Fox) selectedPiece;
				Location foxBodyLocation = fox.getBodyLocation();
				updateSquare = board.getSquareAtLocation(foxBodyLocation);
				updateRequired = true;
			}
			
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
			
			if (updateRequired) {
				updateRequired = false;
				view.imageHandler(updateSquare, false);
			}
			
			if (board.isGameWon()) {
				view.displayLevelCompeletePopup();
			}
		}
	}

}
