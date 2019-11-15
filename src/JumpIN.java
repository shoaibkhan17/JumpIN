import java.util.Scanner;
/**
 *  Main class which contains the functionality of the game.
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class JumpIN {
	
	private Board board;
	private Scanner scanner;
	private Parser parser;

	/**
	 * Default constructor which initializes instance variables
	 */
	public JumpIN() {
		this.scanner = new Scanner(System.in);
		this.parser = new Parser();

		// Defaulting to level 1 for now
		board = new Board(2);
	}

	/**
	 * method which prints out the text when a move is made, invalid text is entered or when a piece is being selected
	 * @param moveText text printed out and prompts the user for input
	 * @param invalidText text printed out saying invalid location if invalid location entered
	 * @param selecting true when the user is selecting a piece, false if the piece is not being selected
	 */
	private void printMoveText(String moveText, String invalidText, boolean selecting) {
		do {
			// Print the board.
			board.printBoard();
			System.out.print(moveText);
			String input = scanner.nextLine();
			parser.setText(input);

			if (parser.isValidLocation) {
				// Selecting the piece
				if (selecting) {
					// If the piece cannot be selected.
					if (!board.selectPiece(parser.getLocation())) {
						this.printSeparator();
						System.out.println(invalidText);
						parser.isValidLocation = false;
					}
				}

				// Moving the piece to a new location
				else {
					// If the selected piece cannot be moved to the new location.
					if (!board.move(parser.getLocation())) {
						this.printSeparator();
						System.out.println(invalidText);
						parser.isValidLocation = false;
					}
				}
			}

			else {
				System.out.println(invalidText + "\n");
			}

		// Continue the loop this entire process is successful. 
		} while (!parser.isValidLocation);
	}

	/**
	 * prints a line gap on the board
	 */
	private void printLineGap() {
		for (int i = 0; i < 45; i++) {
			System.out.print('-');
		}
		System.out.println();
	}

	/**
	 * calls the print line gap method
	 */
	private void printSeparator() {
		this.printLineGap();
	}

	/**
	 * Function to print out the welcome message and basic instructions. 
	 */
	private void printWelcomeMessage() {
		this.printSeparator();
		System.out.println("-- Welcome to the JumpIN game --\n");
		System.out.println("Currently two levels are developed.");
		System.out.println("Playing level 2");
		System.out.println("To move a piece:"); 
		System.out.println("-Enter the piece's location");
		System.out.println("-Wait for validation");
		System.out.println("-Enter the location you want to move the piece to\n");
		this.printSeparator();
	}

	/**
	 * method for playing the game
	 */
	public void playGame() {		
		this.printWelcomeMessage();

		// While the game hasn't finished yet. 
		while (!board.isGameWon()) {
			this.printMoveText("What piece would you like to move: ", "\n-- PLEASE ENTER A VALID PIECE --", true);
			this.printSeparator();
			this.printMoveText("Where would you like to move this piece: ", "\n-- PLEASE ENTER A VALID LOCATION --",
					false);
			this.printSeparator();
		}
		System.out.println(board.toString());
		System.out.println("Level Complete - Congratulations!");
	}
	
	/**
	 * Main method used to create an instance of the JumpIN game object and
	 * play the game
	 */
	public static void main(String[] args) {
		JumpIN jumpIN = new JumpIN();
		jumpIN.playGame(); 
	} 
}