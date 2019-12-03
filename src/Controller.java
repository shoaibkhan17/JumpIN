import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Controller class which holds the controller for the game
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class Controller implements Runnable {

	private Board board;
	private View view;
	
	/**
	 * Default constructor initializes instance variables
	 * 
	 * @param board of the game
	 * @param view instance of view
	 */
	public Controller(Board board, View view) {
		this.board = board;
		this.view = view;
	}

	/**
	 * Controller method to handle the event, this method gets triggered when a
	 * button is pressed
	 * 
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
	 * Controller method to select a square or a piece, this method gets triggered
	 * when a button is pressed in this case by 'mouse"
	 * 
	 * @param event that takes care of the correspondence event
	 */
	public void select(ActionEvent event) {
		Square square = (Square) event.getSource();
		Location location = square.getLoc();
		board.selectPiece(location);
	}

	/**
	 * Controller method to move a piece from its location to a different location
	 * 
	 * @param event which handles what happens after the button is pressed
	 */
	public void move(ActionEvent event) {
		Square square = (Square) event.getSource();
		Location location = square.getLoc();
		board.move(location);
	}

	/**
	 * Controller method for auto solving the game
	 */
	public void autoSolver() {
		Thread thread = new Thread(this);
		thread.start();
	}

	/**
	 * Controller undo method to undo a move
	 */
	public void undo() {
		board.undo();
	}

	/**
	 * Controller redo method to redo a move
	 */
	public void redo() {
		board.redo();
	}

	/**
	 * Method which lets the user select the level
	 * @param level to be selected.
	 * @returns true if it a valid solvable level. 
	 * 
	 */
	public boolean levelSelect(Integer level) {
		boolean valid = board.changeLevel(level);
		return valid;
	}

	/**
	 * Saves the state of the board in a file in the SavedGames folder.
	 * It does this by using serialization to write the state of the
	 * board to file in the SavedGames folder
	 * @param {String} fileName The name the save file will be saved as, given by user
	 * @throws IOException if exception occurs
	 * @return true if the save was successful
	 */
	public boolean save(String fileName) {
		try {
			File file = new File(Constants.SAVED_GAME_PATH + fileName);
			FileOutputStream writer = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(writer);		
			out.writeObject(board);		
			out.close();
			writer.close();			
			return true;
		} catch (IOException e1) {
			return false;
		}
	}

	/**
	 * Loads a save file from the SavedGames folder. It does this 
	 * by deserializing the board from the file then calling view.setBoard()
	 * 
	 * @param {String} fileName The name of the file to be loaded
	 * @throws IOException if exception occurs
     * @throws ClassNotFoundException if exception occurs
	 * @returns the saved board
	 */
	public Board load(String fileName) {
		try {
			FileInputStream file = new FileInputStream(Constants.SAVED_GAME_PATH + fileName);
			ObjectInputStream in = new ObjectInputStream(file);

			Board savedBoard = (Board) in.readObject();
			
			in.close();
			file.close();
			
			return savedBoard;
		}

		catch (IOException ex) {
			System.out.println("Serialized saved object is not in date with the current object.");
			return null;
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
			return null;
		}
	}

	/**
	 * Searches the SavedGames folder for all save files
	 * and returns an array of string containing the save
	 * file names.
	 * @return {String[]} loadOptions: The name of all possible save that can be loaded
	 */
	public String[] getLoadOptions() {
		File saveDirectory = new File(Constants.SAVED_GAME_PATH);
		File[] savedGameFiles = saveDirectory.listFiles();
		String[] loadOptions = new String[savedGameFiles.length];
		for (int i = 0; i < savedGameFiles.length; i++) {
			loadOptions[i] = savedGameFiles[i].getName();
		}
		return loadOptions;
	}
	
	/**
	 * Method to get the level options for the level selection
	 * @return Interger[] of levels
	 */
	public Integer[] getLevelOptions() {
		File saveDirectory = new File(Constants.SAVED_LEVEL_PATH);
		File[] savedGameFiles = saveDirectory.listFiles();
		Integer levels[] = new Integer[savedGameFiles.length];
		for (int i = 0; i < savedGameFiles.length; i++) {
			String fileName = savedGameFiles[i].getName();		
			fileName = fileName.replace("level", "");
			fileName = fileName.replace(".xml", "");
			levels[i] = Integer.parseInt(fileName);
		}
		return levels;
	}

	/**
	 * Method that runs on a different thread.
	 * Run the auto solver loop on this thread.
	 * So, the view can update and render on the other thread.
	 */
	@Override
	public void run() {
		AutoSolver solver = new AutoSolver(board, view);
		boolean sucessful = solver.autoSolve(Constants.SLEEP_TIMER);
		if (sucessful) {
			board.dispatchStandardBoardEvents();
		}
	}
}
