import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

/**
 * View class is the 'View' of the MVC model It is the visual representation of
 * the JumpIN game
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class View extends ViewBuilder implements BoardListener {

	private JFrame frame;
	private Board board;
	private Controller controller;
	private ArrayList<Square> highlightedSquares;

	/**
	 * Constructor to initialize the instance variables
	 */
	public View() {
		this.board = new Board(1);
		this.controller = new Controller(board, this);
		this.highlightedSquares = new ArrayList<>();
		this.init();
	}
	
	/**
	 * Constructor to initialize the instance variables 
	 * @param board
	 */
	public View(Board board) {
		this.board = board;
		this.controller = new Controller(board, this);
		this.highlightedSquares = new ArrayList<>();
		this.init();
	}

	/**
	 * Method to call the initFrame, initMenu and the initView methods
	 */
	private void init() {
		this.initFrame();
		this.initMenu();
		this.initView();
		board.setBoardListener(this);
	}

	/**
	 * Method to initialize the Frame
	 */
	private void initFrame() {
		frame = new JFrame("JumpIN");
		GridLayout grid = new GridLayout(Constants.BOARD_SIZE, Constants.BOARD_SIZE);
		frame.setLayout(grid);
		frame.setSize(Constants.VIEW_DIMENSION);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/**
	 * Method to to initialize the Menu
	 */
	private void initMenu() {

		// Menu Bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);

		// File Menu and Items
		JMenu file = new JMenu("File");
		file.add(this.createMenuItem("Save", (event) -> this.displaySaveDialog()));
		file.add(this.createMenuItem("Load", (event) -> this.displayLoadDialog()));
		file.add(this.createMenuItem("Exit", (event) -> System.exit(0)));

		// Edit Menu and Items
		JMenu edit = new JMenu("Edit");
		edit.add(this.createMenuItem("Undo", (event) -> controller.undo()));
		edit.add(this.createMenuItem("Redo", (event) -> controller.redo()));

		// Help Menu and Items
		JMenu help = new JMenu("Help");
		help.add(this.createMenuItem("Instructions",
				(event) -> JOptionPane.showMessageDialog(frame, Constants.GAME_INSTRUCTIONS)));

		// Level Option Menu and Items
		JMenu levelSelect = new JMenu("Level Options");
		levelSelect.add(this.createMenuItem("Reset Level", (event) -> this.reset()));
		levelSelect.add(this.createMenuItem("Level Select", (event) -> this.levelSelect()));
		levelSelect.add(this.createMenuItem("Auto-Solve", (event) -> controller.autoSolver()));
		levelSelect.add(this.createMenuItem("Level builder", (event) -> this.showLevelBuilderView()));

		// Adding menu into the menu bar.
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		menuBar.add(levelSelect);

		// Setting the menu bar.
		frame.setJMenuBar(menuBar);
	}
	
	/**
	 * Method to listen to all the board events dispatched from the model (board)
	 */
	@Override
	public void BoardEventHandler(Constants.BoardEventType eventType, Square square) {
		switch(eventType) {
		case GameWon:
			this.displayLevelCompeletePopup();
			break;
		case clearHighlight:
			this.unhighlightAllSquares();
			break;
		case updateView:
			this.updateView(board);
			break;
		case highlightSquare:
			if (square != null) {
				this.highlightSelectedSquare(square);
			}
			break;
		default:
			break;
		}	
	}
	
	/**
	 * Method which shows the GUI for the level builder
	 */	
	private void showLevelBuilderView() {
		LevelBuilderView levelBuilderView = new LevelBuilderView();
		levelBuilderView.run();
	}

	/**
	 * Method which resets the current level to its initial state Resets the level
	 */
	private void reset() {
		JFrame popupFrame = new JFrame();
		int option = JOptionPane.showConfirmDialog(popupFrame,
				"Are you sure you want to reset level " + board.getLevel());

		if (option == 0) {
			this.resetView();
		}
	}

	/**
	 * Method to update the view after reset.
	 */
	public void resetView() {
		board.changeLevel(board.getLevel());
		this.setButtonsEnabled(true);
		this.updateView(board);
	}

	/**
	 * Method that allows the user to select the level of their choice
	 */
	public void levelSelect() {	
		Integer[] possibilities = controller.getLevelOptions();
		
		Integer level = (Integer) JOptionPane.showInputDialog(frame, "What Level would you like to play:",
				"Level Select", JOptionPane.QUESTION_MESSAGE, null, possibilities, 1);

		if (level != null) {
			boolean valid = controller.levelSelect(level);
			if (!valid) {
				JOptionPane.showMessageDialog(frame, "level" + level + ".xml does not contain a valid level.");
				return;
			}
			this.setButtonsEnabled(true);
			this.updateView(board);
		}

	}

	/**
	 * Displays a pop up which takes a file name from the user
	 * If the file name is valid it calls controller.save(fileName)
	 * which saves the state of the board
	 */
	public void displaySaveDialog() {
		String fileName = JOptionPane.showInputDialog(frame, "Enter file name.\nNo special character allowed.");
		if (fileName != null) {
			if (controller.save(fileName)) {
				JOptionPane.showMessageDialog(frame, "Your file has been saved.");
			} else {
				JOptionPane.showMessageDialog(frame, "Your file could not be saved. Please try again.");
			}
		}
	}

	/**
	 * Displays a pop up with all available save files 
	 * to load. When the user selects a file it calls controller.load(loadFile)
	 * which loads the save
	 */
	public void displayLoadDialog() {
		String[] loadOptions = controller.getLoadOptions();
		
		if (loadOptions.length != 0) {
			String loadFile = (String) JOptionPane.showInputDialog(frame, "Which save would you like to load?", "Load", 
					JOptionPane.QUESTION_MESSAGE, null, loadOptions, null);
			Board savedBoard = controller.load(loadFile);
			if (savedBoard != null) {
				this.setBoard(savedBoard);
			}
		} else {
			JOptionPane.showMessageDialog(frame, "No save data to load.");
		}
	}

	/**
	 * Method to enable or disable the buttons on the squares
	 * @param enabled true or false to enable to disable the buttons
	 */
	private void setButtonsEnabled(boolean enabled) {
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			for (int x = 0; x < Constants.BOARD_SIZE; x++) {
				board.squares[x][y].setEnabled(enabled);
			}
		}
	}

	/**
	 * Method which initializes the View of the game
	 */
	private void initView() {
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			for (int x = 0; x < Constants.BOARD_SIZE; x++) {
				frame.add(this.createButton(board.squares[x][y], x % 2 == 0 && y % 2 == 0, (event) -> controller.eventHandler(event)));
			}
		}
	}

	/**
	 * Method to set the Frame's visibility
	 */
	private void run() {
		frame.setVisible(true);
	}


	/**
	 * Method to display the level complete popup dialog message
	 */

	protected void displayLevelCompeletePopup() {
		JFrame popupFrame = new JFrame();
		String message = "";

		if (board.getLevel() < Constants.TOTAL_LEVELS) {
			message = "Congratulations on completing Level " + board.getLevel() + "!";
			message += "\n";
			message += "Press OK to play level " + (board.getLevel() + 1);
			JOptionPane.showMessageDialog(popupFrame, message);
			boolean valid = board.changeLevel(board.getLevel() + 1);
			if (!valid) {
				JOptionPane.showMessageDialog(frame, "level" + board.getLevel() + ".xml does not contain a valid level.");
			}
			this.updateView(board);
		}

		else {
			message = "Congratulations on completing the game!";
			JOptionPane.showMessageDialog(popupFrame, message);
			this.setButtonsEnabled(false);
		}
	}

	/**
	 * Method to highlight the selected square so the player can see the selected
	 * piece
	 * @param square of which the color is to be set
	 */
	protected void highlightSelectedSquare(Square square) {
		square.setBackground(Constants.SELECTED_SQUARE_COLOR);
		highlightedSquares.add(square);
	}

	/**
	 * Method to unhighlight all highlighted squares in the view
	 */
	protected void unhighlightAllSquares() {
		for (Square square : highlightedSquares) {
			Location squareLocation = square.getLoc();
			boolean cornerPiece = squareLocation.getX() % 2 == 0 && squareLocation.getY() % 2 == 0;
			square.setBackground(cornerPiece ? Constants.CORNER_SQUARE_COLOR : Constants.MAIN_SQUARE_COLOR);
		}

		highlightedSquares.clear();
	}

	/**
	 * Method to clear the highlight of the selected square
	 * @param square which is to be cleared from the highlight
	 */
	protected void clearHighlight(Square square) {
		Location loc = square.getLoc();
		boolean cornerPiece = loc.getX() % 2 == 0 && loc.getY() % 2 == 0;
		square.setBackground(cornerPiece ? Constants.CORNER_SQUARE_COLOR : Constants.MAIN_SQUARE_COLOR);
	}
	
	/**
	 * Creates a new view based off a new board
	 * @param {Board} newBoard The board that will be used to create the new view.
	 */
	public void setBoard(Board newBoard) {
		frame.dispose();
		this.board = null;
		this.controller = null;
		this.board = newBoard;
		this.controller = new Controller(newBoard, this);
		this.init();
		this.run();
	}

	/**
	 * Main method used to run the game and display the view
	 * @param args
	 */
	public static void main(String[] args) {
		View view = new View();
		view.run();
	}
}
