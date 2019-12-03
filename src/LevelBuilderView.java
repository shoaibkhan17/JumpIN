import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

/**
 * 	Level builder view class
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class LevelBuilderView extends ViewBuilder {
	private JFrame frame;
	private Board board;
	private LevelBuilder builder;

	/**
	 * Constructor to initialize the instance variables
	 */
	public LevelBuilderView() {
		board = new Board();
		builder = new LevelBuilder(board);
		this.init();
	}

	/**
	 * Method to call the initFrame, initMenu and the initView methods
	 */
	private void init() {
		this.initFrame();
		this.initView();
		this.initMenu();
	}

	/**
	 * Method to initialize the Frame
	 */
	private void initFrame() {
		frame = new JFrame("Level Builder");
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
		file.add(this.createMenuItem("Reset", (event) -> this.reset()));
		file.add(this.createMenuItem("Save", (event) -> this.levelBuilderSaveDialog()));
		file.add(this.createMenuItem("Help", (event) -> this.showHelpDialog()));
		file.add(this.createMenuItem("Exit", (event) -> System.exit(0)));

		// Adding menu into the menu bar.
		menuBar.add(file);

		// Setting the menu bar.
		frame.setJMenuBar(menuBar);
	}
	
	public void showHelpDialog() {
		JOptionPane.showMessageDialog(frame, Constants.LEVEL_BUILDER_INSTRUCTIONS);
	}

	/**
	 * Method which reset and delete all the pieces. 
	 */
	private void reset() {
		JFrame popupFrame = new JFrame();
		int option = JOptionPane.showConfirmDialog(popupFrame,
				"Are you sure discard all the pieces placed?");

		if (option == 0) {
			this.resetView();
		}
	}

	/**
	 * Method to update the view after reset.
	 */
	public void resetView() {
		board.changeLevel(board.getLevel());
		this.updateView(board);
	}

	/**
	 * Displays a pop up which takes a file name from the user.
	 * If the file name is valid, it saves the xml content of the level to an xml file.
	 */
	public void levelBuilderSaveDialog() {
		String level = JOptionPane.showInputDialog(frame, "Enter a level number.\nOnly numeric value accepted.");
		if (level != null) {
			try {
				int levelNumber = Integer.parseInt(level);			
				if (builder.saveFile(levelNumber)) {
					JOptionPane.showMessageDialog(frame, "The custom level has been succesfully saved.\nTo play the level,"
							+ " select the level under the level options in the main game.");
				}				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frame, "File cannot be saved. Only number input expected.");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "File cannot be saved.\n" + e.getLocalizedMessage());
			}
		}
	}

	/**
	 * Method which initializes the main view of the level builder
	 */
	private void initView() {
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			for (int x = 0; x < Constants.BOARD_SIZE; x++) {
				frame.add(this.createButton(board.squares[x][y], x % 2 == 0 && y % 2 == 0, (event) -> this.buttonClickHandler(event)));
			}
		}
	}

	/**
	 * Method to set the Frame's visibility
	 */
	public void run() {
		frame.setVisible(true);
	}
	
	/**
	 * Button handler method for the squares.
	 * @param event
	 */
	private void buttonClickHandler(ActionEvent event) {
		Square square = (Square) event.getSource();
		builder.buildHandler(square);
		this.updateView(board);
	}
}
