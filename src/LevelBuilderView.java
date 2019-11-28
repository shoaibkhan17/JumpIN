import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
	private Controller controller;
	private ArrayList<Square> highlightedSquares;
	private ArrayList<Location> possibleHoleLocations;

	private final static String GAME_INSTRUCTIONS = "Basic Information\r\n"
			+ "- Currently five levels are developed.\r\n"
			+ "- The goal of the game is to place all the rabbits inside the holes.\r\n"
			+ "- Rabbits can jump over objects, including mushrooms, foxes and other rabbits\r\n"
			+ "- Foxes can slide on empty spaces in the direction that the fox is oriented \r\n";

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
	 * Constructor to initialize the instance variables
	 */
	public LevelBuilderView() {
		this.board = new Board();
        possibleHoleLocations = new ArrayList<>();
        possibleHoleLocations.add(new Location(0, 0));
        possibleHoleLocations.add(new Location(4, 0));
        possibleHoleLocations.add(new Location(2, 2));
        possibleHoleLocations.add(new Location(0, 4));
        possibleHoleLocations.add(new Location(4, 4));
//		this.controller = new Controller(board, this);
//		this.highlightedSquares = new ArrayList<>();
		this.init();
	}
	
	/**
	 * Constructor to initialize the instance variables 
	 * @param board
	 */
	public LevelBuilderView(Board board) {
		this.board = board;
        possibleHoleLocations = new ArrayList<>();
        possibleHoleLocations.add(new Location(0, 0));
        possibleHoleLocations.add(new Location(4, 0));
        possibleHoleLocations.add(new Location(2, 2));
        possibleHoleLocations.add(new Location(0, 4));
        possibleHoleLocations.add(new Location(4, 4));
//		this.controller = new Controller(board, this);
//		this.highlightedSquares = new ArrayList<>();
		this.init();
	}

	/**
	 * Method to call the initFrame, initMenu and the initView methods
	 */
	private void init() {
		this.initFrame();
		this.initToolbar();
		this.initView();
		this.initMenu();
	}

	/**
	 * Method to initialize the Frame
	 */
	private void initFrame() {
		frame = new JFrame("Level Builder");
		frame.setSize(VIEW_DIMENSION);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * 
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
	 * @param item
	 * @return
	 */
	private JLabel addItemToToolbar(String item) {
		var listener = new DragMouseAdapter();
		String path = "src/assets/" + item + ".png";
		ImageIcon icon = new ImageIcon(path);
		JLabel label = new JLabel(icon);
		label.setTransferHandler(new DragAndDropHandler(false));
		label.addMouseListener(listener);
		return label;
	}
	
    private class DragMouseAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {

            var c = (JComponent) e.getSource();
            var handler = c.getTransferHandler();
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
		file.add(this.createMenuItem("Save", (event) -> this.displaySaveDialog()));
		file.add(this.createMenuItem("Load", null));

		// Adding menu into the menu bar.
		menuBar.add(file);

		// Setting the menu bar.
		frame.setJMenuBar(menuBar);
	}

	/**
	 * Method which reset and delete all the pieces. 
	 */
	private void reset() {
		JFrame popupFrame = new JFrame();
		int option = JOptionPane.showConfirmDialog(popupFrame,
				"Are you sure delete all the pieces placed?");

		if (option == 0) {
			this.resetView();
		}
	}

	/**
	 * Method to update the view after reset.
	 */
	public void resetView() {
		board.changeLevel(board.getLevel());
		this.updateView();
	}


	/**
	 * Displays a pop up which takes a file name from the user.
	 * If the file name is valid, it saves the xml content of the level to an xml file.
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
	 * Displays a pop up with all available saved levels files 
	 * Loads the selected level.
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
	 * Method which initializes the main view of the level builder.
	 */
	private void initView() {
		JPanel mainView = new JPanel();
		mainView.setLayout(new GridLayout(Board.BOARD_SIZE, Board.BOARD_SIZE));
		
		for (int y = 0; y < Board.BOARD_SIZE; y++) {
			for (int x = 0; x < Board.BOARD_SIZE; x++) {
				mainView.add(this.createButton(board.squares[x][y], x % 2 == 0 && y % 2 == 0));
			}
		}
		
		frame.add(mainView, BorderLayout.CENTER);
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
		square.setBackground(cornerPiece ? CORNER_SQUARE_COLOR : MAIN_SQUARE_COLOR);
		square.setBorder(COMPOUND);
		square.setTransferHandler(new DragAndDropHandler(true));
//		square.addActionListener((event) -> this.clearIcon(event));
		this.imageHandler(square);
		return square;
	}

	/**
	 * Method which updates the level builder view
	 */
	protected void updateView() {
		for (int y = 0; y < Board.BOARD_SIZE; y++) {
			for (int x = 0; x < Board.BOARD_SIZE; x++) {
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

	/**
	 * Main method for testing.
	 * @param args
	 */
	public static void main(String[] args) {
		LevelBuilderView levelBuilderView = new LevelBuilderView();
		levelBuilderView.run();
	}
}
