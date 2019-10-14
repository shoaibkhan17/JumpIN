import java.util.Scanner;

public class JumpInGame {
	
	private Board testingBoard;
	
	JumpInGame(){
		testingBoard = new Board();
		testingBoard.initBoard();
	}
	
	public static void main(String[] args) { 
		JumpInGame game1 = new JumpInGame(); 
        boolean gameOn = true; 
         
        //game loop  
        while(gameOn) { 
        	//testingBoard.playGame(); 
        	//1.print board 
            System.out.println(game1.testingBoard.toString()); 
            game1.moveValidator("What peice would you like to move: ", "\nPlease enter a valid peice \n");
            game1.moveValidator("Where would you like to move this peice: ", "\nPlease enter a valid location \n");
            //2.checkBoardState 
            //3.getMove 
            //4.validateMove 
            //5.if valid apply move and reprint board 
            //6.Check game status 
            //7.Resume or break loop 
        	 
        } 
	} 
	 
	// validates move
		public void moveValidator(String moveText, String invalidText) {
			Scanner scanner = new Scanner(System.in);
			boolean valid = false;

			while (!valid) {
				System.out.println(this.toString());
				System.out.println(moveText);
				String input = scanner.nextLine();
				if (input.length() == 2) {
					valid = true;
				} else {
					System.out.println(invalidText);
				}
			}
			// scanner.close();
		}

		public void moveText() {
			this.moveValidator("What peice would you like to move: ", "\nPlease enter a valid peice \n");

			// Some issues with the scanner currently.
			this.moveValidator("Where would you like to move this peice: ", "\nPlease enter a valid location \n");
		}

//		public void playGame() {
//			// TODO move this into a while loop
//			this.initBoard();
//			this.moveText();
//		}
 
} 