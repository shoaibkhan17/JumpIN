import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

/**
 * View class is the 'View' of the MVC model
 * It is the visual representation of the JumpIN game
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

class View {

	private JFrame frame;
	private Board board;
	private Controller controller;
	private ArrayList<Square> highlightedSquares;
	protected int turnsTaken;
	
	/**
	 * Styling variables
	 */
	private final static Color MAIN_SQUARE_COLOR = new Color(2, 171, 80);
	private final static Color CORNER_SQUARE_COLOR = new Color(37, 177, 73);
	private final static Color SELECTED_SQUARE_COLOR = new Color(51, 204, 255);
	private final static Border LINE = new LineBorder(Color.white);
	private final static Border MARGIN = new EmptyBorder(5, 15, 5, 15);
	private final static Border COMPOUND = new CompoundBorder(LINE, MARGIN);
	private final static Dimension VIEW_DIMENSION = new Dimension(500, 550);
	
	/**
	 * Constructor to initialize the instance variables
	 */
	public View(Board myBoard) {
		super();
		this.board = myBoard;
		turnsTaken = 0;
		board.changeLevel(1);
		controller = new Controller(board, this);
		highlightedSquares = new ArrayList<>();
		this.init();
		this.run();
	}
	/**
	 * Method to call the initFrame, initMenu and the initView methods
	 */
	private void init() {
		this.initFrame();
		this.initMenu();
		this.initView();
	}
	
	/**
	 * Method to initialize the Frame
	 */
	private void initFrame() {
		frame = new JFrame("JumpIN");
		GridLayout grid = new GridLayout(Board.BOARD_SIZE, Board.BOARD_SIZE);
		frame.setLayout(grid);
		frame.setSize(VIEW_DIMENSION);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	/**
	 * Method to to initialize the Menu
	 */
	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.gray);
		JMenu menu = new JMenu("Options");
		JMenuItem item1 = new JMenuItem("Reset");
		item1.addActionListener((event) -> this.reset());
		item1.setBackground(Color.gray);
		JMenuItem item2 = new JMenuItem("Exit");
		item2.setBackground(Color.gray);
		item2.addActionListener((event) -> System.exit(0));
		JMenuItem item3 = new JMenuItem("Undo");
		item3.setBackground(Color.gray);
		item3.addActionListener((event) -> JOptionPane.showMessageDialog(frame, "Undo"));
		JMenuItem item4 = new JMenuItem("Redo");
		item4.setBackground(Color.gray);
		item4.addActionListener((event) -> JOptionPane.showMessageDialog(frame, "Redo"));
		JMenuItem item5 = new JMenuItem("Help");
		item5.setBackground(Color.gray);
		item5.addActionListener((event) -> JOptionPane.showMessageDialog(frame, "Instructions"));
		JMenuItem item6 = new JMenuItem("Auto-solve");
		item6.setBackground(Color.gray);
		item6.addActionListener((event) -> JOptionPane.showMessageDialog(frame, "Auto-solve"));
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		menu.add(item4);
		menu.add(item5);
		menu.add(item6);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
    }
	
	/**
	 * Method which resets the current level to its initial state
	 * Resets the level
	 */
	private void reset() {
		JFrame popupFrame = new JFrame();
		int option = JOptionPane.showConfirmDialog(popupFrame, "Are you sure you want to reset level " + board.getLevel());
		
		if (option == 0) {
			turnsTaken = 0;
			board.changeLevel(board.getLevel());
			this.setButtonsEnabled(true);
			this.updateView();
		}
	}
	
	/**
	 * Method to enable or disable the buttons on the squares
	 * @param enabled true or false to enable to disable the buttons
	 */
	private void setButtonsEnabled(boolean enabled) {
		for (int y = 0; y < Board.BOARD_SIZE; y++) {
			for (int x = 0; x < Board.BOARD_SIZE; x++) {
				board.squares[x][y].setEnabled(enabled);
			}
		}
	}
	
	/**
	 * Method which initializes the View of the game
	 */
	private void initView() {
		for (int y = 0; y < Board.BOARD_SIZE; y++) {
			for (int x = 0; x < Board.BOARD_SIZE; x++) {
				frame.add(this.createButton(board.squares[x][y], x % 2 == 0 && y % 2 == 0));
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
	 * Method to create Button on the GUI
	 * @param square on the board used to set different attributes 
	 * @param cornerPiece variable used to check if it is a corner piece
	 * @return square on the board
	 */
	private JButton createButton(Square square, boolean cornerPiece) {
		square.setBorderPainted(cornerPiece);
		square.setBackground(cornerPiece ? CORNER_SQUARE_COLOR : MAIN_SQUARE_COLOR);
		square.setBorder(COMPOUND);
		square.addActionListener((event) -> controller.eventHandler(event));
	  	this.imageHandler(square);
	  	return square;
	}
	
	/**
	 * Method which updates the view of the board
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
	 * Method to display the level complete popup dialog message
	 */
	
	protected void displayLevelCompeletePopup() {
		JFrame popupFrame = new JFrame();
		String message = "";
		
		if (board.getLevel() < Board.totalLevels) {
			message = "Congratulations on completing Level " + board.getLevel() + "!";
			message += "\n";
			message += "Turns taken - " + turnsTaken;
			message += "\n";
			message += "Press OK to play level " + (board.getLevel() + 1);
			JOptionPane.showMessageDialog(popupFrame, message);
			board.changeLevel(board.getLevel() + 1);
			turnsTaken = 0;
			this.updateView();
		}
		
		else {
			message = "Congratulations on completing the game!";
			message += "\n";
			message += "Turns taken - " + turnsTaken;
			JOptionPane.showMessageDialog(popupFrame, message);
			this.setButtonsEnabled(false);
		}
	}
	
	/**
	 * Method to highlight the selected square so the player can see the selected piece
	 * @param square of which the color is to be set
	 */
	protected void highlightSelectedSquare(Square square) {
		square.setBackground(SELECTED_SQUARE_COLOR);
		highlightedSquares.add(square);
	}
	
	/**
	 * Method to unhighlight all highlighted squares in the view
	 */
	protected void unhighlightAllSquares() {
		for (Square square: highlightedSquares) {
			Location squareLocation = square.getLoc();
			boolean cornerPiece = squareLocation.getX() % 2 == 0 && squareLocation.getY() % 2 == 0;
			square.setBackground(cornerPiece ? CORNER_SQUARE_COLOR : MAIN_SQUARE_COLOR);
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
		square.setBackground(cornerPiece ? CORNER_SQUARE_COLOR : MAIN_SQUARE_COLOR);
	}
	
	/**
	 * Main method used to run the game and display the view
	 * @param args
	 */
	public static void main(String[] args) {
		Board newBoard = new Board(1);
		View view = new View(newBoard);
	}
}
