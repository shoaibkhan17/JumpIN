import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class View extends Board {

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
	
	public View() {
		super(1);
		this.init();
		this.run();
	}
	
	private void init() {
		this.initFrame();
		this.initMenu();
		this.initView();
	}
	
	private void initFrame() {
		frame = new JFrame("JumpIN");
		GridLayout grid = new GridLayout(5, 5);		
		frame.setLayout(grid);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
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
	
	private void run() {
		frame.setVisible(true);
	}
	
	//ImageIcon image = new ImageIcon(getClass().getResource("Fox.png"));
	//jf.add(image, BorderLayout.NORTH);
	//jf.add(new JLabel(image), BorderLayout.NORTH);
	
	private JButton createButton(Square square, String text, boolean cornorPiece) {
		square.setBorderPainted(cornorPiece);
		square.setBackground(cornorPiece ? CORNOR_SQUARE_COLOR : MAIN_SQUARE_COLOR);
		square.setBorder(COMPOUND);
		square.addActionListener((event) -> this.eventHandler(event));
	  	this.imageHandler(square, cornorPiece);
	  	return square;
	}
	
	private void imageHandler(Square square, boolean cornorPiece) {
		String path = "src/assets/";
		ImageIcon icon;
		Piece piece = square.getPiece();
		
		if (piece == null) {
			icon = new ImageIcon(path + (cornorPiece ? "emptyCornor" : "empty") + ".png");					
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
	
	private void highlightSelectedSquare(Square square) {
		square.setBackground(SELECTED_SQUARE_COLOR);
	}
	
	private void clearHighlight(Square square) {
		Location loc = square.getLoc();
		boolean cornerPiece = loc.getX() % 2 == 0 && loc.getY() % 2 == 0;
		square.setBackground(cornerPiece ? CORNOR_SQUARE_COLOR : MAIN_SQUARE_COLOR);
	}
	
	private void eventHandler(ActionEvent event) {
		if (this.selectedPiece == null) {
			this.select(event);
		}
		
		else {
			this.move(event);
		}
	}
	
	private void select(ActionEvent event) {
		Square square = (Square) event.getSource();
		Piece piece = square.getPiece();
		Location location = square.getLoc();
		if (this.selectPiece(location)) {
			this.highlightSelectedSquare(square);
			oldSelectSquare = square;
		}
	}
	
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
