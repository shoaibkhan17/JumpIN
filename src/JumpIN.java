import java.util.Scanner;

/**
 *  Main class which contains the functionality of the game
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
		this.board = new Board(1);
	}

	/**
	 * method which prints out the text when a move is made, invalid text is entered or when a piece is being selected
	 * @param moveText text printed out and prompts the user for input
	 * @param invalidText text printed out saying invalid location if invalid location entered
	 * @param selecting true when the user is selecting a piece, false if the piece is not being selected
	 */
	public void printMoveText(String moveText, String invalidText, boolean selecting) {
		do {
			board.printBoard();
			System.out.print(moveText);
			String input = scanner.nextLine();
			parser.setText(input);

			if (parser.isValidLocation) {

				// Selecting the piece
				if (selecting) {
					if (!board.selectPiece(parser.getLocation())) {
						this.printSeparator();
						System.out.println(invalidText);
						parser.isValidLocation = false;
					}
				}

				// Moving the piece to a new location
				else {
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

		} while (!parser.isValidLocation);
	}

	/**
	 * prints a line gap on the board
	 */
	public void printLineGap() {
		for (int i = 0; i < 45; i++) {
			System.out.print('-');
		}
		System.out.println();
	}

	/**
	 * clears the screen
	 */
	public void clearScreen() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception E) {
			this.printLineGap();
		}
	}

	/**
	 * calls the print line gap method
	 */
	public void printSeparator() {
		this.printLineGap();
	}

	/**
	 * method for playing the game
	 */
	public void playGame() {

		//1. print board 
		//2. wait for input 
		//3. check input 
		//4. validate move
		//5. move
		//6.Check game status 
		//7.Resume or break loop or switch level and restart

		// While the game hasn't finished yet. 
		this.printSeparator();

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