import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JLabel;

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
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				String text = "";
				if (squares[x][y].getPiece() != null) {
					text = squares[x][y].getPiece().toString();
				} else {
					text = "EMPTY";
				}
				frame.add(this.createButton(text, x % 2 == 0 && y % 2 == 0));
			}
		}
	}
	
	private void run() {
		frame.setVisible(true);
	}
	
	//ImageIcon image = new ImageIcon(getClass().getResource("Fox.png"));
	//jf.add(image, BorderLayout.NORTH);
	//jf.add(new JLabel(image), BorderLayout.NORTH);
	
	private JButton createButton(String text, boolean cornorPiece) {
		JButton button = new JButton(text);
		button.setFocusPainted(false);
		button.setBorderPainted(cornorPiece);
		button.setBackground(cornorPiece ? CORNOR_SQUARE_COLOR : MAIN_SQUARE_COLOR);
	  	button.setBorder(COMPOUND);
	  	this.imageHandler(text, button);
	  	return button;
	}
	
	private void imageHandler(String text, JButton button) {
		ImageIcon icon;
		System.out.print(text + " ");
		switch (text) {
			case ("R"):
				icon =  new ImageIcon("src/resources/rabbitGray.png");					
				button.setIcon(icon);
				button.setText(null);
				break;
			case ("M"):
				icon = new ImageIcon("src/resources/mushroom.png");					
				button.setIcon(icon);
				button.setText(null);
				break;
			case ("O"):
				icon = new ImageIcon("src/resources/hole.png");					
				button.setIcon(icon);
				button.setText(null);
				break;
			default:
				break;
		}
	}
	
	public static void main(String[] args) {
		View obj = new View();
	}
}
