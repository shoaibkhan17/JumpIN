import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
/**
 * View class is the 'View' of the MVC model
 * It is the visual representation of the JumpIn game
 * 
 */

/**
 * TO BE DONE
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */

class View extends Board {

	private static JFrame frame;
	private Square oldSelectSquare;
	
	/**
	 * Styling variables
	 */
	private final static Color MAIN_SQUARE_COLOR = new Color(2, 171, 80);
	private final static Color CORNOR_SQUARE_COLOR = new Color(37, 177, 73);
	private final static Color SELECTED_SQUARE_COLOR = new Color(51, 204, 255);
	private final static Border LINE = new LineBorder(Color.white);
	private final static Border MARGIN = new EmptyBorder(5, 15, 5, 15);
	private final static Border COMPOUND = new CompoundBorder(LINE, MARGIN);
	/**
	 * Constructor to initialize the instance variables
	 */
	public View() {
		super(1);
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
		GridLayout grid = new GridLayout(5, 5);		
		frame.setLayout(grid);
		frame.setSize(500, 500);
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
				String text = "";
				if (squares[x][y].getPiece() != null) {
					text = squares[x][y].getPiece().toString();
				}
				frame.add(this.createButton(squares[x][y], text, x % 2 == 0 && y % 2 == 0));
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
	 * Method to create Button on the gui
	 * @param square the square on the board
	 * @param text the text
	 * @param cornerPiece to check if it is a corner piece
	 * @return square the square which is on the board
	 */
	private JButton createButton(Square square, String text, boolean cornerPiece) {
		square.setBorderPainted(cornerPiece);
		square.setBackground(cornerPiece ? CORNOR_SQUARE_COLOR : MAIN_SQUARE_COLOR);
		square.setBorder(COMPOUND);
		square.addActionListener((event) -> this.eventHandler(event));
	  	this.imageHandler(square, cornerPiece);
	  	return square;
	}
	/**
	 * Method to handle the image and the implementation of the switch cases
	 * @param square the square which the image is on
	 * @param cornerPiece is the piece on the corner
	 */
	
	private void imageHandler(Square square, boolean cornerPiece) {
		String path = "src/assets/";
		ImageIcon icon;
		Piece piece = square.getPiece();
		
		if (piece == null) {
			icon = new ImageIcon(path + (cornerPiece ? "emptyCornor" : "empty") + ".png");					
			square.setIcon(icon);
			square.setText(null);
			return;
		}
	
		switch (piece.getType()) {
			case RABBIT:
				Rabbit rabbit = (Rabbit) piece;
				icon = new ImageIcon(path + "rabbit" + rabbit.rabbitColor + ".png");
				square.setIcon(icon);
				square.setText(null);			
				break;
			case MUSHROOM:
				icon = new ImageIcon(path + "mushroom.png");					
				square.setIcon(icon);
				square.setText(null);
				break;
			case HOLE:
				icon = new ImageIcon(path + "hole.png");					
				square.setIcon(icon);
				square.setText(null);
				break;
			case FOX:
				break;
			default:
				break;
		}
	}
	/**
	 * Method to highlight the selected square so the player can see the selected piece
	 * @param square the square which the color is to be set
	 */
	
	private void highlightSelectedSquare(Square square) {
		square.setBackground(SELECTED_SQUARE_COLOR);
	}
	
	/**
	 * Method to clear the highlight of the selected square
	 * @param square the square which is to be cleared from the highlight
	 */
	private void clearHighlight(Square square) {
		Location loc = square.getLoc();
		boolean cornerPiece = loc.getX() % 2 == 0 && loc.getY() % 2 == 0;
		square.setBackground(cornerPiece ? CORNOR_SQUARE_COLOR : MAIN_SQUARE_COLOR);
	}
	/**
	 * Method to handle the event, this method gets triggered when a button is pressed
	 * @param event that takes care of the correspondence event
	 */
	private void eventHandler(ActionEvent event) {
		if (this.selectedPiece == null) {
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
	
	
	private void select(ActionEvent event) {
		Square square = (Square) event.getSource();
		Piece piece = square.getPiece();
		Location location = square.getLoc();
		if (this.selectPiece(location)) {
			this.highlightSelectedSquare(square);
			oldSelectSquare = square;
		}
	}
	/**
	 * Method to move a piece from its location to a different location
	 * @param event which handles what happens after the button is pressed
	 */
	private void move(ActionEvent event) {
		Square square = (Square) event.getSource();
		Location location = square.getLoc();
		
		if (this.move(location)) {
			this.clearHighlight(oldSelectSquare);
			this.imageHandler(oldSelectSquare, false);
			this.imageHandler(square, false);
			
			if (this.isGameWon()) {
				JFrame popupFrame = new JFrame();
				JOptionPane.showMessageDialog(popupFrame, "Level Complete - Congratulations!");
			}
		}
	}
	
	public static void main(String[] args) {
		View obj = new View();
	}
}
