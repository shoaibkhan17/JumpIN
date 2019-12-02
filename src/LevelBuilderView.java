import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 	Level builder view class
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

public class LevelBuilderView {
	private JFrame frame;
	private Board board;
	private LevelBuilder builder;

	/**
	 * Styling variables
	 */
	private final static Color MAIN_SQUARE_COLOR = new Color(2, 171, 80);
	private final static Color TOOLBAR_COLOR = new Color(40, 90, 40);
	private final static Color CORNER_SQUARE_COLOR = new Color(37, 177, 73);
	private final static Color SELECTED_SQUARE_COLOR = new Color(51, 204, 255);
	private final static Border LINE = new LineBorder(Color.white);
	private final static Border MARGIN = new EmptyBorder(5, 15, 5, 15);
	private final static Border COMPOUND = new CompoundBorder(LINE, MARGIN);
	private final static Dimension VIEW_DIMENSION = new Dimension(600, 550);

	/**
	 * Default Constructor initializing instance variables
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
	 * Method to initialize the tool bar
	 */
	private void initToolbar() {
		JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
		toolbar.setBackground(TOOLBAR_COLOR);
		toolbar.setFloatable(false);
        toolbar.add(this.addItemToToolbar("mushroom"));
        toolbar.add(this.addItemToToolbar("rabbitbrown"));
        toolbar.add(this.addItemToToolbar("rabbitgray"));
        toolbar.add(this.addItemToToolbar("rabbitwhite"));
        toolbar.add(this.addItemToToolbar("foxheadhorizontal"));
        toolbar.add(this.addItemToToolbar("foxheadvertical"));
        frame.add(toolbar, BorderLayout.WEST);
	}
	
	/**
	 * 
	 * @param item to be added to the tool bar
	 * @return label 
	 */
	private JLabel addItemToToolbar(String item) {
		DragMouseAdapter listener = new DragMouseAdapter();
		String path = "src/assets/" + item + ".png";
		ImageIcon icon = new ImageIcon(path);
		JLabel label = new JLabel(icon);
		label.setTransferHandler(new DragAndDropHandler(false));
		label.addMouseListener(listener);
		return label;
	}
	
    private class DragMouseAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {

            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);

        }
        
        public void mouseReleased(MouseEvent e) {
        	  JComponent c = (JComponent) e.getSource();
        	  System.out.println(c);
        	  
//        	  if ()
//              var source = (JLabel) e.getSource();
//              var handler = c.getTransferHandler();
//              handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
        
        public void mouseClicked(MouseEvent e) {}
    }
	
	/**
	 * Method to create the menu items.
	 * @param name
	 * @param actionListener
	 * @return
	 */
	private JMenuItem createMenuItem(String name, ActionListener actionListener) {
		JMenuItem item = new JMenuItem(name);
		item.setBackground(Color.LIGHT_GRAY);
		item.addActionListener(actionListener);
		return item;
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
	 * Method which reset and delete all the pieces
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
	 * Method to update the view after reset
	 */
	public void resetView() {
		board.changeLevel(board.getLevel());
		this.updateView();
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
	 * Displays a pop up with all available saved levels files 
	 * Loads the selected level
	 */
	public void displayLoadDialog() {
		String[] loadOptions = controller.getLoadOptions();
		
		if (loadOptions.length != 0) {
			String loadFile = (String) JOptionPane.showInputDialog(frame, "Which save would you like to load?", "Load", 
					JOptionPane.QUESTION_MESSAGE, null, loadOptions, null);
			controller.load(loadFile);
		} else {
			JOptionPane.showMessageDialog(frame, "No save data to load.");
		}
	}

	/**
	 * Method which initializes the main view of the level builder
	 */
	private void initView() {
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			for (int x = 0; x < Constants.BOARD_SIZE; x++) {
				frame.add(this.createButton(board.squares[x][y], x % 2 == 0 && y % 2 == 0));
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
	 * Method to create Button on the GUI
	 * @param square on the board used to set different attributes
	 * @param cornerPiece variable used to check if it is a corner piece
	 * @return square on the board
	 */
	private JButton createButton(Square square, boolean cornerPiece) {
		square.setBorderPainted(cornerPiece);
		square.setBackground(cornerPiece ? Constants.CORNER_SQUARE_COLOR : Constants.MAIN_SQUARE_COLOR);
		square.setBorder(Constants.COMPOUND);
		square.addActionListener((event) -> this.buttonClickHandler(event));
		this.imageHandler(square);
		return square;
	}
	
	/**
	 * Button handler method for the squares.
	 * @param event
	 */
	private void buttonClickHandler(ActionEvent event) {
		Square square = (Square) event.getSource();
		builder.buildHandler(square);
		this.updateView();
	}

	/**
	 * Method which updates the level builder view
	 */
	protected void updateView() {
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			for (int x = 0; x < Constants.BOARD_SIZE; x++) {
				this.imageHandler(board.squares[x][y]);
			}
		}
	}

	/**
	 * Method to handle the image and the implementation of the switch cases
	 * @param square on which the image is placed on, used to set the icon
	 */

	protected void imageHandler(Square square) {
		String path = "src/assets/";
		ImageIcon icon;
		Piece piece = square.getPiece();

		if (piece == null) {
			icon = new ImageIcon(path + "empty.png");
			square.setIcon(icon);
			return;
		}

		switch (piece.getType()) {
		case RABBIT:
			Rabbit rabbit = (Rabbit) piece;
			icon = new ImageIcon(path + "rabbit" + rabbit.rabbitColor + ".png");
			square.setIcon(icon);
			break;

		case MUSHROOM:
			icon = new ImageIcon(path + "mushroom.png");
			square.setIcon(icon);
			break;

		case HOLE:
			Hole hole = (Hole) piece;
			Piece innerPiece = hole.getPiece();
			ImageIcon frontIcon = null;

			if (innerPiece != null) {
				Rabbit innerRabbit = (Rabbit) innerPiece;
				frontIcon = new ImageIcon(path + "rabbit" + innerRabbit.rabbitColor + ".png");
			}

			icon = new ImageIcon(path + "hole.png");
			CombinedIcon combiedIcon = new CombinedIcon(frontIcon, icon);
			square.setIcon(combiedIcon);
			break;

		case FOX:
			Fox fox = (Fox) piece;
			String direction = fox.isHorizontal() ? "Horizontal" : "Vertical";
			String bodyPart = fox.isTail() ? "Tail" : "Head";
			icon = new ImageIcon(path + "fox" + bodyPart + direction + ".png");
			square.setIcon(icon);
			break;

		default:
			break;
		}
	}

//	/**
//	 * Main method for testing.
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		LevelBuilderView levelBuilderView = new LevelBuilderView();
//		levelBuilderView.run();
//	}
}
