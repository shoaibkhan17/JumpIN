import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;

/**
 * View builder class.
 * A helper class that help build the view classes (View and LevelBuilderView)
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class ViewBuilder {
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
	 * Method to create the menu items
	 * @param name name of the menu
	 * @param actionListener takes care of the corresponding action performed
	 * @return item a JMenuItem
	 */
	protected JMenuItem createMenuItem(String name, ActionListener actionListener) {
		JMenuItem item = new JMenuItem(name);
		item.setBackground(Color.LIGHT_GRAY);
		item.addActionListener(actionListener);
		return item;
	}
	
	/**
	 * Method which updates the level builder view
	 * @param board
	 */
	protected void updateView(Board board) {
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			for (int x = 0; x < Constants.BOARD_SIZE; x++) {
				this.imageHandler(board.squares[x][y]);
			}
		}
	}
	
	/**
	 * Method to create Button on the GUI
	 * @param square on the board used to set different attributes
	 * @param cornerPiece variable used to check if it is a corner piece
	 * @param ActionLister onClick method
	 * @return square on the board
	 */
	protected JButton createButton(Square square, boolean cornerPiece, ActionListener listener) {
		square.setBorderPainted(cornerPiece);
		square.setBackground(cornerPiece ? Constants.CORNER_SQUARE_COLOR : Constants.MAIN_SQUARE_COLOR);
		square.setBorder(Constants.COMPOUND);
		square.addActionListener(listener);
		this.imageHandler(square);
		return square;
	}
}
