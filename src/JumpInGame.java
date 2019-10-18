import java.util.Scanner;

/**
 * JumpInGame uses multiple class to run the game
 */
public class JumpInGame {
	
	private Board board;
	private Scanner scanner;
	private Parser parser;
	 
	// constructor
	public JumpInGame(){
		scanner = new Scanner(System.in);
		parser = new Parser();

		// Defaulting to level 1 for now
		board = new Board(1);
	}

	public void printMoveText(String moveText, String invalidText, boolean selecting) {
		do {
			board.printBoard();

			// TODO Display hole status here.

			System.out.print(moveText);
			String input = scanner.nextLine();
			parser.setText(input);
			parser.check();

			if (parser.isValidLocation) {

				// Selecting the piece
				if (selecting) {
					if (!board.selectPiece(parser.getLocation())) {
						System.out.println(invalidText + "\n");
						parser.isValidLocation = false;
					}
				}

				// Moving the piece to a new location
				else {
					if (!board.move(parser.getLocation())) {
						System.out.println(invalidText + "\n");
						parser.isValidLocation = false;
					}
				}
			}
			
			else {
				System.out.println(invalidText + "\n");
			}

		} while (!parser.isValidLocation);
	}

	public void playGame() {

		//1. print board 
		//2. wait for input 
		//3. check input 
		//4. validate move
		//5. move
		//6.Check game status 
		//7.Resume or break loop or switch level and restart

		// While the game hasn't finished yet. 
		 while (true) {
			this.printMoveText("What piece would you like to move: ", "\nPlease enter a valid piece.", true); 
			this.printMoveText("Where would you like to move this piece: ", "\nPlease enter a valid location.", false);
			
		 }
			
	}
 
	public static void main(String[] args) {
		JumpInGame jumpIN = new JumpInGame();
		jumpIN.playGame(); 
	} 
}