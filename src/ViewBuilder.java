import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class ViewBuilder {
	private JFrame frame;
	
	private final static Dimension VIEW_DIMENSION = new Dimension(500, 550);
	
	public ViewBuilder(JFrame frame) {
		this.frame = frame;
	}
	
	public void initFrame(String name) {
		frame.setName(name);
		GridLayout grid = new GridLayout(Board.BOARD_SIZE, Board.BOARD_SIZE);
		frame.setLayout(grid);
		frame.setSize(VIEW_DIMENSION);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
