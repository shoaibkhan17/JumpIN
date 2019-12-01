import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * File or Class that contains all the constants used to develop the JumpIN game.
 *
 * @author Khalil Aalab - 101070879 
 * @author Kamaluddin Shakiri - 101054933 
 * @author Simon Yacoub - 101044159 
 * @author Md Aiman Sharif - 101062765 
 * @author Shoaib Khan - 101033582 
 */
public class Constants {
	
	/**
	 * Autosolver view update timer.
	 */
	public static final int SLEEP_TIMER = 100;
	
	/**
	 * Board size 5x5
	 */
	public static final int BOARD_SIZE = 5;
	
	/**
	 * Board print character
	 * Used for the text based version of the game.
	 */
	public static final char BOARD_PRINT_CHAR = '*';
	
	/**
	 * Path where all the saved games are stored.
	 */
	public static final String SAVED_GAME_PATH = "SavedGames/";
	
	/**
	 * Path where all the level XML files are stored.
	 */
	public static final String SAVED_LEVEL_PATH = "Levels/";

	/**
	 * XML Tags enum
	 * Used in the level builder class
	 */
	public enum XMLTerms {Level, LevelNumber, Mushroom, Coordinate1, Coordinate2, Rabbit, Color, RabbitCount, Fox, Movement}

	/**
	 * Total developed levels.
	 * Custom levels do not count under the developed levels.
	 * The 9th level is a custom level generated from the level builder.
	 * The 9th level is not added under the developed levels.
	 */
	public static final int TOTAL_LEVELS = 8;
	
	/**
	 * Button background color for the main empty square
	 */
	public final static Color MAIN_SQUARE_COLOR = new Color(2, 171, 80);
	
	/**
	 * Button background color for the corner squares.
	 * The corner squares are a shade lighter than the main squares
	 */
	public final static Color CORNER_SQUARE_COLOR = new Color(37, 177, 73);
	
	/**
	 * Selected square color for the main view.
	 */
	public final static Color SELECTED_SQUARE_COLOR = new Color(51, 204, 255);
	
	/**
	 * Border line color
	 */
	public final static Border LINE = new LineBorder(Color.white);
	
	/**
	 * Margin size
	 */
	public final static Border MARGIN = new EmptyBorder(5, 15, 5, 15);
	
	/**
	 * Border
	 */
	public final static Border COMPOUND = new CompoundBorder(LINE, MARGIN);
	
	/**
	 * Frame dimensions
	 */
	public final static Dimension VIEW_DIMENSION = new Dimension(500, 550);
	
    /**
     * Possible rows enum
     */
    public enum PossibleRows {A, B, C, D, E}
    
	/**
	 *  Rabbit colors enum
	 */
	public enum RABBIT_COLORS {Gray, White, Brown}

	/**
	 * Main game instructions
	 */
	public final static String GAME_INSTRUCTIONS = "Basic Information\r\n"
			+ "- Currently five levels are developed.\r\n"
			+ "- The goal of the game is to place all the rabbits inside the holes.\r\n"
			+ "- Rabbits can jump over objects, including mushrooms, foxes and other rabbits\r\n"
			+ "- Foxes can slide on empty spaces in the direction that the fox is oriented \r\n";
	
	public final static String LEVEL_BUILDER_INSTRUCTIONS = "Select a square to add a piece on the board.\n"
			+ "Clicking on the same piece will toggle the piece.\n"
			+ "Not all pieces can be added to every square.\n"
			+ "So, some squares won't show foxes or mushrooms.\n"
			+ "Once youre done, save the file.\nThen, close the builder and reopen the game.\n"
			+ "Select your level from the level select option.\n"
			+ "Enjoy!";
}
