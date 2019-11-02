import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class View extends Board{
	
	public View() {
		super(1);
		JFrame jf = new JFrame("JumpIN");
		JMenuBar mb = new JMenuBar();
		GridLayout grid = new GridLayout(5, 5);
		
		jf.setLayout(grid);

		Square squares[][] = super.getSquares();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				String text = "";
				if (squares[i][j].getPiece() != null) {
					text = squares[i][j].getPiece().toString();
				} else {
					text = "EMPTY";
				}

				JButton button = new JButton(text);
				jf.add(button);
			}
		}
		jf.setSize(800, 800);
		jf.setVisible(true);
		
	}
	//ImageIcon image = new ImageIcon(getClass().getResource("Fox.png"));
	//jf.add(image, BorderLayout.NORTH);
	//jf.add(new JLabel(image), BorderLayout.NORTH);
	
	
	public static void main(String[] args) {
		View obj = new View();

	}

}
