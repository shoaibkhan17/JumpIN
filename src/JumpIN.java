import java.util.Scanner;

/**
 * JumpInGame uses multiple class to run the game
 */
public class JumpIN {
	
	private Board board;
	private Scanner scanner;
	private Parser parser;
	private static boolean gameOn;

	public JumpIN(){
		scanner = new Scanner(System.in);
		parser = new Parser();
		gameOn = true;

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

	public void printLineGap() {
		for (int i = 0; i < 45; i++) {
			System.out.print('-');
		}
	}

	public void clearScreen() {
		try {
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		} catch(Exception E) {
			this.printLineGap();
		}
	}

	public void printSeparator() {
		this.printLineGap();
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
		this.printSeparator();

		// TODO check for winning logic here.
		while (gameOn) {
			this.printMoveText("What piece would you like to move: ", "\n-- PLEASE ENTER A VALID PIECE --", true); 
			this.printSeparator();
			this.printMoveText("Where would you like to move this piece: ", "\n-- PLEASE ENTER A VALID LOCATION --", false);
			this.printSeparator();
			if(this.isGameWon()) {
				gameOn = false;
				System.out.println("\nLevel Complete - Congratulations!");
			}
		}

		// board.printBoard();
		// System.out.println("Congratulations for finishing up the game");
	}
	
	//Method that returns true if all rabbits are in holes
	public boolean isGameWon() {
		return board.isGameWon();
	}
 
	public static void main(String[] args) {
		JumpIN jumpIN = new JumpIN();
		jumpIN.playGame(); 
		
	} 
}