import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class GUI extends Board{
	
	public GUI() {
		super(1);
		JFrame jf = new JFrame("JumpIN");
		JMenuBar mb = new JMenuBar();
		GridLayout grid = new GradeLayout();
		
		jf.setLayout new grid();

		Square squares[][] = super.getSquares();
		for(int i=0 ; i<super.BOARD_SIZE; i++) {
			for (int j =0; j <super.BOARD_SIZE; j++) {
				String text = "";
				if (squares[i][j].getPiece() != null) {
					text = squares[i][j].getPiece().toString();
				}
				else {
					text = "NA";
				}
				
				JButton button = new JButton(text);
				jf.add(button);
			}
		}
		jf.setSize(800, 800);
		jf.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		GUI obj = new GUI();

	}

}
