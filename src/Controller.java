import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

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
	private static final String SAVED_GAME_PATH = "SavedGames/";
	
	/**
	 * Default constructor initializes instance variables
	 * 
	 * @param board
	 *            of the game
	 * @param view
	 *            instance of view
	 */
	public Controller(Board board, View view) {
		this.board = board;
		this.view = view;
		oldSelectSquare = null;
	}

	/**
	 * Controller method to handle the event, this method gets triggered when a
	 * button is pressed
	 * 
	 * @param event
	 *            of type ActionEvent that takes care of the correspondence event
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
	 * Controller method to select a square or a piece, this method gets triggered
	 * when a button is pressed in this case by 'mouse"
	 * 
	 * @param event
	 *            that takes care of the correspondence event
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
	 * Controller method to move a piece from its location to a different location
	 * 
	 * @param event
	 *            which handles what happens after the button is pressed
	 */
	public void move(ActionEvent event) {
		Square square = (Square) event.getSource();
		Location location = square.getLoc();
		if (board.move(location)) {
			Piece selectedPiece = square.getPiece();

			if (selectedPiece == null) {
				return;
			}

			// If the fox was moved
			// Update and render the entire view
			else if (selectedPiece.getType() == PieceType.FOX) {
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
	 * Controller method for auto solving the game
	 */
	public void autoSolver() {
		view.resetView();
		AutoSolver solver = new AutoSolver(board, view);
		boolean sucessful = solver.autoSolve();
		if (sucessful) {
			view.displayLevelCompeletePopup();
		}
	}

	/**
	 * Controller undo method to undo a move
	 */
	public void undo() {
		board.undo();
		view.updateView();
	}

	/**
	 * Controller redo method to redo a move
	 */
	public void redo() {
		board.redo();
		view.updateView();
	}

	/**
	 * Method which lets the user select the level
	 * 
	 * @param level
	 *            to be selected
	 */
	public void levelSelect(Integer level) {
		board.changeLevel(level);
		view.updateView();
	}

	/**
	 * Method to save the game Will be implemented in milestone 4
	 */
	public void save(String fileName) {
		File f = new File(SAVED_GAME_PATH + fileName);
		String str = "Fox And Rabbits Save";
		try {
			FileOutputStream writer = new FileOutputStream(f);
			writer.write(str.getBytes());
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Method to load the game Will be implemented in milestone 4
	 * @param fileName 
	 */
	public void load(String fileName) {
		System.out.println(fileName);
	}
	
	public String[] getLoadOptions(){
		File saveDirectory = new File(SAVED_GAME_PATH);
		File[] savedGameFiles = saveDirectory.listFiles();
		String[] loadOptions = new String[savedGameFiles.length];
		for(int i = 0; i < savedGameFiles.length; i++) {
			loadOptions[i] = savedGameFiles[i].getName();
		}
		return loadOptions;
	}

}
