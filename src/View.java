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

class View extends Board {

	private static JFrame frame;
	private int currentLevel;
	private Controller controller;
	private ArrayList<Square> highlightedSquares; 
	
	/**
	 * Styling variables
	 */
	private final static Color MAIN_SQUARE_COLOR = new Color(2, 171, 80);
	private final static Color CORNER_SQUARE_COLOR = new Color(37, 177, 73);
	private final static Color SELECTED_SQUARE_COLOR = new Color(51, 204, 255);
	private final static Border LINE = new LineBorder(Color.white);
	private final static Border MARGIN = new EmptyBorder(5, 15, 5, 15);
	private final static Border COMPOUND = new CompoundBorder(LINE, MARGIN);
	private final static Dimension viewDimension = new Dimension(500, 550);
	
	/**
	 * Constructor to initialize the instance variables
	 */
	public View() {
		super(3);
		currentLevel = 3;
		controller = new Controller(this, this);
		highlightedSquares = new ArrayList<>();
		this.init();
		this.run();
	}
	/**
	 * Method to call the initFrame, initMenu and the initView
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
		frame.setSize(viewDimension);
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
		item1.setBackground(Color.gray);
		JMenuItem item2 = new JMenuItem("Exit");
		item2.setBackground(Color.gray);
		item2.addActionListener((event) -> System.exit(0));
		menu.add(item1);
		menu.add(item2);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
    }
	
	/**
	 * Method to initialize the View
	 */
	private void initView() {
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				frame.add(this.createButton(squares[x][y], x % 2 == 0 && y % 2 == 0));
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
	 * @param square the square on the board
	 * @param cornerPiece to check if it is a corner piece
	 * @return square the square which is on the board
	 */
	private JButton createButton(Square square, boolean cornerPiece) {
		square.setBorderPainted(cornerPiece);
		square.setBackground(cornerPiece ? CORNER_SQUARE_COLOR : MAIN_SQUARE_COLOR);
		square.setBorder(COMPOUND);
		square.addActionListener((event) -> controller.eventHandler(event));
	  	this.imageHandler(square);
	  	return square;
	}
	
	protected void updateView() {
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				this.imageHandler(squares[x][y]);
			}
		}
	}
	
	/**
	 * Method to handle the image and the implementation of the switch cases
	 * @param square the square which the image is on
	 * @param cornerPiece is the piece on the corner
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
	 * Method to display the level complete popup dialog message. 
	 */
	protected void displayLevelCompeletePopup() {
		JFrame popupFrame = new JFrame();
		JOptionPane.showMessageDialog(popupFrame, "Level Complete - Congratulations!");
	}
	
	/**
	 * Method to highlight the selected square so the player can see the selected piece
	 * @param square the square which the color is to be set
	 */
	protected void highlightSelectedSquare(Square square) {
		square.setBackground(SELECTED_SQUARE_COLOR);
		highlightedSquares.add(square);
	}
	
	/**
	 * Method to unhighlight all highlighted squares in the view.
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
	 * @param square the square which is to be cleared from the highlight
	 */
	protected void clearHighlight(Square square) {
		Location loc = square.getLoc();
		boolean cornerPiece = loc.getX() % 2 == 0 && loc.getY() % 2 == 0;
		square.setBackground(cornerPiece ? CORNER_SQUARE_COLOR : MAIN_SQUARE_COLOR);
	}
	
	/**
	 * Main method used to run the game
	 * @param args
	 */
	public static void main(String[] args) {
		View view = new View();
	}
}
