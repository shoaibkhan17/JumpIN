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
	private Square[][] squares;
	
	/**
	 * Styling variables
	 */
	private final static Color MAIN_SQUARE_COLOR = new Color(2, 171, 80);
	private final static Color CORNOR_SQUARE_COLOR = new Color(37, 177, 73);
	private final static Border LINE = new LineBorder(Color.white);
	private final static Border MARGIN = new EmptyBorder(5, 15, 5, 15);
	private final static Border COMPOUND = new CompoundBorder(LINE, MARGIN);
	private int counter = 0;
	private enum RABBIT_COLORS {Gray, White, Brown}
	
	public View() {
		super(1);
		this.init();
		this.run();
	}
	
	private void init() {
		squares = super.getSquares();
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
		square.addActionListener((event) -> this.test(event));
	  	this.imageHandler(text, square, cornorPiece);
	  	return square;
	}
	
	private void imageHandler(String text, JButton button, boolean cornorPiece) {
		String path = "src/assets/";
		ImageIcon icon;
	
		switch (text) {
			case ("R"):
				icon =  new ImageIcon(path + "rabbit" + RABBIT_COLORS.values()[counter] +".png");					
				button.setIcon(icon);
				button.setText(null);
				counter++;
				
				if (counter > 3) {
					counter = 0;
				}
				
				break;
			case ("M"):
				icon = new ImageIcon(path + "mushroom.png");					
				button.setIcon(icon);
				button.setText(null);
				break;
			case ("O"):
				icon = new ImageIcon(path + "hole.png");					
				button.setIcon(icon);
				button.setText(null);
				break;
			case (""):
				icon = new ImageIcon(path + (cornorPiece ? "emptyCornor" : "empty") + ".png");					
				button.setIcon(icon);
				button.setText(null);
				break;
			default:
				break;
		}
	}
	
	private void test(ActionEvent event) {
		Square square = (Square) event.getSource();
		Piece piece = square.getPiece();
		Location location = square.getLoc();
		
		if (piece != null) {
			String selectableText = piece.isSelectable() ? "selectable" : "not selectable";
			System.out.println(piece.getType() + " at " + location.toStringNumeric() + " - " + selectableText);
		}
	}
	
	public static void main(String[] args) {
		View obj = new View();
	}
}
