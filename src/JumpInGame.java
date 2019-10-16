/**
 * JumpInGame uses multiple class to run the game
 */
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
            System.out.println(game1.testingBoard.toString()); 
            int[] oldLocation = game1.getOldLocation();
            int[] newLocation = game1.getNewLocation();
            
            game1.testingBoard.squares[oldLocation[0]][oldLocation[1]].removePiece();
            game1.testingBoard.squares[newLocation[0]][newLocation[1]].setPiece(new Rabbit());
            
            //1.print board 
            //2.checkBoardState 
            //3.getMove 
            //4.validateMove 
            //5.if valid apply move and reprint board 
            //6.Check game status 
            //7.Resume or break loop
        } 
	} 
	 
		public boolean locationValidator(String userInput) {
			boolean valid = false;
			
			if(userInput.length() == 2) {
				return true;
			}
			else {
				return false;
			}
		}

		public int[] getOldLocation() {
			Scanner scanner = new Scanner(System.in);
			System.out.println("What piece would you like to move: ");
			String input = "";
			
			input = scanner.next();
			
			int oldColumn = -1;
			int oldRow = -1;
			
			if(locationValidator(input)) {
				
				oldColumn = charToInt(input.charAt(0));
				oldRow = Character.getNumericValue(input.charAt(1) - 1);
				
			}
			
			int[] oldCoordinates = new int[2];
			oldCoordinates[0] = oldColumn;
			oldCoordinates[1] = oldRow;
			
			return oldCoordinates;
		}
		
		
		public int[] getNewLocation() {
			int [] newLocation = new int[2];
			Scanner scanner = new Scanner(System.in);
			System.out.println("Where would you like to move: ");
			
			String input = scanner.next();
			int newColumn = charToInt(input.charAt(0));
			int newRow = Character.getNumericValue(input.charAt(1) - 1);
			
			newLocation[0] = newColumn;
			newLocation[1]  = newRow;
			

			return newLocation;
		}
		
		public int charToInt(char c) {
			switch (c) {

			case 'A':
				return 0;
			case 'B':
				return 1;
			case 'C':
				return 2;
			case 'D':
				return 3;
			case 'E':
				return 4;

			default:
				break;
			}

			return 0;
		}
		
		public boolean isGameOver() {
			if(true)//check win condition
			
			return true;
			return false;
		}
 
}